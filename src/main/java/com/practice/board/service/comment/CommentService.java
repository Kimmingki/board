package com.practice.board.service.comment;

import com.practice.board.dto.comment.CommentRequestDTO;

public interface CommentService {

    /**
     * 댓글 작성
     * @param commentRequestDTO 댓글 정보
     * @param boardId 게시물
     * @param email 작성자
     * @return 댓글 ID
     */
    Long writeComment(CommentRequestDTO commentRequestDTO, Long boardId, String email);
}
