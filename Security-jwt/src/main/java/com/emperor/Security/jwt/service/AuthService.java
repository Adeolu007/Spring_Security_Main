package com.emperor.Security.jwt.service;

import com.emperor.Security.jwt.dto.LoginDto;
import com.emperor.Security.jwt.dto.USerRegisterDto;

public interface AuthService {
    String regiter(USerRegisterDto uSerRegisterDto);
    String login (LoginDto loginDto);
}
