package JavaCommunityBoard.Controller.AccessToken;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("accessToken")
@CrossOrigin
@RequiredArgsConstructor
public class AccessTokenController {

    @GetMapping("verify")
    public boolean isVerifyAccessToken() {
        return true;
    }


}
