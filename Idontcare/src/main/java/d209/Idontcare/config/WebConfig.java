package d209.Idontcare.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  
  @Override
  public void addCorsMappings(CorsRegistry registry){
    registry.addMapping("/**")
        .allowedOriginPatterns("http://localhost*", "https://localhost*", "http://j9d209.p.ssafy.io*", "https://j9d209.p.ssafy.io*")
        .exposedHeaders("Authorization")  //Access Token에 대해 접근할 수 있도록 변경
        .allowCredentials(true)           //쿠키 요청을 허용
        .allowedMethods(
            HttpMethod.GET.name(),
            HttpMethod.HEAD.name(),
            HttpMethod.POST.name(),
            HttpMethod.PUT.name(),
            HttpMethod.DELETE.name(),
            HttpMethod.OPTIONS.name(),
            HttpMethod.PATCH.name()
        );
  }
}
