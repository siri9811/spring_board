package spring.community.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spring.community.exception.DomainException.ForbiddenException;
import spring.community.exception.DomainException.InvalidTokenException;
import spring.community.exception.DomainException.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFound(EntityNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "엔티티를 찾을 수 없습니다.");
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();

        errorResponse.put("error", "유효성 검사 실패");
        errorResponse.put("message", errorMessage);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(InvalidTokenException.class) // InvalidToken을 처리하는 핸들러
    public ResponseEntity<Map<String, String>> handleInvalidTokenException(InvalidTokenException ex) {
        Map<String, String> errorResponse = new HashMap<>();

        errorResponse.put("error", "유효하지 않은 토큰입니다.");
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(errorResponse); // UNAUTHORIZED 상태 코드
    }
    //  @ExceptionHandler(NoSuchElementException.class)
//  public ResponseEntity<Map<String, String>> handleNotFound(NoSuchElementException ex) {
//    Map<String, String> errorResponse = new HashMap<>();
//    errorResponse.put("error", "리소스를 찾을 수 없습니다.");
//    errorResponse.put("message", ex.getMessage());
//
//    return ResponseEntity.status(HttpStatus.NOT_FOUND)
//        .body(errorResponse);
//  }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Map<String, String>> handleForbidden(ForbiddenException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "권한이 없습니다.");
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(errorResponse);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "리소스를 찾을 수 없습니다.");
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


}
