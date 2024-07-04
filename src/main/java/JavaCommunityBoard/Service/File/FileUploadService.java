package JavaCommunityBoard.Service.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileUploadService {

    public String storeFile(MultipartFile file, String uploadDir) throws IOException {
        //폴더생성
        if (!file.isEmpty()) {
            Files.createDirectories(Paths.get(uploadDir));

            //유니크한 파일 이름 생성
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir,fileName);

            //fs에 파일 저장
            Files.copy(file.getInputStream(), filePath);

            return fileName;
        } else {
            String defaultUploadDir = "C:\\Users\\nayou\\OneDrive\\Desktop\\every\\Coding\\JavaSpringProjects\\JavaComunityBoard-B\\imageDatas\\defaultProfile";
            Files.createDirectories(Paths.get(defaultUploadDir));

            //유니크한 파일 이름 생성
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(defaultUploadDir,fileName);

            //fs에 파일 저장
            Files.copy(file.getInputStream(), filePath);

            return fileName;
        }

    }

    //얘가 하는역할은 뭐지 ?
    public Path loadFIle(String dir,String fileName){
        return Paths.get(dir,fileName);
    }
}
