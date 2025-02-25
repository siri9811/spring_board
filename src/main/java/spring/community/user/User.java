package spring.community.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "deleted = false")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

  @Id
  private String username;

  @Column(nullable = false)
  private String password;

  private String role;

  private boolean deleted = false;

  public void changePassword(UserRequest dto) {
    this.password = dto.getPassword();
  }
}

