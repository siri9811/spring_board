package spring.community.user;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SoftDelete;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@SoftDelete(columnName = "is_deleted")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    private String email;

    private String username;

    @Column(nullable = false)
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(value = EnumType.STRING)
    private Set<UserRole> roles;

    static public User createUser(String email, String username, String password) {
        return new User(
                email,
                username,
                password,
                Set.of(UserRole.USER)
        );
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Set<UserRole> getAuthorities() {
        return roles;
    }
}

// 서비스 로직과 시큐리티는 분리되어야 한다.
