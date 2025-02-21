package spring.community.user;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public UserDto getUser(String email) {
    return userRepository.findById(email)
        .map(UserDto::createUserDto)
        .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, email: " + email));
  }

  public List<UserDto> getAllUsers() {
    return userRepository.findAll()
        .stream()
        .map(UserDto::createUserDto)
        .collect(Collectors.toList());
  }


  public UserDto updateUser(String email, UserDto dto) {
    User target = userRepository.findById(email)
        .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, email: " + email));

    target.patch(dto);

    User update = userRepository.save(target);

    return UserDto.createUserDto(update);
  }

  public void deleteUser(String email) {
    User target = userRepository.findById(email)
        .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, email: " + email));
    userRepository.delete(target);
  }


}
