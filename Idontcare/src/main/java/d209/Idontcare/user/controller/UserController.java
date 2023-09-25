package d209.Idontcare.user.controller;


import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.*;
import d209.Idontcare.jwt.AccessRefreshTokenDto;
import d209.Idontcare.jwt.JwtTokenProvider;
import d209.Idontcare.user.dto.*;
import d209.Idontcare.user.dto.req.LoginReqDto;
import d209.Idontcare.user.dto.req.RefreshReqDto;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Tag(name="유저 API")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {
    
    private final OauthService oauthService;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    
    @PostMapping(value = "/login")
    @Operation(summary="로그인", description = "카카오 코드를 통해 로그인을 요청합니다")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = GetUserInfoDto.class))),
        @ApiResponse(responseCode= BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
    })
    public ResponseDto login(@RequestBody LoginReqDto req){
        String accessToken = oauthService.getOauthAccessToken(req);
        GetUserInfoDto userInfo = oauthService.getUserInfo(accessToken);
        
        return ResponseDto.success(userInfo);
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
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = UserInfoResDto.class))),
        @ApiResponse(responseCode= AuthenticationException.CODE, description = AuthenticationException.DESCRIPTION),
    })
    public ResponseDto myInfo(HttpServletRequest request){
        Long userId = (Long)request.getAttribute("userId");
        User user = userService.findByUserId(userId).get();
        return ResponseDto.success(new UserInfoResDto(user));
    }
    
    @PostMapping("/login/{kakaoId}")
    @Operation(summary = "테스트 로그인", description = "코드를 통해 로그인, [1 ~ 9]: 부모, [11 ~ 19] : 자식")
    @ApiResponses(value = {
        @ApiResponse(responseCode="200", description = "성공",
            content=@Content(schema = @Schema(implementation = GetUserInfoDto.class)))
    })
    public ResponseDto loginTest(@PathVariable("kakaoId") Long kakaoId){
        GetUserInfoDto userInfo = oauthService.getUserInfoTest(kakaoId);
        
        return ResponseDto.success(userInfo);
    }
    
    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급", description = "리프레시 토큰을 사용하여 액세스토큰, 리프레시 토큰 재발급")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공",
            content=@Content(schema = @Schema(implementation = AccessRefreshTokenDto.class))),
        @ApiResponse(responseCode= BadRequestException.CODE, description = BadRequestException.DESCRIPTION),
    })
    public ResponseDto refresh(@Valid @RequestBody RefreshReqDto req){
        AccessRefreshTokenDto result = jwtTokenProvider.refresh(req.getRefreshToken());
        
        return ResponseDto.success(result);
    }
}
