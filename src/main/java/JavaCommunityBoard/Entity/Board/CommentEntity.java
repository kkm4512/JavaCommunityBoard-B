package JavaCommunityBoard.Entity.Board;

import JavaCommunityBoard.Entity.MemberEntity;
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
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id",nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne
    @JoinColumn(name = "board_id",nullable = false)
    private BoardEntity boardEntity;

    @Column
    private String comment;

}
