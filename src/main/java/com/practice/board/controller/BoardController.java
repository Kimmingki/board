package com.practice.board.controller;

import com.practice.board.dto.board.BoardResponseDTO;
import com.practice.board.dto.board.BoardWriteRequestDTO;
import com.practice.board.service.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /**
     * 게시글 작성
     * @return 게시글 작성 페이지
     */
    @GetMapping("/write")
    public String writeForm() {
        return "board/write";
    }

    /**
     * 게시글 작성 post
     * @param boardWriteRequestDTO 게시글 정보
     * @param authentication 유저 정보
     * @return 게시글 디테일 페이지
     */
    @PostMapping("/write")
    public String write(BoardWriteRequestDTO boardWriteRequestDTO, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boardService.saveBoard(boardWriteRequestDTO, userDetails.getUsername());

        return "/home";
    }

    /**
     * 게시글 상세 조회
     * @param id 게시글 ID
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        BoardResponseDTO result = boardService.boardDetail(id);
        model.addAttribute("dto", result);
        model.addAttribute("id", id);

        return "board/detail";
    }

    /**
     * 게시글 수정
     * @param id 게시글 ID
     * @param model
     * @return 게시글 수정 페이지
     */
    @GetMapping("/{id}/update")
    public String boardUpdateForm(@PathVariable Long id, Model model) {
        BoardResponseDTO result = boardService.boardDetail(id);
        model.addAttribute("dto", result);
        model.addAttribute("id", id);

        return "board/update";
    }

    /**
     * 게시글 수정 post
     * @param id 게시글 ID
     * @param boardWriteRequestDTO 수정 정보
     * @return 게시글 상세 조회 페이지
     */
    @PostMapping("/{id}/update")
    public String boardUpdate(@PathVariable Long id, BoardWriteRequestDTO boardWriteRequestDTO) {
        boardService.boardUpdate(id, boardWriteRequestDTO);

        return "redirect:/board/{id}}";
    }
}
