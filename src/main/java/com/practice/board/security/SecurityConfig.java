package com.practice.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        // 정적 리소스들이 보안필터를 거치지 않게끔
        return (web) -> web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/font/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()                               // cors 방지
                .csrf().disable()                           // csrf 방지
                .headers().frameOptions().disable();        // x frame 방어 해제

        http.authorizeRequests()
                .antMatchers("/member/**").hasRole("USER")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/loginForm")                                // 로그인 페이지
                .loginProcessingUrl("/login")                           // 로그인 로직 있는 곳
                .defaultSuccessUrl("/")                                 // 로그인 성공 시 리다이렉트
                .failureUrl("/login?error=true")      // 로그인 실패 후 리다이렉트
                .usernameParameter("email")                             // 아이디 파라미터명
                .passwordParameter("password")                          // 패스워드 파라미터명
                .and()
                .logout()
                .logoutSuccessUrl("/")          // 로그아웃 성공 시 리다이렉트
                .invalidateHttpSession(true);   // 세션 날리기

        return http.build();
    }
//
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.H2)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }
//
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        UserDetails user = User.withUsername("user")
//                .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")
//                .roles("USER")
//                .build();
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        return users;
//    }
}
