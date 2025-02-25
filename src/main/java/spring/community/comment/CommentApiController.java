package spring.community.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * 댓글을 관리하는 controller
 * 파일명 : CommentApiController
 * @author : hyunchul
 * @since : 2025-02-21
 */
@Tag(name = "CommentApiController", description = "댓글 관리 API 엔드포인트")
@RequiredArgsConstructor
@RestController
public class CommentApiController {

  private final commentService commentService;

  /**
   *해당 게시글의 모든 댓글 조회
   * @param postId 조회할 댓글의 게시글 ID
   * @return 해당 게시글의 모든 댓글의 DTO 리스트
   */
  @GetMapping("/post/{postId}/comments")
  @Operation(summary = "해당 게시글의 모든 댓글 조회", description = "해당 게시글의 모든 댓글을 조회합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  public ResponseEntity<List<CommentDto>> show(@PathVariable("postId") Long postId) {
    List<CommentDto> dto = commentService.getAllComments(postId);
    return ResponseEntity.ok().body(dto);
  }

  /**
   * 댓글 단건 작성
   * @param postId 작성할 댓글의 게시글 ID
   * @param dto 작성할 댓글의 DTO
   * @return 작성된 댓글의 DTO
   */
  @PostMapping("/post/{postId}/comments")
  @Operation(summary = "댓글 작성", description = "댓글을 작성합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  public ResponseEntity<CommentDto> create(@PathVariable("postId") Long postId, @RequestBody CommentDto dto) {
    CommentDto createDto = commentService.createComment(postId, dto);
    return ResponseEntity.ok().body(createDto);
  }

  /**
   * 댓글 수정
   * @param commentId 수정할 댓글의 ID
   * @param dto 수정할 댓글의 DTO
   * @return 수정된 댓글의 DTO
   */
  @PatchMapping("/comments/{commentId}")
  @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
  @ApiResponse(responseCode = "200", description = "성공")
  public ResponseEntity<CommentDto> update(@PathVariable("commentId") Long commentId,@RequestBody CommentDto dto) {
    CommentDto updateDto = commentService.updateComment(commentId, dto);
    return ResponseEntity.ok().body(updateDto);
  }

  /**
   * 댓글 삭제
   * @param commentId 삭제할 댓글의 ID
   * @return HTTP 204(No content) 응답
   */
  @DeleteMapping("/comments/{commentId}")
  @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
  @ApiResponse(responseCode = "204", description = "성공")
  public ResponseEntity<Void> delete(@PathVariable("commentId") Long commentId) {
    commentService.deleteComment(commentId);
    return ResponseEntity.noContent().build();
  }


}
