package spring.community.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 게시글을 관리하는 Controller
 * 파일명 : BoardApiController
 * @author : hyunchul
 * @since : 2025-02-20
 */
@Tag(name = "게시글 관리 API", description = "게시글 관리 API 엔드포인트")
@RestController
@RequiredArgsConstructor
public class PostApiController {

  private final PostService postService;

  /**
   * 모든 게시글 조회
   * @return 모든 게시글의 DTO 리스트
   */
  @GetMapping("/post")
  @Operation(summary = "모든 게시글 조회", description = "모든 게시글을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "성공",
      content = @Content(schema = @Schema(implementation = PostDto.class))
  )
  public ResponseEntity<List<PostDto>> index() {
    List<PostDto> dto = postService.getAllPosts();
    return ResponseEntity.ok(dto);
  }

  /**
   * 게시글 단건 조회
   * @param postId 조회할 게시글의 ID
   * @return 조회된 게시글의 DTO
   */
  @GetMapping("/post/{postId}")
  @Operation(summary = "게시글 단건 조회", description = "게시글 단건을 조회합니다.")
  public ResponseEntity<PostDto> show(@PathVariable Long postId) {
    PostDto dto = postService.getPost(postId);
    return ResponseEntity.ok(dto);
  }

  /**
   * 게시글 작성
   * @param dto 작성할 게시글의 DTO
   * @return 생성된 게시글의 DTO
   */
  @PostMapping("/post")
  @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
  public ResponseEntity<PostDto> save(@Valid @RequestBody PostDto dto) {
    PostDto createdDto = postService.createPost(dto);
    return ResponseEntity.ok(createdDto);
  }

  /**
   * 게시글 수정
   * @param postId 수정할 게시글의 ID
   * @param dto 수정할 게시글의 DTO
   * @return 수정된 게시글의 DTO
   */
  @PatchMapping("/post/{postId}")
  @Operation(summary = "하나의 게시글 수정", description = "하나의 게시글을 수정합니다.")
  public ResponseEntity<PostDto> update(@PathVariable Long postId, @RequestBody PostDto dto) {
    PostDto updateDto = postService.updatePost(postId, dto);
    return ResponseEntity.ok(updateDto);
  }

  /**
   * 게시글 삭제
   * @param postId 삭제할 게시글의 ID
   * @return HTTP 204(No content) 응답
   */
  @DeleteMapping("/post/{postId}")
  @Operation(summary = "하나의 게시글 삭제", description = "하나의 게시글을 삭제합니다.")
  public ResponseEntity<Void> delete(@PathVariable Long postId) {
    postService.deletePost(postId);
    return ResponseEntity.noContent().build();
  }

}