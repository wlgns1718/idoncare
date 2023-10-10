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
        .allowedOriginPatterns("*")       //credentials를 allow로 하면 patterns를 대신 사용 해야 정상 작동한다
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
