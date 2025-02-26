package spring.community.comment;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    /**
     * 게시글에 해당하는 댓글을 조회합니다.
     *
     * @param postId 게시글 id
     * @return 댓글 목록
     */
    List<Comment> findByPostId(Long postId);

    /**
     * 유저가 작성한 댓글을을 조회합니다.
     *
     * @param authorId 작성자 id
     * @return 댓글 목록
     */
    List<Comment> findByAuthorId(Long authorId);
}
