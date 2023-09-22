package d209.Idontcare.user.service;


import com.fasterxml.jackson.core.type.TypeReference;
import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.jwt.JwtTokenProvider;
import d209.Idontcare.user.dto.GetUserInfoDto;
import d209.Idontcare.user.dto.KakaoUserInfoDto;
import d209.Idontcare.user.dto.req.LoginReqDto;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements OauthService{
    
    private final UserService userService;
    private final UserRepository userRepository;
    
    private final JwtTokenProvider jwtTokenProvider;
    
    private final static long HOUR = 1000 * 60 * 60;
    private final static long DAY = 1000 * 60 * 60 * 24;

    //properties분리해서 숨겨줄 값들
//    private static final String REST_API_KEY = "57207a98af7edacf30bb14f2b4effbc4";
    private static final String REDIRECT_URL = "http://localhost:5173/login";
//    private static final String CLIENT_SECRET = "Srvk6ZfqwnWw6bDf2tZVA4I9VP731p3D";
    
    private static final String REST_API_KEY = "ac3bdd0a13805da6552ba025e4967855";
    private static final String CLIENT_SECRET = "tMxbImuvzmdNytv1Cbz1UT0qIOC8pTV7";
    
    
    @SuppressWarnings("unchecked")
    @Override
    public String getOauthAccessToken(LoginReqDto req){
        final String URL = "https://kauth.kakao.com/oauth/token";

        Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("grant_type", "authorization_code");
            requestBody.put("client_id", REST_API_KEY);
            requestBody.put("redirect_uri", REDIRECT_URL);
            requestBody.put("code", req.getCode());
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
        } catch(Exception e) {
            System.out.println(e.getMessage());
            throw new BadRequestException("코드에 대한 요청이 처리되지 못 하였습니다");
        }
        
        if(result.getStatus() != HttpStatus.OK) {throw new BadRequestException("코드에 대한 요청이 처리되지 못 하였습니다");}
        
        Map<String, Object> responseBody = result.getBody(Map.class);

        return (String)responseBody.get("access_token");
    }

    @SuppressWarnings("unchecked")
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
        } catch(Exception e) {
            throw new BadRequestException("요청에 오류가 발생하였습니다");
        }

        
        if(result.getStatus() != HttpStatus.OK) {throw new BadRequestException("요청에 오류가 발생하였습니다");}
        
        KakaoUserInfoDto kakaoUserInfo = new KakaoUserInfoDto((Map<String, Object>)result.getBody());
        
        //닉네임 설정
        String nickname = null;
        if(kakaoUserInfo.getProperties() != null) nickname = kakaoUserInfo.getProperties().getNickname();   //닉네임 사용 허용한 경우
        
        //이메일 설정
        String email = null;
        if(kakaoUserInfo.getKakaoAccount().getHasEmail()) email = kakaoUserInfo.getKakaoAccount().getEmail(); //이메일 사용에 허용한 경우
        
        //지급된 kakao id를 통해 우리의 DB에서 찾자
        Optional<User> userOptional = userService.findByKakaoId(kakaoUserInfo.getId());
        
        User user = null;
        boolean isJoined = false;
        String jwtAccessToken = null;
        String jwtRefreshToken = null;
        
        if(userOptional.isEmpty()){
            //만약 카카오 로그인 자체를 한적이 없으면
            // -> 새롭게 유저를 만들어서 넣어주자
            User newUser = User.builder()
                .kakaoId(kakaoUserInfo.getId())
                .build();
            newUser.setUUID();
            user = userRepository.save(newUser);
        }
        else{
            //만약 카카오 로그인을 한 적이 있으면
            user = userOptional.get();
            
            if(user.getRole() != null){
                //회원가입까지 되어 있으면
                // -> 토큰 발급
                jwtAccessToken = jwtTokenProvider.createAccessToken(user.getUserId(), user.getRole());
                System.out.printf("User : %s\n", jwtTokenProvider.getAuthInfo(jwtAccessToken));
                jwtRefreshToken = jwtTokenProvider.createRefreshToken(user.getUserId(), user.getRole());
                isJoined = true;
            }
        }
        
        

        GetUserInfoDto resultDto = GetUserInfoDto.builder()
            .userId(user.getUserId())
            .msg(isJoined ? "등록된 회원입니다." : "회원정보가 없습니다. 회원가입 페이지로 이동합니다.")
            .joined(isJoined)
            .nickname(nickname)
            .email(email)
            .accessToken(jwtAccessToken)
            .refreshToken(jwtRefreshToken)
            .build();
        
        
        return resultDto;
    }
}
