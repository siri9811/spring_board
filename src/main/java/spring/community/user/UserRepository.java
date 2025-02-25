package spring.community.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

  Boolean existsByUsername(String username);

  User findByUsername(String username);
}
