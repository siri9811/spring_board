package spring.community.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String title;

  @Column
  private String content;

  public Post(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void patch(PostDto dto) {
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }

  public static Post createPost(PostDto dto) {
    return new Post(dto.getTitle(), dto.getContent());
  }
}