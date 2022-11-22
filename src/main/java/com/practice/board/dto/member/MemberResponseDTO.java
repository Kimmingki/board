package com.practice.board.dto.member;

import com.practice.board.domain.Member;
import com.practice.board.domain.Role;
import lombok.*;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {

    private String email;
    private String username;
    private Role role;

    @Builder
    public MemberResponseDTO(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
        this.role = member.getRole();
    }
}
