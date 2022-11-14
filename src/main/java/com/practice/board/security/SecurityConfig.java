package com.practice.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()                               // cors 방지
                .csrf().disable()                           // csrf 방지
                .headers().frameOptions().disable();        // x frame 방어 해제


        http.authorizeRequests()
                // TODO: member role 추가하고 권한별로 페이지 설정
                .antMatchers("/**").permitAll()
//                .antMatchers("/member/**").hasRole("ROLE_USER")
                .and()
                .formLogin()
                .loginPage("/loginForm")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/");

        return http.build();
    }
}
