package spring.community.Img;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class Imagecontroller {
  private final ImageUploadApiAdapter imageUploadApiAdapter;;

  @PostMapping("/images")
  public ResponseEntity postImage(@RequestParam MultipartFile file) {
    ImageResponseDto imageResponseDto = imageUploadApiAdapter.uploadImage(file);
    return ResponseEntity.ok(imageResponseDto);
  }
}
