package com.practice.board.service.board;

import com.practice.board.dto.board.BoardWriteRequestDTO;

public interface BoardService {

    /**
     * 게시글 작성
     * @param boardWriteRequestDTO 게시글 정보
     * @param email 작성자
     * @return 게시글 ID
     */
    Long saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, String email);
}
