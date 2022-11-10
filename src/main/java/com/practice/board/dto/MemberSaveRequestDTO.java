package com.practice.board.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberSaveRequestDTO {

    private String email;
    private String username;
    private String password;
}
