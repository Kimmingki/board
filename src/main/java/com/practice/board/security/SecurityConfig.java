package com.practice.board.security;

import com.practice.board.domain.Member;
import com.practice.board.dto.MemberResponseDTO;
import com.practice.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberRepository memberRepository;
    private final UserDetailsService userDetailsService;

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
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors().disable()                               // cors 방지
                .csrf().disable()                           // csrf 방지
                .headers().frameOptions().disable();        // x frame 방어 해제

        http.authenticationProvider(authenticationProvider());

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
                .permitAll()
                .and()
                .rememberMe().userDetailsService(userDetailsService)
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")                 // 로그아웃 성공 시 리다이렉트
                .invalidateHttpSession(true)                            // 세션 날리기
                .deleteCookies("JSESSIONID")
                .permitAll();

        return http.build();
    }
}
