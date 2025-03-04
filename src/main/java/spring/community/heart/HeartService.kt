package spring.community.heart

import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import spring.community.post.PostRepository

@Service
open class HeartService (
    val heartRepository: HeartRepository,
    val postRepository: PostRepository
) {
    @Transactional
    open fun addlike(heartRequest: HeartRequest, userId: String): Any? {
        val post = postRepository.findById(heartRequest.postId)
            ?: throw EntityNotFoundException("게시물이 존재하지 않습니다: $heartRequest.postId")


        /**
         * 이미 좋아요가 존재하는 경우
         */
        if(heartRepository.findByPostIdAndUserId(heartRequest.postId, userId) != null) {
            throw Exception("이미 좋아요가 존재합니다");
        }

        val heart = Heart(postId = heartRequest.postId, userId = userId)
        heartRepository.save(heart)

//        post.likeCount += 1
//        postRepository.save(post)


        return heart

    }

    @Transactional
    open fun removelike(heartId: Long) {
        heartRepository.findById(heartId)
            ?: throw EntityNotFoundException("좋아요가 존재하지 않습니다: $heartId")
        heartRepository.deleteById(heartId)

        /**
        post.likeCount = maxOf(0L, post.likeCount - 1)  // likeCount가 음수가 되지 않도록 방어 코드 추가
        postRepository.save(post)
         **/

    }
}
