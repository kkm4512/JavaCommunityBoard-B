package JavaCommunityBoard.Service.File;

import JavaCommunityBoard.Paths.PathConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileDownloadService {

    private final FileEncodedService fileEncodedService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String loadFileAsResource(String fileName) {

        //파일 경로 설정 -> 파일 경로 정규화
        Path filePath = Paths.get(uploadDir).resolve(fileName).normalize();


        //파일 경로 UrlResource 객체 생성
        UrlResource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        String resourceGetFileName = resource.getFilename();
        String encodedFileName =  fileEncodedService.encodedFile(resourceGetFileName);
        String getProfileMemberUrl = PathConstants.MEMBER_PROFILE_URL + encodedFileName;

        //리소스가 존재하면 반환
        if (resource.exists()) return getProfileMemberUrl;
        else throw new RuntimeException("File Is Not Found");

    }
}
