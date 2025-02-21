package spring.community.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import spring.community.auth.dto.RegisterDto;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String email;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  private String role;

  public static User createRegister(RegisterDto dto) {
    return new User(
        dto.getEmail(),
        dto.getUsername(),
        dto.getPassword(),
        dto.getRole()
    );
  }

  public void patch(UserDto dto) {
    this.username = dto.getUsername();
    this.password = dto.getPassword();
  }
}

