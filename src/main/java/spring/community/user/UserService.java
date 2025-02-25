package spring.community.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public UserRequest getUser(String username) {
    return userRepository.findById(username)
        .map(UserRequest::createUserDto)
        .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, email: " + username));
  }

  public UserRequest updateUser(String username, UserRequest userRequest) {

    User target = userRepository.findById(username)
        .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, email: " + username));

    String hashedPassword = bCryptPasswordEncoder.encode(userRequest.getPassword()); // 비밀번호 해싱
    target.setPassword(hashedPassword); // 해싱된 비밀번호로 설정

    User update = userRepository.save(target);

    return UserRequest.createUserDto(update);
  }

  public void deleteUser(String username) {
    User target = userRepository.findById(username)
        .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, username: " + username));
    target.setDeleted(true);
    userRepository.save(target);
  }


}
