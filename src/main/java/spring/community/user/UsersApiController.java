package spring.community.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.community.user.data.UserRequest;

import static spring.community.config.SwaggerConfig.BEARER_AUTH;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = BEARER_AUTH)
@RequiredArgsConstructor
public class UsersApiController {
    private final UserService userService;

    @GetMapping("/{username}")
    public ResponseEntity<UserRequest> getUserInfo(
            @PathVariable String username
    ) {
        UserRequest user = userService.getUser(username);
        return ResponseEntity.ok(user);
    }
}
