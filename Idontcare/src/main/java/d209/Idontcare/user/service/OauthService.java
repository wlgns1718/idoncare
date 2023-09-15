package d209.Idontcare.user.service;


import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public interface OauthService {
    public void getOauthAccessToken(String code) throws IOException, NullPointerException, Exception;

    public Map<String, String> getUserInfo(String accessToken) throws Exception;
}
