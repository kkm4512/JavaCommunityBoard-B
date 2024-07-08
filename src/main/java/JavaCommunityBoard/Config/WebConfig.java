package JavaCommunityBoard.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000") // 클라이언트의 도메인 주소
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //해당 경로로 프론트에서 매핑 시도가오면
        registry.addResourceHandler("/uploads/**")
                //하단의 경로로 매핑이 된다
                .addResourceLocations("file:///C:/Users/nayou/Desktop/every/Coding/JavaSpringProjects/JavaComunityBoard-B/uploads/");
    }

}
