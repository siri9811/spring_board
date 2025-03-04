package spring.community.heart

import jakarta.persistence.*

/**
 * 좋아요 엔티티
 */
@Entity
data class Heart (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column
    val postId: Long,

    @Column
    val userId: String,


)