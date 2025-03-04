package spring.community.auth.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import spring.community.auth.dto.LoginRequest;
import spring.community.auth.dto.RegisterRequest;
import spring.community.auth.service.AuthService;

@RestController
@RequiredArgsConstructor
/**
 * 로그인 및 회원가입 컨트롤러
 */
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입합니다.")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        String token = authService.Register(registerRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "유저를 로그인합니.")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest loginRequest
    ) {
        String token = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(token);
    }
}
