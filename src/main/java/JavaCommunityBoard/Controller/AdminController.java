package JavaCommunityBoard.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping
@CrossOrigin
public class AdminController {

    @GetMapping("admin")
    public String sayAdmin(){
        return "adminPage";
    }
}
