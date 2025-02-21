package spring.community.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
  public String email;
  public String username;
  public String password;
  public String role = "ROLE_ADMIN";
}
