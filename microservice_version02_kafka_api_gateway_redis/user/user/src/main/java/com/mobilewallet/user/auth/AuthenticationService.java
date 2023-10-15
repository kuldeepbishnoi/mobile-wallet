package com.mobilewallet.user.auth;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mobilewallet.user.config.JwtService;
import com.mobilewallet.user.entity.Role;
import com.mobilewallet.user.entity.User;
// import com.kuberCash.main.entity.User;
import com.mobilewallet.user.service.userService.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request){
        // User user = new User();
        // user.setFirstName(request.getFirstName());
        // user.setLastName(request.getLastName());
        // user.setMobileNo(request.getMobileNo());
        // user.setEmail(request.getEmail());
        // user.setPassword(passwordEncoder.encode(request.getPassword()));
        // userService.save(user);

        var user = User.builder()
            .firstName(request.getFirstName())
            .lastName(request.getLastName())
            .mobileNo(request.getMobileNo())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
        userService.save(user);
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(token)
            .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
     authenticationManager.authenticate(
         new UsernamePasswordAuthenticationToken(
            request.getUsername(), 
            request.getPassword()));
        var user = userService.findByMobileNoOrEmail(request.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        var token = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
            .token(token)
            .build();
    }
}
