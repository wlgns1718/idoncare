package d209.Idontcare.user.controller;


import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.jwt.AccessRefreshTokenDto;
import d209.Idontcare.jwt.JwtTokenProvider;
import d209.Idontcare.user.dto.*;
import d209.Idontcare.user.dto.req.LoginReqDto;
import d209.Idontcare.user.dto.req.RefreshReqDto;
import d209.Idontcare.user.dto.res.LoginResDto;
import d209.Idontcare.user.dto.res.UserInfoResDto;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.service.OauthService;
import d209.Idontcare.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;

@Tag(name="유저 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    
    private final OauthService oauthService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Value("${jwt.refresh-expiration-time}")
    private Long refreshExpirationTime;
    
    @PostMapping(value = "/login")
    @Operation(summary="로그인", description = "카카오 코드를 통해 로그인을 요청합니다")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = GetUserInfoDto.class))),
        @ApiResponse(responseCode= BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
    })
    public ResponseDto login(@RequestBody LoginReqDto req,
                             HttpServletResponse response){
        String accessToken = oauthService.getOauthAccessToken(req);
        GetUserInfoDto userInfo = oauthService.getUserInfo(accessToken);
        
        response.addHeader("Authorization", "Bearer " + userInfo.getAccessToken());
        
        ResponseCookie cookie = ResponseCookie.from("refreshToken", userInfo.getRefreshToken())
            .path("/")
            .sameSite("None")
            .secure(true)
//            .httpOnly(true)
            .domain("j9d209.p.ssafy.io")
            .maxAge(refreshExpirationTime / 1000)
            .build();
        response.addHeader("set-cookie", cookie.toString());
        
        return ResponseDto.success(new LoginResDto(userInfo));
    }

    @PostMapping(value = "/regist")
    @Operation(summary="회원가입", description = "카카오 유저 ID와 입력된 값들을 통해 회원가입")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode= BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
        @ApiResponse(responseCode= DuplicatedException.CODE, description = DuplicatedException.DESCRIPTION),
    })
    public ResponseDto regist(@RequestBody JoinUserReqDto req){
       userService.joinUser(req);
       return ResponseDto.success(null);
    }

    @GetMapping("")
    @Operation(summary = "내정보", description = "내 정보 보기")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = UserInfoResDto.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto myInfo(HttpServletRequest request){

        
        Long userId = (Long)request.getAttribute("userId");
        User user = userService.findByUserId(userId).get();
        return ResponseDto.success(new UserInfoResDto(user));
    }
    
    @PostMapping("/login/{kakaoId}")
    @Operation(summary = "테스트 로그인", description = "코드를 통해 로그인, [1 ~ 2]: 부모, [3 ~ 5] : 자식")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = LoginResDto.class)))
    })
    public ResponseDto loginTest(@PathVariable("kakaoId") Long kakaoId,
                                 HttpServletResponse response){
        GetUserInfoDto userInfo = oauthService.getUserInfoTest(kakaoId);
        
        response.addHeader("Authorization", "Bearer " + userInfo.getAccessToken());
        
        ResponseCookie cookie = ResponseCookie.from("refreshToken", userInfo.getRefreshToken())
            .path("/")
            .sameSite("None")
            .secure(true)
//            .httpOnly(true)
            .domain("j9d209.p.ssafy.io")
            .maxAge(refreshExpirationTime / 1000)
            .build();
        response.addHeader("set-cookie", cookie.toString());
        
        return ResponseDto.success(new LoginResDto(userInfo));
    }
    
    @PostMapping("/logout")
    @Operation(summary = "로그아웃", description = "로그아웃을 수행합니다")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공",
            content=@Content(schema = @Schema(implementation = AccessRefreshTokenDto.class)))
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto logout(HttpServletRequest request){
        String accessToken = jwtTokenProvider.getBearerToken(request);
        jwtTokenProvider.expireTokens(accessToken);
        
        return ResponseDto.success(null);
    }
    
    @GetMapping("/token/valid")
    @Operation(summary = "토큰 유효성 체크", description = "Access Token의 유효성을 체크")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공",
            content=@Content(schema = @Schema(implementation = Void.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
        @ApiResponse(responseCode= ExpiredTokenException.CODE, description = ExpiredTokenException.DESCRIPTION),
        @ApiResponse(responseCode= InternalServerException.CODE, description = InternalServerException.DESCRIPTION),
    })
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto checkAccessToken(){
        return ResponseDto.success(null);
    }
    
//    @GetMapping("/token/valid")
//    @Operation(summary = "Access Token 유효성 검사", description = "로그인을 수해")
}
