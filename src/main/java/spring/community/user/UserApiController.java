package spring.community.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserApiController {
  private final UserService userService;

  @GetMapping
  public ResponseEntity<List<UserDto>> show() {
    List<UserDto> userDto = userService.getAllUsers();
    return ResponseEntity.ok(userDto);
  }

  @GetMapping("/{email}")
  public ResponseEntity<UserDto> findById(@PathVariable String email) {
    UserDto target  = userService.getUser(email);
    return ResponseEntity.ok(target);
  }

  @PatchMapping("/{email}")
  public ResponseEntity<UserDto> update(@PathVariable String email, @RequestBody UserDto userDto) {
    UserDto patchDto = userService.updateUser(email, userDto);
    return ResponseEntity.ok(patchDto);
  }

  @DeleteMapping("/{email}")
  public ResponseEntity<Void> delete(@PathVariable String email) {
    userService.deleteUser(email);
    return ResponseEntity.noContent().build();
  }


}
