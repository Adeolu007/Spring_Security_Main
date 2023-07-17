package com.JWTDemo.JWT.demo.auth;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {
private String email;
private String password;
}
