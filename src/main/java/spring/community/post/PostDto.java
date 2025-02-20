package spring.community.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDto {

  private Long id;
  private String title;
  private String content;

  public static PostDto createPostDto(Post post) {
    return new PostDto(
        post.getId(),
        post.getTitle(),
        post.getContent()
    );
  }

}

