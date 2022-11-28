package com.practice.board.service.board;

import com.practice.board.dto.board.BoardResponseDTO;
import com.practice.board.dto.board.BoardWriteRequestDTO;

public interface BoardService {

    /**
     * 게시글 작성
     * @param boardWriteRequestDTO 게시글 정보
     * @param email 작성자
     * @return 게시글 ID
     */
    Long saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, String email);

    /**
     * 게시글 상세조회
     * @param id 게시글 ID
     * @return
     */
    BoardResponseDTO boardDetail(Long id);
}
