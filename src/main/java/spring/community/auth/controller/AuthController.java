package spring.community.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.community.auth.dto.LoginDto;
import spring.community.auth.dto.RegisterDto;
import spring.community.auth.service.AuthService;

@Controller
@ResponseBody
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/register")
  public String register(@RequestBody RegisterDto dto) {
    authService.Register(dto);
    return "ok";

  }
  @PostMapping("/login")
  public String login(@RequestBody LoginDto loginDto) {
    return "ok";
  }
}
