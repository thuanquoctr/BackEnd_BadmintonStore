package store.badminton.BadmintonStore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import store.badminton.BadmintonStore.payloads.JwtAuthRequest;
import store.badminton.BadmintonStore.payloads.JwtAuthResponse;
import store.badminton.BadmintonStore.payloads.UserDto;
import store.badminton.BadmintonStore.services.impl.JwtService;
import store.badminton.BadmintonStore.services.UserService;
import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @Autowired
    public UserController(UserService userService, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable long id) {
        return ResponseEntity.ok(this.userService.getUserById(id));
    }
    @GetMapping("/getByUsername/{username}")
    public ResponseEntity<UserDto> getUser(@PathVariable String username) {
        return ResponseEntity.ok(this.userService.getUserByUsername(username));
    }
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto userResult = userService.createUser(userDto);
        return new ResponseEntity<>(userResult, HttpStatus.CREATED);
    }


    @PutMapping
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        UserDto userResult = userService.updateUser(userDto);
        return ResponseEntity.ok(userResult);
    }

    @GetMapping("/existsByUsername")
    public Boolean existsByUsername(@RequestParam String username) {
        return userService.checkIfUserExists(username);
    }

    @GetMapping("/existsByEmail")
    public Boolean existsByEmail(@RequestParam String email) {
        return userService.checkIfEmailExists(email);
    }

    @GetMapping("/existsByPhone")
    public Boolean existsByPhone(@RequestParam String phone) {
        return userService.checkIfPhoneExists(phone);
    }

    @PostMapping("/loginAccount")
    public ResponseEntity<?> existsByPhone(@RequestBody JwtAuthRequest jwtAuthRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword()));
            if (authentication.isAuthenticated()) {
                final String token = jwtService.generateToken(jwtAuthRequest.getUsername());
                return ResponseEntity.ok(new JwtAuthResponse(token));
            }
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.badRequest().body("Invalid username or password");
    }
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{id}")
//    public ResponseEntity<> deleteUser(@PathVariable long id) {
//        userService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


}
