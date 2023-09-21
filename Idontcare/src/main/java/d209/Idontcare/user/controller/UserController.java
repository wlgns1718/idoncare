package d209.Idontcare.user.controller;


import d209.Idontcare.common.APIBuilder;
import d209.Idontcare.common.annotation.LoginOnly;
import d209.Idontcare.common.dto.APIResultDto;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.user.dto.*;
import d209.Idontcare.user.entity.User;
import d209.Idontcare.user.service.OauthService;
import d209.Idontcare.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseDto login(@RequestBody KakaoDto kakao){
        String accessToken = oauthService.getOauthAccessToken(kakao.getCode());
        GetUserInfoDto userInfo = oauthService.getUserInfo(accessToken);
        
        return ResponseDto.success(userInfo);
    }

    @PostMapping(value = "/regist")
    @Operation(summary="회원가입", description = "카카오 유저 ID와 입력된 값들을 통해 회원가입")
    public ResponseDto regist(@RequestBody JoinUserReqDto req){
       userService.joinUser(req);
       return ResponseDto.success(null);
    }

    @GetMapping("")
    @Operation(summary = "내정보", description = "내 정보 보기")
    @LoginOnly(level = LoginOnly.Level.PARENT_OR_CHILD)
    public ResponseDto myInfo(HttpServletRequest request){
        Long userId = (Long)request.getAttribute("userId");
        User user = userService.findByUserId(userId).get();
        
        
        return ResponseDto.success(user);
    }
    
//    @GetMapping("/test")
//    @Operation(summary = "테스트")
//    public ResponseDto test(){
//
//        RequestDTO requestDto = new RequestDTO();
//
//        APIResultDto result = APIBuilder.build()
//            .url("http://localhost:8081" + "/openbanking/oauth/2.0/token")
//            .method(HttpMethod.POST)
//            .body(requestDto)
//            .execute();
//
//        return ResponseDto.success(result.getBody());
//    }
//
//
//    @Data
//    class RequestDTO {
//        private String phoneNumber;
//        private String birth;
//        private String mobileSort;
//        private String name;
//
//        public RequestDTO(){
//            this.phoneNumber = "01012345678";
//            this.birth = "19900101";
//            this.mobileSort = "SK";
//            this.name = "김엄마";
//        }
//    }
}
