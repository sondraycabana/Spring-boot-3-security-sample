package com.example.Geeksample.controller;


import com.example.Geeksample.User;
import com.example.Geeksample.dtos.LoginResponse;
import com.example.Geeksample.dtos.LoginUserDto;
import com.example.Geeksample.dtos.RegisterUserDto;
import com.example.Geeksample.service.AuthenticationService;
import com.example.Geeksample.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.slf4j.SLF4JLogger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDto registerUserDto) {
        User registeredUser = authenticationService.createUser(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse .setExpiresIn(jwtService.getExpirationTime());

        log.info("This is the token:@log" + jwtToken );
        return ResponseEntity.ok(loginResponse);
//        log.info("This is the token:@log" + jwtToken );
//        System.out.println("This is the token:" + jwtToken);
//        return ResponseEntity.ok("Created");
    }
}