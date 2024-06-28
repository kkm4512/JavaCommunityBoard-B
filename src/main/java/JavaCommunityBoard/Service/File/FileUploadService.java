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

    @Value("${file.upload-dir}")
    private String uploadDir;

    public String storeFile(MultipartFile file) throws IOException {

        //폴더생성
        Files.createDirectories(Paths.get(uploadDir));

        //유니크한 파일 이름 생성
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir,fileName);

        //fs에 파일 저장
        Files.copy(file.getInputStream(), filePath);

        return fileName;
    }

    public Path loadFIle(String fileName){
        return Paths.get(uploadDir,fileName);
    }
}
