package com.practice.board.dto.board;

import com.practice.board.domain.Board;
import com.practice.board.domain.BoardImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private String username;
    private String email;
    private List<String> imageUrls;

    @Builder
    public BoardResponseDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdAt = board.getCreatedAt();
        this.username = board.getMember().getUsername();
        this.email = board.getMember().getEmail();
        this.imageUrls = board.getBoardImages().stream()
                .map(BoardImage::getUrl)
                .collect(Collectors.toList());
    }
}
