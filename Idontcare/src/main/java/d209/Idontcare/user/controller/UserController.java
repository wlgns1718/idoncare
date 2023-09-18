package d209.Idontcare.user.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.user.dto.KakaoDto;
import d209.Idontcare.user.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final OauthService oauthService;

    @PostMapping(value = "/login")
    public ResponseDto<?> login(@RequestBody KakaoDto kakao){
        try {
<<<<<<< HEAD
            String accessToken = oauthService.getOauthAccessToken(kakao.getCode());
            Map<String,Object> userInfo = oauthService.getUserInfo(accessToken);


=======
            oauthService.getOauthAccessToken(kakao.getCode());
>>>>>>> 9e6dbb2055c9b578f544f52991528b34de404334
        }catch (Exception e){
            e.getMessage();
        }
        return ResponseDto.success("SUCCESS");
    }

}
