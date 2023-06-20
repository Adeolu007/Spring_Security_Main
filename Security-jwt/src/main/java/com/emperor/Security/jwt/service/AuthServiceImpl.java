package com.emperor.Security.jwt.service;

import com.emperor.Security.jwt.dto.LoginDto;
import com.emperor.Security.jwt.dto.USerRegisterDto;
import com.emperor.Security.jwt.entity.RoleEntity;
import com.emperor.Security.jwt.entity.UserEntity;
import com.emperor.Security.jwt.repository.RoleRepository;
import com.emperor.Security.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    @Override
    public String regiter(USerRegisterDto uSerRegisterDto) {
        //check whether username is in use
        //register a user, fetch role from role repository, add to user, save user, return registered successfully
       if(userRepository.existsByUsernameOrEmail(uSerRegisterDto.getUsername(), uSerRegisterDto.getEmail())){
           return "Username or email already taken";
       }

        RoleEntity role  = roleRepository.findByName("USER").get();
        UserEntity user = UserEntity.builder()
                .firstName((uSerRegisterDto.getFirstName()))
                .lastName(uSerRegisterDto.getLastName())
                .username(uSerRegisterDto.getUsername())
                .email(uSerRegisterDto.getEmail())
                .password(passwordEncoder.encode(uSerRegisterDto.getPassword()))
                .roles(Collections.singleton(role))
                .build();
        userRepository.save(user);
        return "User successfully registered";
    }

    @Override
    public String login(LoginDto loginDto) {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "Login Successfully";
    }
}
