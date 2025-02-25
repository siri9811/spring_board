package spring.community.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.community.auth.dto.LoginRequest;
import spring.community.auth.dto.RegisterRequest;
import spring.community.auth.service.AuthService;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
    authService.Register(registerRequest);
    return ResponseEntity.ok("User registerd successfully");
  }
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

    String token = authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
    return ResponseEntity.ok(token);
  }
}
