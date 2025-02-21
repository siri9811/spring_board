package spring.community.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserDto {
  private String email;
  private String username;
  private String password;

  public static UserDto createUserDto(User user) {
    return new UserDto(
        user.getEmail(),
        user.getUsername(),
        user.getPassword()
    );
  }
}
