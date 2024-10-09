package com.supricode.security.auth;

import com.supricode.security.config.JwtService;
import com.supricode.security.user.Admin;
import com.supricode.security.user.User;
import com.supricode.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceAdmin {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private  final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
//
//    private String name;
//
//    private String city;
//
//    private String email;
//
//    private String password;


//    public AuthenticationResponseAdmin register(UserRequest request) {
//        var user = Admin,
////                .firstname(request.getFirstname())
////                .lastname(request.getLastname())
////                .email(request.getEmail())
////                .password(passwordEncoder.encode(request.getPassword()))
////                .id_number(request.getId_number())
//                .
//                .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .firstname(user.getFirstname())
                .build();
    }

}
