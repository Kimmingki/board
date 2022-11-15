package com.practice.board.dto;

import com.practice.board.domain.Member;
import com.practice.board.domain.Role;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class MemberResponseDTO implements UserDetails {

    private String email;
    private String username;
    private Role role;

    @Builder
    public MemberResponseDTO(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.role = member.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.toString()));
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
