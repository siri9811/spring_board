package spring.community.user;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/users")
public class UserApiController {
  private final UserService userService;


  @GetMapping
  public ResponseEntity<UserRequest> getUserInfo(Principal principal) {
    UserRequest user = userService.getUser(principal.getName());
    return ResponseEntity.ok(user);
  }

  @PatchMapping
  public ResponseEntity<UserRequest> updateUser(@RequestBody UserRequest userRequest, Principal principal) {
    UserRequest update = userService.updateUser(principal.getName(), userRequest);
    return ResponseEntity.ok(update);
  }

  @DeleteMapping
  public ResponseEntity<Void> delete(Principal principal) {
    userService.deleteUser(principal.getName());
    return ResponseEntity.noContent().build();
  }


}
