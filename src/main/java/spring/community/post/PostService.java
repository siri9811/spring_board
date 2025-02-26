package spring.community.post;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.community.Img.ImageStorage;
import spring.community.post.data.PostForm;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ImageStorage imageStorage;

    public Page<Post> getAllPosts(
            Pageable pageable
    ) {
        return postRepository.findAll(pageable);
    }


    public Post getPost(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다 postId : " + postId));
    }

    @Transactional
    public Post createPost(PostForm dto, String userId) {
        String imageUrl = null;
        if (dto.getImage() != null) {
            imageUrl = imageStorage.uploadImage(dto.getImage());
        }

        return postRepository.save(
                Post.createPost(
                        dto,
                        imageUrl
                )
        );
    }

    @Transactional
    public Post updatePost(Long postId, PostForm dto) {
        Post target = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다 postId : " + postId));

        target.patch(dto);

        if (dto.getImage() != null) {
            target.setImageUrl(imageStorage.uploadImage(dto.getImage()));
        }

        return target;
    }

    public void deletePost(Long postId) {
        Post target = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다 postId : " + postId));
        postRepository.delete(target);
    }
}
