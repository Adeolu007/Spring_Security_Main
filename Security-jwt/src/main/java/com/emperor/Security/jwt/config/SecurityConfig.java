package com.emperor.Security.jwt.config;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@AllArgsConstructor
public class SecurityConfig {

    private CustomUserDetailService customUserDetailService;
    //UserDetailService, PasswordEncoder, SecurityFilterChain, AuthenticationManager

//    1st
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    2nd
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

                http
                        .csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests((authorizeHttpRequests) ->
                                authorizeHttpRequests.requestMatchers(HttpMethod.GET,"/api/testing").permitAll()
//                                        .requestMatchers("/api/testing/admin").hasRole("ADMIN")
                                        .requestMatchers("/api/testing/admin").hasAnyAuthority("CEO","ADMIN")
                                        .requestMatchers("/api/auth/**").permitAll()
                                        .anyRequest().authenticated()
                        ).httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails firstUser = User.builder().username("noah").password(passwordEncoder().encode("1234")).roles("USER").build();
//
//        UserDetails secondUser = User.builder().username("moses").password(passwordEncoder().encode("12345")).roles("ADMIN").build();
//
//        UserDetails thirdUser = User.builder().username("emperor").password(passwordEncoder().encode("123456")).roles("CEO").build();
//
//        return new InMemoryUserDetailsManager(firstUser,secondUser,thirdUser);
//    }
}
