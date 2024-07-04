package JavaCommunityBoard.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class MemberDTO {

    private Long id;

    @NotBlank(message = "[DTO] 이름은 공백 일 수 없습니다")
    @Size(min = 1,max = 10,message = "[DTO] 이름은 최소 1글자, 최대 10글자 이하입니다")
    private String name;

    @NotBlank(message = "[DTO] 비밀번호는 공백 일 수 없습니다")
    @Size(min = 1,max = 10,message = "[DTO] 비밀번호는 최소 1글자, 최대 10글자 이하입니다")
    private String password;

    @NotBlank(message = "[DTO] 닉네임은 공백 일 수 없습니다")
    @Size(min = 5,max = 10,message = "[DTO] 닉네임은 최소 5글자, 최대 10글자 이하입니다")
    private String nickname;

    @NotBlank(message = "[DTO] 권한은 공백 일 수 없습니다")
    private String role;

    private String multipartFilePath;

    @Override
    public String toString() {
        return "MemberDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role='" + role + '\'' +
                ", multipartFilePath=" + multipartFilePath +
                '}';
    }
}
