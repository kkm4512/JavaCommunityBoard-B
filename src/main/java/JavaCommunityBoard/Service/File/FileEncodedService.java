package JavaCommunityBoard.Service.File;

import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
public class FileEncodedService {

    public String encodedFile(String fileName){
        return URLEncoder.encode(fileName, StandardCharsets.UTF_8).replace("+","%20");
    }
}
