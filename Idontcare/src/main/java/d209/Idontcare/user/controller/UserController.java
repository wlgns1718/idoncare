package d209.Idontcare.user.controller;


import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.user.dto.*;
import d209.Idontcare.user.service.OauthService;
import d209.Idontcare.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final OauthService oauthService;
    private final UserService userService;
    @PostMapping(value = "/login")
    public ResponseDto<?> login(@RequestBody KakaoDto kakao, HttpServletResponse response){
        try {
            HashMap<String,Object> resultMap = new HashMap<String, Object>();
            String accessToken = oauthService.getOauthAccessToken(kakao.getCode());
            Map<String,Object> userInfo = oauthService.getUserInfo(accessToken);

            if(!(Boolean)(userInfo.get("boolean"))){
                resultMap.put("msg","회원가입 해야합니다.");
                return ResponseDto.success(resultMap);
            }

            String jwtAccessToken = String.valueOf(userInfo.get("accessToken"));
            String jwtRefreshToken = String.valueOf(userInfo.get("refreshToken"));
            resultMap.put("accessToken",jwtAccessToken);
            Cookie cookie = new Cookie("refreshToken",jwtRefreshToken);
            int refreshExpired = 1000 * 60 * 60 * 24 * 7; //7일
            cookie.setMaxAge(refreshExpired);
            response.addCookie(cookie);
            resultMap.put("id",userInfo.get("id"));
            resultMap.put("msg","카카오 로그인 성공");
            resultMap.put("msg2",userInfo.get("msg"));
            return ResponseDto.success(resultMap);
        }
        catch (IOException e){
            return ResponseDto.fail(e);
        }
        catch(CommonException e){
            //유저가 회원가입 하지 않은 경우
            return ResponseDto.fail(e);
        }
        catch (Exception e){
            return ResponseDto.success(null);
        }
    }

    @PostMapping(value = "/regist")
    public ResponseDto<?> regist(@RequestBody JoinUserDto joinUserDto){

        try{
           //유저 테이블과 유저 디테일에 들어갈 데이터 분리
           userService.joinUser(joinUserDto);

           return ResponseDto.success(null);
        }
        catch (CommonException e){
            return ResponseDto.fail(e);
        }
    }



}
