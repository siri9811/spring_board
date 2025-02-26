package spring.community.user;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.community.exception.UserNotFoundException;
import spring.community.user.data.UserRequest;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public UserRequest getUser(String username) {
        return userRepository.findById(username)
                .map(UserRequest::createUserDto)
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Transactional
    public UserRequest updateUser(String username, UserRequest userRequest) {
        User target = userRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, email: " + username));

        target.changePassword(
                bCryptPasswordEncoder.encode(userRequest.password())
        ); // 비밀번호 변경

        return UserRequest.createUserDto(target);
    }

    @Transactional
    public void deleteUser(String username) {
        User target = userRepository.findById(username)
                .orElseThrow(() -> new EntityNotFoundException("유저 정보가 존재하지 않습니다, username: " + username));
        userRepository.delete(target);
    }
}
