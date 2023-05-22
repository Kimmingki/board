package com.practice.board.dto.comment;

import com.practice.board.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDTO {

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String content;
    private String username;

    @Builder
    public CommentResponseDTO(Comment comment) {
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.content = comment.getContent();
        this.username = comment.getMember().getUsername();
    }
}
