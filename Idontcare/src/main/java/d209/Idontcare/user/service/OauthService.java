package d209.Idontcare.user.service;


import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.user.dto.GetUserInfoDto;
import d209.Idontcare.user.dto.req.LoginReqDto;
import org.springframework.stereotype.Service;

@Service
public interface OauthService {
    String getOauthAccessToken(LoginReqDto code)
        throws BadRequestException;
    
    GetUserInfoDto getUserInfo(String accessToken)
        throws BadRequestException;
    
    GetUserInfoDto getUserInfoTest(Long kakaoId);
}
