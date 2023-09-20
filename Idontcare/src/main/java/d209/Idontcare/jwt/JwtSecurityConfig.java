package d209.Idontcare.jwt;

import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  
  private final JwtTokenProvider jwtTokenProvider;
  private final UserRepository userRepository;
  
  @Override
  public void configure(HttpSecurity http) throws Exception{
    JwtFilter jwtFilter = new JwtFilter(jwtTokenProvider, userRepository);
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
