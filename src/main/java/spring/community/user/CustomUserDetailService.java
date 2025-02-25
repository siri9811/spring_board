package spring.community.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User userData = userRepository.findByUsername(username);
    log.info("Attempting to log in with username: " + username);

    if (userData != null) {

      //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
      return new CustomUserDetails(userData);
    }

    return null;
  }
}



