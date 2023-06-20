package com.emperor.Security.jwt.config;

import com.emperor.Security.jwt.entity.UserEntity;
import com.emperor.Security.jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsernameOrEmail(username,username).orElseThrow(()->new UsernameNotFoundException("User not found"));
        Set<GrantedAuthority> grantedAuthorities = user.getRoles().stream().map((role)->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new User(user.getUsername(), user.getPassword(),grantedAuthorities);
    }
}
