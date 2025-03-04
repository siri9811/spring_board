package spring.community.heart

import org.springframework.data.jpa.repository.JpaRepository

interface HeartRepository : JpaRepository<Heart, Long> {
    fun findByPostIdAndUserId(postId: Long, userId: String): Heart?
}