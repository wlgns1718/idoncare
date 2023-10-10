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
//        .allowedOrigins(  //origin에 대해 직접 지정할 경우
//            "http://localhost:3000",
//            "https://localhost:3000",
//            "http://localhost:5173",
//            "https://localhost:5173",
//            "http://localhost:8080",
//            "http://localhost:8081",
//
//            "http://j9d209.p.ssafy.io:8081",
//            "http://j9d209.p.ssafy.io:8082",
//            "https://j9d209.p.ssafy.io:9081",
//            "https://j9d209.p.ssafy.io:9082",
//            "http://j9d209.p.ssafy.io:10000",
//            "https://j9d209.p.ssafy.io"
//        )
        .allowedOriginPatterns("*")
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
