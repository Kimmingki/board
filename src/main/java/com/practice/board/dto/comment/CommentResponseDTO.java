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
    private Long id;
    private String content;
    private String username;
    private String email;
    private String imageUrl;

    @Builder
    public CommentResponseDTO(Comment comment) {
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();
        this.id = comment.getId();
        this.content = comment.getContent();
        this.username = comment.getMember().getUsername();
        this.email = comment.getMember().getEmail();
        this.imageUrl = comment.getMember().getImage().getUrl();
    }
}
