package epam.training.finalproject.web.controller;

import epam.training.finalproject.model.domain.entity.Role;
import epam.training.finalproject.model.domain.entity.User;
import epam.training.finalproject.model.domain.entity.enums.RoleName;
import epam.training.finalproject.model.service.interfaces.RoleService;
import epam.training.finalproject.model.service.interfaces.UserService;
import epam.training.finalproject.web.dto.ApiResponse;
import epam.training.finalproject.web.dto.JwtAuthenticationResponse;
import epam.training.finalproject.web.dto.LoginRequest;
import epam.training.finalproject.web.dto.SignUpRequest;
import epam.training.finalproject.web.jwt.JwtTokenProvider;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
public class AuthController {

    private final JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    private static final Logger LOGGER = Logger.getLogger(AuthController.class);

    @Autowired
    public AuthController(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

//    @PostMapping(value = "/registration")
//    public long registration(@Validated @RequestBody User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userService.save(user);
//    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if(userService.findByUsername(signUpRequest.getUsername())!=null) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if(userService.findByEmail(signUpRequest.getEmail())!=null) {
            return new ResponseEntity(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Role userRole = roleService.findByRoleName(RoleName.ROLE_USER);
        user.setRoles(Collections.singletonList((userRole)));

        userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(user.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }


}
