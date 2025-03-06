package spring.community.img;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {
    /**
     * 이미지를 업로드하고 이미지 URL을 반환한다.
     *
     * @param file 업로드할 이미지 파일
     * @return 업로드된 이미지 URL
     */
    String uploadImage(MultipartFile file);
}
