package spring.community.exception;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(NotFountException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFountException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "리소스를 찾을 수 없습니다.");
        errorResponse.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }


}
