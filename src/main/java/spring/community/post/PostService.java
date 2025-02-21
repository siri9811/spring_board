package spring.community.post;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public List<PostDto> getAllPosts() {
    return postRepository.findAll()
        .stream()
        .map(PostDto::createPostDto)
        .collect(Collectors.toList());
  }


  public PostDto getPost(Long postId) {
    return postRepository.findById(postId)
        .map(PostDto::createPostDto)
        .orElseThrow(()-> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다 postId : " + postId));
  }

  public PostDto createPost(PostDto dto) {
    Post post = Post.createPost(dto);
    Post created = postRepository.save(post);
    return PostDto.createPostDto(created);
  }

  public PostDto updatePost(Long postId, PostDto dto) {
    Post target = postRepository.findById(postId)
        .orElseThrow(() -> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다 postId : " + postId));

    target.patch(dto);

    Post updated = postRepository.save(target);

    return PostDto.createPostDto(updated);

  }

  public void deletePost(Long postId) {
    Post target = postRepository.findById(postId)
        .orElseThrow(()-> new EntityNotFoundException("해당 게시글을 찾을 수 없습니다 postId : " + postId));
    postRepository.delete(target);
  }
}