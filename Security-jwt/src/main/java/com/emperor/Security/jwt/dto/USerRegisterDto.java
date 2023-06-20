package com.emperor.Security.jwt.dto;

import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class USerRegisterDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
