package spring.community.heart

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.community.post.Post
import spring.community.post.PostRepository

@Service
class HeartService(
    val heartRepository: HeartRepository,
    val postRepository: PostRepository,
) {
    @Transactional
    fun addLike(
        heartRequest: HeartRequest,
        userId: String,
    ): HeartDto {
        val post: Post = postRepository.findByIdOrNull(heartRequest.postId)
            ?: throw EntityNotFoundException("게시물이 존재하지 않습니다: $heartRequest.postId")

        /**
         * 이미 좋아요가 존재하는 경우
         */
        require(heartRepository.findByPostIdAndUserId(heartRequest.postId, userId) == null) {
            "이미 좋아요가 존재합니다"
        }

        val heart = Heart(
            postId = heartRequest.postId,
            userId = userId,
        )

        heartRepository.save(heart)
        post.increaseLike()
        val likeCount = postRepository.countById(heartRequest.postId)

        return HeartDto(heart.postId, heart.userId, likeCount)
    }

    @Transactional
    fun removelike(heartId: Long): HeartDto {
        val heart = heartRepository.findById(heartId)
            ?: throw EntityNotFoundException("좋아요가 존재하지 않습니다: $heartId")
        val postId = heart.get().postId
        val userId = heart.get().userId
        val post: Post = postRepository.findByIdOrNull(postId)
            ?: throw EntityNotFoundException("게시물이 존재하지 않습니다: $postId")
        heartRepository.deleteById(heartId)
        post.decreaseLike()
        val likeCount = postRepository.countById(postId)

        return HeartDto(postId, userId, likeCount)

        /**
        post.likeCount = maxOf(0L, post.likeCount - 1)  // likeCount가 음수가 되지 않도록 방어 코드 추가
        postRepository.save(post)
         **/

    }
}
