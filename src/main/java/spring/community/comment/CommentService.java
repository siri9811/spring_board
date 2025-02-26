package spring.community.comment;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.community.post.Post;
import spring.community.post.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository boardRepository;

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }


    public CommentDto createComment(Long postId, CommentDto dto, String userId) {
        Post post = boardRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("게시물이 존재하지 않습니다: " + postId));

        Comment comment = Comment.createComment(post, dto, userId);
        Comment create = commentRepository.save(comment);


        return CommentDto.createDto(create);
    }


    public CommentDto updateComment(Long commentId, String dto) {
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 존재하지 않습니다: " + commentId));

        target.patch(dto);
        Comment update = commentRepository.save(target);

        return CommentDto.createDto(update);
    }

    public void deleteComment(Long commentId) {
        Comment target = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("댓글이 존재하지 않습니다: " + commentId));

        commentRepository.delete(target);
    }


}
