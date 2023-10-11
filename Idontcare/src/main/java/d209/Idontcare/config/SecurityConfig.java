package d209.Idontcare.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import d209.Idontcare.common.dto.ResponseDto;
import d209.Idontcare.common.exception.CommonException;
import d209.Idontcare.jwt.JwtSecurityConfig;
import d209.Idontcare.jwt.JwtTokenProvider;
import d209.Idontcare.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${springsecurity.secret.key}")
    private String secret;
    @Value("${springsecurity.secret.salt}")
    private String salt;
    @Value("${jwt.refresh-expiration-time}")
    private Long refreshExpirationTime;
  
  
  @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      //쿠키를 사용할 수 있도록 CORS 관련 설정을 해주자
      CorsConfiguration config = new CorsConfiguration();
      config.setExposedHeaders(Arrays.asList("Authorization"));
      config.addAllowedOriginPattern("*");
      config.addAllowedHeader("*");
      config.addAllowedMethod("*");
      config.setAllowCredentials(true);
      
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", config);
     
      http.cors().configurationSource(source);
      
      http.csrf().disable()
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults()) // 기본 인증 로그인 사용하지 않음. (rest api)
        
        .sessionManagement((session) -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .requestMatchers(matchers -> matchers
            .antMatchers("/**")
        )

        .authorizeHttpRequests(authorize -> authorize
            .requestMatchers(new AntPathRequestMatcher("**")).permitAll()
            .anyRequest().authenticated()
        );

    http.apply(new JwtSecurityConfig(jwtTokenProvider, refreshExpirationTime));
    return http.build();
    }

    @Bean
    public AesBytesEncryptor aesBytesEncryptor() {
      return new AesBytesEncryptor(secret, "70726574657374");
    }
}
