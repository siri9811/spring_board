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

    public String Register(RegisterRequest registerRequest) {

        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User data = User.createUser(
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                bCryptPasswordEncoder.encode(registerRequest.getPassword())
        );

        userRepository.save(data);

        return jwtUtil.createJwt(data.getUsername(), data.getRoles(), 60 * 60 * 10L);
    }

    public String authenticate(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow();
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return jwtUtil.createJwt(user.getUsername(), user.getRoles(), 60 * 60 * 10L);
        }

        throw new RuntimeException("Invalid username or password");
    }
}
