package epam.training.finalproject.web.controller;

import epam.training.finalproject.web.dto.JwtAuthenticationResponse;
import epam.training.finalproject.web.dto.SignInDTO;
import epam.training.finalproject.web.dto.SignUpDTO;
import epam.training.finalproject.web.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(value = "/signIn")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInDTO request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@RequestBody SignUpDTO request) {
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(request.getUsername()).toUri()).body(authenticationService.signUp(request));
    }


}
