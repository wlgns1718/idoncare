package d209.Idontcare.user.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class KakaoUserInfoDto {
  private Long id;
  private LocalDateTime connectedAt;
  
  private Profile properties;
  
  private KakaoAccount kakaoAccount;
  
  public KakaoUserInfoDto(Map<String, Object> body){
    this.id = (Long)body.get("id");
    String time = (String)body.get("connected_at");
    this.connectedAt =  LocalDateTime.of(Integer.parseInt(time.substring(0, 4)),
                                         Integer.parseInt(time.substring(5, 7)),
                                         Integer.parseInt(time.substring(8, 10)),
                                         Integer.parseInt(time.substring(11, 13)),
                                         Integer.parseInt(time.substring(14, 15)));
    
    if(body.get("properties") != null){
      //properties가 제대로 주어지면
      Map<String, Object> properties = (Map<String,Object>)body.get("properties");
      Profile profile = new Profile();
      profile.setNickname((String)properties.get("nickname"));
      
      this.properties = profile;
    }
    
    Map<String, Object> accountMap = (Map<String, Object>)body.get("kakao_account");
    KakaoAccount account = new KakaoAccount();
    account.setProfileNicknameNeedsAgreement((Boolean)accountMap.get("profile_nickname_needs_agreement"));
    account.setHasEmail((Boolean)accountMap.get("has_email"));
    account.setEmailNeedsAgreement((Boolean)accountMap.get("email_needs_agreement"));
    account.setIsEmailValid((Boolean)accountMap.get("is_email_valid"));
    account.setIsEmailVerified((Boolean)accountMap.get("is_email_verified"));
    account.setEmail((String)accountMap.get("email"));
    
    this.kakaoAccount = account;
  }
  
  @Data @NoArgsConstructor
  public class Profile{
    private String nickname;
  }
  
  @Data @NoArgsConstructor
  public class KakaoAccount{
    private Boolean profileNicknameNeedsAgreement;
    
    private Boolean hasEmail;
    private Boolean emailNeedsAgreement;
    private Boolean isEmailValid;
    private Boolean isEmailVerified;
    
    private String email;
  }
}




