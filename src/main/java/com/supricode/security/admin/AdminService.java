package com.supricode.security.admin;

import com.supricode.security.auth.AuthenticationRequest;
import com.supricode.security.auth.AuthenticationResponse;
import com.supricode.security.auth.RegisterRequest;
import com.supricode.security.config.JwtService;
import com.supricode.security.user.Admin;
import com.supricode.security.user.AdminRepository;
import com.supricode.security.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

//    public AuthenticationResponse register(RegisterRequest request) {
//        var user = User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .id_number(request.getId_number())
//                .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();

    public AdminResponse register(AdminRequest request) {
        var admin = Admin.builder()
                .name(request.getName())
                .city(request.getCity())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(admin);
        var jwtToken = jwtService.generateToken(admin);
        return AdminResponse.builder()
                .token(jwtToken)
                .name(admin.getName())
                .build();
    }

    public AdminResponse authenticate(AuthenticationRequestAdmin request) {
        // Attempt to authenticate with the provided credentials
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // Fetch the admin from the repository
        var admin = repository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        // Generate JWT token
        var jwtToken = jwtService.generateToken(admin);

        // Return the response with the token
        return AdminResponse.builder()
                .token(jwtToken)
                .name(admin.getName())
                .build();
    }


//    public AdminResponse authenticate(AdminRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var admin = repository.findByEmail(request.getEmail())
//                .orElseThrow();
//        System.out.println("admin "+ admin);
//        var jwtToken = jwtService.generateToken(admin);
//        return AdminResponse.builder()
//                .token(jwtToken)
//                .name(admin.getName())
//                .build();
//    }

//    public AuthenticationResponse authenticate(AuthenticationRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//        var user = repository.findByEmail(request.getEmail())
//                .orElseThrow();
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .firstname(user.getFirstname())
//                .build();
//    }

}
