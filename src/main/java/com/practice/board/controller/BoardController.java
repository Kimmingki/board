package com.practice.board.controller;

import com.practice.board.dto.board.BoardResponseDTO;
import com.practice.board.dto.board.BoardWriteRequestDTO;
import com.practice.board.dto.comment.CommentResponseDTO;
import com.practice.board.dto.image.BoardImageUploadDTO;
import com.practice.board.service.board.BoardService;
import com.practice.board.service.comment.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    private final BoardService boardService;
    private final CommentService commentService;

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
    public String write(BoardWriteRequestDTO boardWriteRequestDTO,
                        @ModelAttribute BoardImageUploadDTO boardImageUploadDTO,
                        Authentication authentication) {

        logger.info("boardImageDTO is {}", boardImageUploadDTO);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        boardService.saveBoard(boardWriteRequestDTO, boardImageUploadDTO, userDetails.getUsername());

        return "redirect:/";
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
        List<CommentResponseDTO> commentResponseDTO = commentService.commentList(id);

        model.addAttribute("comments", commentResponseDTO);
        model.addAttribute("dto", result);
        model.addAttribute("id", id);

        return "board/detail";
    }

    /**
     * 게시글 수정
     * @param id 게시글 ID
     * @param model
     * @param authentication 유저 정보
     * @return 게시글 수정 페이지
     */
    @GetMapping("/{id}/update")
    public String boardUpdateForm(@PathVariable Long id, Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        BoardResponseDTO result = boardService.boardDetail(id);
        if (result.getEmail() != userDetails.getUsername()) {
            return "redirect:/";
        }

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

        return "redirect:/board/" + id;
    }

    /**
     * 게시글 삭제
     * @param id 게시글 ID
     * @param authentication 유저 정보
     * @return
     */
    @GetMapping("/{id}/remove")
    public String boardRemove(@PathVariable Long id, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        BoardResponseDTO result = boardService.boardDetail(id);
        if (!Objects.equals(result.getEmail(), userDetails.getUsername())) {
            return "redirect:/";
        }

        boardService.boardRemove(id);

        return "redirect:/";
    }
}
