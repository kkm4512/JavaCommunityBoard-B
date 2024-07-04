package JavaCommunityBoard.Service.File;

import JavaCommunityBoard.Paths.PathConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileDownloadService {

    private final FileEncodedService fileEncodedService;

    //fs에 접근 할 수있는 url 생성
    public String loadFileAsResource(String fileName,String downloadDIr) {

        //파일 경로 설정 -> 파일 경로 정규화
        Path filePath = Paths.get(downloadDIr).resolve(fileName).normalize();

        //맨끝에 위치한 디렉토리명을 가져옴
        String dir = "/uploads/" + Paths.get(downloadDIr).getFileName().toString() + "/";

        //파일 경로 UrlResource 객체 생성
        UrlResource resource = null;
        try {
            //실제 디렉토리에 그 파일들이 있는지 확인함
            //정확한 경로를 적어야함
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"File Not Found");
        }

        //경로생성
        String resourceGetFileName = resource.getFilename();
        String encodedFileName =  fileEncodedService.encodedFile(resourceGetFileName);
        String getProfileMemberUrl = PathConstants.DOMAIN_URL+ dir + encodedFileName;

        //리소스가 존재하면 반환
        if (resource.exists()) return getProfileMemberUrl;
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND,"File Not Found");

    }
}
