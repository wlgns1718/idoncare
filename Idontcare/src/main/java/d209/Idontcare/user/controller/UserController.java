package d209.Idontcare.user.controller;


import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.AuthenticationException;
import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.relationship.dto.res.RelationshipResDto;
import d209.Idontcare.user.dto.*;
import d209.Idontcare.user.service.OauthService;
import d209.Idontcare.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Tag(name="유저 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    
    private final int HOUR = 1000 * 60 * 60;
    private final int DAY = 1000 * 60 * 60 * 24;

    private final OauthService oauthService;
    private final UserService userService;
    
    @PostMapping(value = "/login")
    @Operation(summary="로그인", description = "카카오 코드를 통해 로그인을 요청합니다")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = GetUserInfoDto.class))),
        @ApiResponse(responseCode= BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
    })
    public ResponseDto<?> login(@RequestBody KakaoDto kakao, HttpServletResponse response){
        try {
            String accessToken = oauthService.getOauthAccessToken(kakao.getCode());
            
            GetUserInfoDto userInfo = oauthService.getUserInfo(accessToken);
            
            Cookie cookie = new Cookie("refreshToken",userInfo.getRefreshToken());
            int refreshExpired = DAY * 7;
            cookie.setMaxAge(refreshExpired);
            response.addCookie(cookie);
            
            return ResponseDto.success(userInfo);
        }
        catch (CommonException e){
            return ResponseDto.fail(e);
        }
    }

    @PostMapping(value = "/regist")
    public ResponseDto<?> regist(@RequestBody JoinUserReqDto req){

        try{
           //유저 테이블과 유저 디테일에 들어갈 데이터 분리
           userService.joinUser(req);

           return ResponseDto.success(null);
        }
        catch (CommonException e){
            return ResponseDto.fail(e);
        }
    }



}
