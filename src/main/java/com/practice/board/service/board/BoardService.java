package com.practice.board.service.board;

import com.practice.board.dto.board.BoardResponseDTO;
import com.practice.board.dto.board.BoardWriteRequestDTO;

import java.util.List;

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
     * @return 게시글 정보
     */
    BoardResponseDTO boardDetail(Long id);

    /**
     * 게시글 전체조회
     * @return
     */
    List<BoardResponseDTO> boardList();

    /**
     * 게시글 수정
     * @param id 게시글 ID
     * @param boardWriteRequestDTO 수정 정보
     * @return 게시글 ID
     */
    Long boardUpdate(Long id, BoardWriteRequestDTO boardWriteRequestDTO);

    /**
     * 게시글 삭제
     * @param id 게시글 ID
     */
    void boardRemove(Long id);
}
