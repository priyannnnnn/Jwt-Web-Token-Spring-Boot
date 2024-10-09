package com.supricode.security.admin;

import com.supricode.security.auth.AuthenticationRequest;
import com.supricode.security.auth.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

///api/v1/admin/
@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService service;


    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AdminResponse> register(
            @RequestBody AdminRequest request
    ) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate/admin")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AdminResponse> register(
            @RequestBody AuthenticationRequestAdmin request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }
//    @PostMapping("/authenticate")
//    @CrossOrigin(origins = "http://localhost:3000")
//    public ResponseEntity<AuthenticationResponse> register(
//            @RequestBody AuthenticationRequest request
//    ){
//        return ResponseEntity.ok(service.authenticate(request));
//    }

}
