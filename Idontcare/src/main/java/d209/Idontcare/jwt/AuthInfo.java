package d209.Idontcare.jwt;

import d209.Idontcare.user.entity.Role;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data @ToString
public class AuthInfo {
  private Long userId;
  private Role role;
}
