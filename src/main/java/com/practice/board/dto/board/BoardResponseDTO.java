package com.practice.board.dto.board;

import com.practice.board.domain.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {

    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String username;

    @Builder
    public BoardResponseDTO(Board board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.username = board.getMember().getUsername();
    }
}
