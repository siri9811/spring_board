package spring.community.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * username이 존재하는지 확인
     *
     * @param username 사용하려는 username
     * @return 존재하면 true, 존재하지 않으면 false
     */
    Boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);
}
