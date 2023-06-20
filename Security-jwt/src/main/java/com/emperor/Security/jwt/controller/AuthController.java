package com.emperor.Security.jwt.controller;


import com.emperor.Security.jwt.dto.LoginDto;
import com.emperor.Security.jwt.dto.USerRegisterDto;
import com.emperor.Security.jwt.service.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthServiceImpl authService;

    @PostMapping("/register")
    public ResponseEntity<String> register (@RequestBody USerRegisterDto uSerRegisterDto){
        return new ResponseEntity<>(authService.regiter(uSerRegisterDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login (@RequestBody LoginDto loginDto){
        return new ResponseEntity<>(authService.login(loginDto),HttpStatus.OK);
    }

}
