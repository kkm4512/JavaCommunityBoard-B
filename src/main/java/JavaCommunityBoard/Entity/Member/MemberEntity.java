package JavaCommunityBoard.Entity.Member;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Entity(name = "Member")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String role;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column
    private String multipartFilePath;



    public MemberEntity() {}

    //회원가입 데이터 확인용
    public MemberEntity(String name, String password, String nickname, String role){
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        signUpValidate();
    }

    //회원가입 데이터 확인용
    public MemberEntity(String name, String password){
        this.name = name;
        this.password = password;
        signInValidate();
    }

    //회원가입 데이터 확인 로직
    private void signUpValidate() {
        //null 확인
        if (this.name.isEmpty()) throw new IllegalStateException("[Entity] 이름은 공백 일 수 없습니다");
        if (this.password.isEmpty()) throw new IllegalStateException("[Entity] 비밀번호는 공백 일 수 없습니다");
        if (this.nickname.isEmpty()) throw new IllegalStateException("[Entity] 닉네임은 공백 일 수 없습니다");
        if (this.role.isEmpty()) throw new IllegalStateException("[Entity] 권한은 공백 일 수 없습니다");

        //글자수 확인
        if (this.name.length() > 10) throw new IllegalStateException("[Entity] 이름은 최소 1글자, 최대 10글자 이하입니다");
        if (this.nickname.length() > 10) throw new IllegalStateException("[Entity] 닉네임은 최소 1글자, 최대 10글자 이하입니다");
    }

    //로그인 데이터 확인 로직
    private void signInValidate() {
        //null 확인
        if (this.name.isEmpty()) throw new IllegalStateException("[Entity] 이름은 공백 일 수 없습니다");
        if (this.password.isEmpty()) throw new IllegalStateException("[Entity] 비밀번호는 공백 일 수 없습니다");

        //글자수 확인
        if (this.name.length() > 10) throw new IllegalStateException("[Entity] 이름은 최소 1글자, 최대 10글자 이하입니다");
        if (this.password.length() > 10) throw new IllegalStateException("[Entity] 비밀번호는 최소 1글자, 최대 10글자 이하입니다");
    }

    @Override
    public String toString() {
        return "MemberEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
