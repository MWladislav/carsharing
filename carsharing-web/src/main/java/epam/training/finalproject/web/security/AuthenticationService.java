package epam.training.finalproject.web.security;

import epam.training.finalproject.exception.DuplicateEntityCredentialsException;
import epam.training.finalproject.exception.OperationException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthenticationService {

    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public JwtAuthenticationResponse authenticate(LoginRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new JwtAuthenticationResponse(tokenProvider.generateToken(authentication));
    }

    public ApiResponse signUp(SignUpRequest request){
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        Role userRole = roleService.findByRoleName(RoleName.ROLE_USER);
        user.setRoles(Collections.singletonList((userRole)));

        try {
            userService.save(user);
        } catch (OperationException | DuplicateEntityCredentialsException ex){
            return new ApiResponse(false, ex.getLocalizedMessage());
        }

        return new ApiResponse(true, "SingUp successful");
    }

}
