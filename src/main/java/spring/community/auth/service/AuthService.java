package spring.community.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.community.auth.dto.RegisterRequest;
import spring.community.jwt.JWTUtil;
import spring.community.user.User;
import spring.community.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  private final JWTUtil jwtUtil;

  public void Register(RegisterRequest registerRequest) {

    if (userRepository.existsByUsername(registerRequest.getUsername())) {
      return;
    }

    User data = new User();
    data.setUsername(registerRequest.getUsername());
    data.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
    data.setRole("ROLE_USER");

    userRepository.save(data);

  }

  public String authenticate(String username, String password) {
    User user = userRepository.findByUsername(username);
    if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
      return jwtUtil.createJwt(user.getUsername(), user.getRole(), 900000L); //15분
    }
    throw new RuntimeException("Invalid username or password");
  }
}
