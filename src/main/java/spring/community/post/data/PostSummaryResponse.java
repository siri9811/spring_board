package spring.community.post.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import spring.community.post.Post;

@Data
public class PostSummaryResponse {
    @Schema(description = "게시글 ID")
    private Long id;

    @Schema(description = "게시글 제목")
    private String title;

    @Schema(description = "게시글 내용")
    private String content;

    private String imageUrl;

    static public PostSummaryResponse from(Post post) {
        PostSummaryResponse postResponse = new PostSummaryResponse();
        postResponse.setId(post.getId());
        postResponse.setTitle(post.getTitle());
        String content = post.getContent();
        // content의 길이에 따라 substring을 조절합니다.
        if (content.length() <= 25) {
            postResponse.setContent(content);
        } else {
            postResponse.setContent(content.substring(0, 25));
        }        postResponse.setImageUrl(post.getImageUrl());
        return postResponse;
    }
}
