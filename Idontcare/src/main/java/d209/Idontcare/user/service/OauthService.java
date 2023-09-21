package d209.Idontcare.user.service;


import d209.Idontcare.common.exception.BadRequestException;
import d209.Idontcare.user.dto.GetUserInfoDto;
import org.springframework.stereotype.Service;

@Service
public interface OauthService {
    String getOauthAccessToken(String code)
        throws BadRequestException;
    
    GetUserInfoDto getUserInfo(String accessToken)
        throws BadRequestException;
}
