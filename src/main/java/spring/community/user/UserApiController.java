package spring.community.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.community.user.data.UserRequest;

import static spring.community.config.SwaggerConfig.BEARER_AUTH;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = BEARER_AUTH)
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserRequest> getUserInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserRequest user = userService.getUser(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }

    @PatchMapping
    @Secured("ROLE_USER")
    public ResponseEntity<UserRequest> updateUser(
            @RequestBody UserRequest userRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserRequest update = userService.updateUser(userDetails.getUsername(), userRequest);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Principal principal
    ) {
        userService.deleteUser(principal.getName());
        return ResponseEntity.noContent().build();
    }
}
