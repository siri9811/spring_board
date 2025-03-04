package spring.community.user;

import static spring.community.config.SwaggerConfig.BEARER_AUTH;

import io.swagger.v3.oas.annotations.Operation;
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

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = BEARER_AUTH)
@RequestMapping("/user")
public class UserApiController {
    private final UserService userService;

    @GetMapping
    @Operation(summary = "유저 정보 검색", description = "유저 정보를 검색합니다.")

    public ResponseEntity<UserRequest> getUserInfo(
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserRequest user = userService.getUser(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }

    @PatchMapping
    @Secured("ROLE_USER")
    @Operation(summary = "유저 정보 수정", description = "유저 정보를 수정합니다.")
    public ResponseEntity<UserRequest> updateUser(
            @RequestBody UserRequest userRequest,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserRequest update = userService.updateUser(userDetails.getUsername(), userRequest);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping
    @Operation(summary = "유저 정보 삭제", description = "유저 정보를 삭제합니다.")
    public ResponseEntity<Void> delete(
            @AuthenticationPrincipal Principal principal
    ) {
        userService.deleteUser(principal.getName());
        return ResponseEntity.noContent().build();
    }
}
