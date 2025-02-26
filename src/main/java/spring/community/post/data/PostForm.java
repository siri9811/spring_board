package spring.community.post.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


/**
 * 게시글 작성/수정 폼
 *
 * @author : hyunchul
 * @author : cmsong111 (Namju Kim)
 * @since : 2025-02-21
 */
@AllArgsConstructor
@Getter
@Setter
@Schema(description = "게시글 관리 DTO")
public class PostForm {
    @Schema(description = "게시글 제목")
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @Schema(description = "게시글 내용")
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    private MultipartFile image;
}

