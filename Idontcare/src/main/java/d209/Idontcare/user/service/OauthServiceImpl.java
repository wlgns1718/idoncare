package d209.Idontcare.user.service;


import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import d209.Idontcare.TUser;
import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.common.exception.NoSuchUserException;
import d209.Idontcare.user.dto.GetUserInfoDto;
import d209.Idontcare.user.dto.KakaoUserInfoDto;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{
    private final UserService userService;
    
    private final static long HOUR = 1000 * 60 * 60;
    private final static long DAY = 1000 * 60 * 60 * 24;

    private static final Long ACCESS_EXPIRED = 2 * HOUR; //2시간
    private static final Long REFRESH_EXPIRED = 7 * DAY; //7일

    //properties분리해서 숨겨줄 값들
    private static final String REST_API_KEY = "57207a98af7edacf30bb14f2b4effbc4";
    private static final String REDIRECT_URL = "http://127.0.0.1:8000";
    private static final String CLIENT_SECRET = "Srvk6ZfqwnWw6bDf2tZVA4I9VP731p3D";

    @Override
    public String getOauthAccessToken(String code){
        final String URL = "https://kauth.kakao.com/oauth/token";

        Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("grant_type", "authorization_code");
            requestBody.put("client_id", REST_API_KEY);
            requestBody.put("redirect_uri", REDIRECT_URL);
            requestBody.put("code", code);
            requestBody.put("client_secret", CLIENT_SECRET);
        
        //인가 코드를 통해 Oauth에서 AccessToken 얻기
        APIResultDto result = null;
        try{
            result = APIBuilder.build()
                            .url(URL)
                            .method(HttpMethod.POST)
                            .mediaType(MediaType.APPLICATION_FORM_URLENCODED)
                            .body(requestBody)
                            .execute();
        } catch(Exception e) {throw new BadRequestException("코드에 대한 요청이 처리되지 못 하였습니다");}
        
        if(result.getStatus() != HttpStatus.OK) {throw new BadRequestException("코드에 대한 요청이 처리되지 못 하였습니다");}
        
        Map<String, Object> responseBody = (Map<String, Object>)result.getBody();
        
        return (String)responseBody.get("access_token");
    }

    @Override
    public GetUserInfoDto getUserInfo(String accessToken){
        final String URL = "https://kapi.kakao.com/v2/user/me";
        APIResultDto result = null;
        try{
            result = APIBuilder.build()
                            .url(URL)
                            .method(HttpMethod.GET)
                            .header(Map.of("Authorization", "Bearer " + accessToken))
                            .execute();
        } catch(Exception e) {throw new BadRequestException("요청에 오류가 발생하였습니다");}

        
        if(result.getStatus() != HttpStatus.OK) {throw new BadRequestException("요청에 오류가 발생하였습니다");}
        
        KakaoUserInfoDto kakaoUserInfo = new KakaoUserInfoDto((Map<String, Object>)result.getBody());
        
        //닉네임 설정
        String nickname = null;
        if(kakaoUserInfo.getProperties() != null) nickname = kakaoUserInfo.getProperties().getNickname();   //닉네임 사용 허용한 경우
        
        //이메일 설정
        String email = null;
        if(kakaoUserInfo.getKakaoAccount().getHasEmail()) email = kakaoUserInfo.getKakaoAccount().getEmail(); //이메일 사용에 허용한 경우
        
        Optional<User> user = userService.findByUserId(kakaoUserInfo.getId());
        
        String jwtAccessToken = null;
        String jwtRefreshToken = null;
        
        if(user.isPresent()){
            jwtAccessToken = JwtUtil.createToken(String.valueOf(kakaoUserInfo.getId()),ACCESS_EXPIRED);
            jwtRefreshToken = JwtUtil.createToken(String.valueOf(kakaoUserInfo.getId()),REFRESH_EXPIRED);
        }
        
        GetUserInfoDto resultDto = GetUserInfoDto.builder()
            .userId(kakaoUserInfo.getId())
            .msg(user.isEmpty() ? "회원정보가 없습니다. 회원가입 페이지로 이동합니다." : "등록된 회원입니다.")
            .isJoined(user.isPresent())
            .nickname(nickname)
            .email(email)
            .accessToken(jwtAccessToken)
            .refreshToken(jwtRefreshToken)
            .build();
        
        return resultDto;
    }
}
