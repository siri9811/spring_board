package spring.community.post;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


/**
 * 파일명 : PostDto
 * @author : hyunchul
 * @since : 2025-02-21
 */
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "게시글 관리 DTO")
public class PostDto {

  @Schema(description = "게시글 ID")
  private Long id;

  @Schema(description = "게시글 제목")
  @NotBlank(message = "제목을 입력해주세요.")
  private String title;

  @Schema(description = "게시글 내용")
  @NotBlank(message = "내용을 입력해주세요")
  private String content;

  public static PostDto createPostDto(Post post) {
    return new PostDto(
        post.getId(),
        post.getTitle(),
        post.getContent()
    );
  }

}

