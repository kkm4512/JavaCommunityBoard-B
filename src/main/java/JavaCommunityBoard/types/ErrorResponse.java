package JavaCommunityBoard.types;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class ErrorResponse {
    private int status;
    private Map<String,String> errors;

    public ErrorResponse(int status, Map<String, String> errors) {
        this.status = status;
        this.errors = errors;
    }


}
