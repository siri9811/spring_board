package spring.community.Img;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface ImageUploadApiAdapter {
  ImageResponseDto uploadImage(MultipartFile file);
}
