package spring.community.heart

import jakarta.persistence.*
import java.time.Instant

/**
 * 좋아요 엔티티
 */
@Entity
class Heart(
    id: Long = 0L,
    postId: Long,
    userId: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = id

    @Column
    val postId: Long = postId

    @Column
    val userId: String = userId


    val createdAt: Instant = Instant.now()

    var updatedAt: Instant = Instant.now()
        protected set
}