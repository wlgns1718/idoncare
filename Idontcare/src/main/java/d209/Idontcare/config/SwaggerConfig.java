package d209.Idontcare.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.awt.*;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI openAPI() {
    return new OpenAPI()
        .components(getComponents())
        .addSecurityItem(getSecurity())
        .info(apiInfo());
  }
  
  private Info apiInfo() {
    return new Info()
        .title("아이돈케어 API")
        .description("아이돈케어 API")
        .version("1.0.0");
  }
  
  private Components getComponents(){
    Components components = new Components();
    components.addSecuritySchemes("Authorization",
        new SecurityScheme().name("Authorization")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT"));
    
    return components;
  }
  
  private SecurityRequirement getSecurity(){
    // API 요청헤더에 인증정보 포함
    SecurityRequirement securityRequirement = new SecurityRequirement()
        .addList("Authorization");
    return securityRequirement;
  }
}
