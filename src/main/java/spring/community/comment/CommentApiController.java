package spring.community.comment;

import static spring.community.config.SwaggerConfig.BEARER_AUTH;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 댓글을 관리하는 controller
 * 파일명 : CommentApiController
 *
 * @author : hyunchul
 * @since : 2025-02-21
 */
@Tag(name = "CommentApiController", description = "댓글 관리 API 엔드포인트")
@RequiredArgsConstructor
@RestController
public class CommentApiController {

    private final CommentService commentService;

    /**
     * 댓글 단건 작성
     *
     * @param postId 작성할 댓글의 게시글 ID
     * @param dto    작성할 댓글의 DTO
     * @return 작성된 댓글의 DTO
     */
    @PostMapping("/post/{postId}/comments")
    @SecurityRequirement(name = BEARER_AUTH)
    @ApiResponse(responseCode = "200", description = "성공")
    @Operation(summary = "댓글 작성", description = "댓글을 작성합니다.")
    public ResponseEntity<CommentDto> create(
            @PathVariable("postId") Long postId,
            @RequestBody CommentDto dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        CommentDto createDto = commentService.createComment(postId, dto, userDetails.getUsername());
        return ResponseEntity.ok().body(createDto);
    }

    /**
     * 댓글 수정
     *
     * @param commentId 수정할 댓글의 ID
     * @param dto       수정할 댓글의 DTO
     * @return 수정된 댓글의 DTO
     */
    @PatchMapping("/comments/{commentId}")
    @SecurityRequirement(name = BEARER_AUTH)
    @ApiResponse(responseCode = "200", description = "성공")
    @Operation(summary = "댓글 수정", description = "댓글을 수정합니다.")
    public ResponseEntity<CommentDto> update(
        @PathVariable("commentId") Long commentId,
        @RequestBody CommentDto dto,
        @AuthenticationPrincipal UserDetails userDetails)
    {
        CommentDto updateDto = commentService.updateComment(commentId, dto.getContent());
        return ResponseEntity.ok().body(updateDto);
    }

    /**
     * 댓글 삭제
     *
     * @param commentId 삭제할 댓글의 ID
     * @return HTTP 204(No content) 응답
     */
    @DeleteMapping("/comments/{commentId}")
    @SecurityRequirement(name = BEARER_AUTH)
    @ApiResponse(responseCode = "204", description = "성공")
    @Operation(summary = "댓글 삭제", description = "댓글을 삭제합니다.")
    public ResponseEntity<Void> delete(
        @PathVariable("commentId") Long commentId,
        @AuthenticationPrincipal UserDetails userDetails) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }


}
