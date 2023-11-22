package d209.Idontcare.jwt;

import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
  
  private JwtTokenProvider jwtTokenProvider;
  private Long refreshExpirationTime;
  
  public JwtSecurityConfig(JwtTokenProvider jwtTokenProvider,
                           Long refreshExpirationTime){
    this.jwtTokenProvider = jwtTokenProvider;
    this.refreshExpirationTime = refreshExpirationTime;
  }
  
  @Override
  public void configure(HttpSecurity http){
    JwtFilter jwtFilter = new JwtFilter(jwtTokenProvider, refreshExpirationTime);
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }
}
