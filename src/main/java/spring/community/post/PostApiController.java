package spring.community.post;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import spring.community.comment.CommentService;
import spring.community.post.data.PostForm;
import spring.community.post.data.PostResponse;
import spring.community.post.data.PostSummaryResponse;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;
import static spring.community.config.SwaggerConfig.BEARER_AUTH;


/**
 * 게시글을 관리하는 Controller
 * 파일명 : BoardApiController
 *
 * @author : hyunchul
 * @since : 2025-02-20
 */
@Tag(name = "게시글 관리 API", description = "게시글 관리 API 엔드포인트")
@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;
    private final CommentService commentService;

    /**
     * 모든 게시글 조회
     *
     * @return 모든 게시글의 DTO 리스트
     */
    @GetMapping("/post")
    @Operation(summary = "모든 게시글 조회", description = "모든 게시글을 조회합니다.")
    @ApiResponse(responseCode = "200", description = "성공",
            content = @Content(schema = @Schema(implementation = PostForm.class))
    )
    public ResponseEntity<Page<PostSummaryResponse>> index(
            @ModelAttribute Pageable pageable
    ) {
        return ResponseEntity.ok(postService.getAllPosts(pageable).map(
                PostSummaryResponse::from
        ));
    }

    /**
     * 게시글 단건 조회
     *
     * @param postId 조회할 게시글의 ID
     * @return 조회된 게시글의 DTO
     */
    @GetMapping("/post/{postId}")
    @Operation(summary = "게시글 단건 조회", description = "게시글 단건을 조회합니다.")
    public ResponseEntity<PostResponse> show(@PathVariable Long postId) {
        return ResponseEntity.ok(PostResponse.from(
                postService.getPost(postId),
                commentService.getCommentsByPostId(postId)
        ));
    }

    /**
     * 게시글 작성
     *
     * @param dto 작성할 게시글의 DTO
     * @return 생성된 게시글의 DTO
     */
    @Operation(summary = "게시글 작성", description = "게시글을 작성합니다.")
    @SecurityRequirement(name = BEARER_AUTH)
    @PostMapping(path = "/post", consumes = MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<PostResponse> createPost(
            @Valid @ModelAttribute PostForm dto,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Post post = postService.createPost(dto, userDetails.getUsername());
        return ResponseEntity.ok(PostResponse.from(
                post,
                commentService.getCommentsByPostId(post.getId())
        ));
    }

    /**
     * 게시글 수정
     *
     * @param postId 수정할 게시글의 ID
     * @param dto    수정할 게시글의 DTO
     * @return 수정된 게시글의 DTO
     */
    @Secured({"ROLE_USER"})
    @SecurityRequirement(name = BEARER_AUTH)
    @PatchMapping(path = "/post/{postId}", consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "하나의 게시글 수정", description = "하나의 게시글을 수정합니다.")
    public ResponseEntity<PostResponse> update(
            @PathVariable Long postId,
            @RequestBody @ModelAttribute PostForm dto,
            @AuthenticationPrincipal Principal principal
    ) {
        return ResponseEntity.ok(PostResponse.from(
                postService.updatePost(postId, dto),
                commentService.getCommentsByPostId(postId)
        ));
    }

    /**
     * 게시글 삭제
     *
     * @param postId 삭제할 게시글의 ID
     * @return HTTP 204(No content) 응답
     */
    @DeleteMapping("/post/{postId}")
    @Operation(summary = "하나의 게시글 삭제", description = "하나의 게시글을 삭제합니다.")
    public ResponseEntity<Void> delete(
            @PathVariable Long postId,
            @AuthenticationPrincipal Principal principal
    ) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }

}
