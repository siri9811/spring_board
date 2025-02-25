package spring.community.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
  private String username;
  private String password;

  public static UserRequest createUserDto(User user) {
    return new UserRequest(
        user.getUsername(),
        user.getPassword()
    );
  }
}
