package ch.medusa.sqlapi.domain.user;

import ch.medusa.sqlapi.domain.user.dto.UserDTO;
import ch.medusa.sqlapi.domain.user.dto.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    public ResponseEntity<UserDTO> findAuthenticated() {
        User user = userService.findAuthenticatedUser();

        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<UserDTO> updateAuthenticated(UserDTO userDTO) {
        User user = userService.updateAuthenticatedUser(userMapper.fromDTO(userDTO));

        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAuthenticated() {
        userService.deleteAuthenticatedUser();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
