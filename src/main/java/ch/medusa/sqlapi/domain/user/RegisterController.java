package ch.medusa.sqlapi.domain.user;

import ch.medusa.sqlapi.domain.user.dto.UserDTO;
import ch.medusa.sqlapi.domain.user.dto.UserMapper;
import ch.medusa.sqlapi.domain.user.dto.UserRegistrationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    private UserService userService;
    private UserMapper userMapper;

    @Autowired
    public RegisterController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public ResponseEntity<UserDTO> register(@RequestBody UserRegistrationDTO userDTO) {
        User user = userService.register(userMapper.fromDTO(userDTO));

        return new ResponseEntity<>(userMapper.toDTO(user), HttpStatus.CREATED);
    }
}
