package spring.community.post.data;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;
import spring.community.comment.Comment;
import spring.community.post.Post;
import spring.community.user.data.UserSummaryProfile;

@Data
public class PostResponse {
    @Schema(description = "게시글 ID")
    private Long id;

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 작성자")
    private UserSummaryProfile author;

    @Schema(description = "게시글 내용")
    private String content;

    private String imageUrl;

    private List<Comment> comments;

    static public PostResponse from(Post post, List<Comment> comments) {
        PostResponse postResponse = new PostResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        UserSummaryProfile userSummaryProfile = new UserSummaryProfile();
        userSummaryProfile.setUsername(post.getAuthor()); //
        postResponse.setAuthor(userSummaryProfile);
        postResponse.setContent(post.getContent());
        postResponse.setImageUrl(post.getImageUrl());

        postResponse.setComments(comments);
        return postResponse;
    }
}
