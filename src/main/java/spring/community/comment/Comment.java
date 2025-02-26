package spring.community.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long postId;

    @Column
    private String content;

    @Column
    private String authorId;

    public static Comment createComment(Long postId, String content, String authorId) {
        return new Comment(
                null,
                postId,
                content,
                authorId
        );
    }

    public void patch(String content) {
        this.content = content;
    }
}
