package JavaCommunityBoard.Entity.Board;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shares")
public class ShareEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;
    private boolean shared;
    private Long loginMemberId;

    @Override
    public String toString() {
        return "ShareEntity{" +
                "id=" + id +
                ", boardEntity=" + boardEntity +
                ", shared=" + shared +
                ", loginMemberId=" + loginMemberId +
                '}';
    }
}
