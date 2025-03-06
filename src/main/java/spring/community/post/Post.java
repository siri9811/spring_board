package spring.community.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.Instant;

import kotlin.jvm.JvmField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import spring.community.heart.Heart;
import spring.community.post.data.PostForm;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
/**
 *  게시글 엔티티
 */
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @Column
    private String imageUrl;

    /**
     * 게시글 작성자 (User ID인 이메일만 저장)
     */
    @Column
    private String author;

    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    /**
     * 좋아요 수
     */
    @Column
    private Long likeCount;

    public static Post createPost(PostForm dto, String imageUrl, String author) {
        var now = Instant.now();
        return Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .imageUrl(imageUrl)
                .author(author)
                .likeCount(0L)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }

    public void patch(PostForm dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.updatedAt = Instant.now();
    }

    public void increaseLike() {
        this.likeCount++;
    }

    public void decreaseLike() {
        this.likeCount--;
    }
}

// DTO

// Entity = DB 테이블과 매핑되는 객체
// PostData = 포스트 데이터 (프록시 객체 포함)
// PostSummaryData = 포스트 요약 데이터 (하위 프록싱 된 객체 미포함)

// Response 객체
// 게시글 리스트에서 보일 데이터 (제목, 내용(25자), 댓글 개수)
// 게시글 상세보기에서 보일 데이터 (제목, 내용, 이미지 여러개, 댓글)
