package spring.community.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.community.auth.dto.RegisterDto;
import spring.community.user.User;
import spring.community.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public void Register(RegisterDto dto) {

    Boolean isExist = userRepository.existsByEmail(dto.getEmail());

    if(isExist) {
      return;
    }

    User newUser = User.createRegister(dto);
    userRepository.save(newUser);

  }

}
