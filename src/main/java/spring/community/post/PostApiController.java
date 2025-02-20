package spring.community.post;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class PostApiController {

  private final PostService postService;

  @GetMapping("/post")
  public ResponseEntity<List<PostDto>> index() {
    List<PostDto> dto = postService.getAllPosts();
    return ResponseEntity.ok().body(dto);
  }

  @GetMapping("/post/{postId}")
  public ResponseEntity<PostDto> show(@PathVariable Long postId) {
    PostDto dto = postService.getPost(postId);
    return ResponseEntity.status(HttpStatus.OK).body(dto);
  }

  @PostMapping("/post")
  public ResponseEntity<PostDto> save(@RequestBody PostDto dto) {
    PostDto createdDto = postService.createPost(dto);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
  }

  @PatchMapping("/post/{postId}")
  public ResponseEntity<PostDto> update(@PathVariable Long postId, @RequestBody PostDto dto) {
    PostDto updateDto = postService.updatePost(postId, dto);
    return ResponseEntity.status(HttpStatus.OK).body(updateDto);
  }

  @DeleteMapping("/post/{postId}")
  public ResponseEntity<PostDto> delete(@PathVariable Long postId) {
    postService.deletePost(postId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

}