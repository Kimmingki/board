package com.practice.board.service.comment;

import com.practice.board.dto.comment.CommentRequestDTO;
import com.practice.board.dto.comment.CommentResponseDTO;

import java.util.List;

public interface CommentService {

    /**
     * 댓글 작성
     * @param commentRequestDTO 댓글 정보
     * @param boardId 게시물
     * @param email 작성자
     * @return 댓글 ID
     */
    Long writeComment(CommentRequestDTO commentRequestDTO, Long boardId, String email);

    /**
     * 댓글 조회
     * @param id 게시물
     * @return 게시물 별 댓글
     */
    List<CommentResponseDTO> commentList(Long id);
}
