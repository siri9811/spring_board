package spring.community.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import spring.community.auth.dto.RegisterRequest;
import spring.community.jwt.TokenProvider;
import spring.community.user.User;
import spring.community.user.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    public String Register(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new RuntimeException("email already exists");
        }

        User data = User.createUser(
                registerRequest.getEmail(),
                registerRequest.getUsername(),
                bCryptPasswordEncoder.encode(registerRequest.getPassword())
        );

        userRepository.save(data);

        return tokenProvider.createToken(data.getEmail(), data.getRoles(), 60 * 60 * 10L);
    }

    public String authenticate(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("email not found"));
        if (bCryptPasswordEncoder.matches(password, user.getPassword())) {
            return tokenProvider.createToken(user.getEmail(), user.getRoles(), 60 * 60 * 10L);
        }

        throw new RuntimeException("Invalid email or password");
    }

}
