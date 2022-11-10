package com.practice.board.dto;

import com.practice.board.domain.Member;
import lombok.*;

@Getter
@NoArgsConstructor
public class MemberResponseDTO {

    private String email;
    private String username;

    @Builder
    public MemberResponseDTO(Member member) {
        this.email = member.getEmail();
        this.username = member.getUsername();
    }
}
