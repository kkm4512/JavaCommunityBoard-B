package JavaCommunityBoard.Config;

import jakarta.servlet.MultipartConfigElement;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;

public class FileUploadConfig {

    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize(DataSize.ofMegabytes(10)); //파일크기 제한
        factory.setMaxRequestSize(DataSize.ofMegabytes(10)); //요청 크기 제한
        return factory.createMultipartConfig();
    }
}
