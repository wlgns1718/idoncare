package d209.Idontcare.user.service;

import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public interface UserService {

    public Map<String,Object> login(Long userId, String password) throws Exception;



}
