package spring.community.heart

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.ResponseEntity
import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import spring.community.config.SwaggerConfig
import spring.community.jwt.AuthenticatedUser

/**
 * 좋아요 관리 컨트롤러
 */
@RestController
@RequestMapping("/like")
class HeartController(
    private val heartService: HeartService,
) {

    /**
     * @param heartRequest 하트 DTO
     * @param authenticatedUser 유저 정보
     * @return 하트 정보
     */
    @PostMapping
    @Secured("ROLE_USER")
    @SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
    @Operation(summary = "게시글에 좋아요 추가", description = "특정 게시글에 좋아요를 추가합니다.")

    fun addLike(
        @RequestBody heartRequest: HeartRequest,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser
    ):
            ResponseEntity<Any> {
        val userId = authenticatedUser.email
        val result = heartService.addLike(heartRequest, userId)
        return ResponseEntity.ok(result)
    }

    /**
     * @param heartId 하트 DTO
     * @param authenticatedUser 유저 정보
     * @return HTTP 204(No content) 응답
     */
    @DeleteMapping("/{heartId}")
    @Secured("ROLE_USER")
    @SecurityRequirement(name = SwaggerConfig.BEARER_AUTH)
    @Operation(summary = "게시글에 좋아요 삭제", description = "특정 게시글에 좋아요를 삭제합니다.")
    fun deleteLike(
        @PathVariable heartId: Long,
        @AuthenticationPrincipal authenticatedUser: AuthenticatedUser
    ): ResponseEntity<Any> {

        val heart: Heart = Heart(
            postId = 1L,
            userId = authenticatedUser.email
        )

        val result = heartService.removelike(heartId)
        return ResponseEntity.ok(result)


    }

}
