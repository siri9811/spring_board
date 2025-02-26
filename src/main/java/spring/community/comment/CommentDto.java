package spring.community.comment;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Schema(description = "댓글 관리 DTO")
public class CommentDto {


    @Schema(description = "댓글 내용")
    @NotBlank(message = "내용을 입력해주세요")
    private String content;

    public static CommentDto createDto(Comment comment) {

        return new CommentDto(
                comment.getContent()
        );
    }
}

