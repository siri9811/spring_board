package spring.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

}
