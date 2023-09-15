package d209.Idontcare.user.controller;


import ch.qos.logback.core.encoder.EchoEncoder;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.user.dto.KakaoDto;
import d209.Idontcare.user.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final OauthService oauthService;

    @PostMapping(value = "/login/kakao")
    public ResponseDto login(@RequestBody KakaoDto kakao){
        ResponseDto responseDto = new ResponseDto(200,HttpStatus.ACCEPTED);
        try {
            oauthService.getOauthAccessToken(kakao.getCode());

        } catch (Exception e) {
            e.getMessage();
        }
        return responseDto;
    }

}
