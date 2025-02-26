package spring.community.user;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * username이 존재하는지 확인
     *
     * @param email 사용하려는 email
     * @return 존재하면 true, 존재하지 않으면 false
     */
    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
