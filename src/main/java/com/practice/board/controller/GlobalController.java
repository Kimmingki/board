package com.practice.board.controller;

import com.practice.board.dto.member.MemberSaveRequestDTO;
import com.practice.board.service.GlobalService;
import com.practice.board.service.board.BoardService;
import com.practice.board.validator.CheckEmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GlobalController {

    private final GlobalService globalService;
    private final BoardService boardService;
    private final CheckEmailValidator checkEmailValidator;

    /* 중복 검사 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }

    /**
     * Home 화면
     * @return 홈 페이지
     */
    @GetMapping("/")
    public String home(Model model, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable, String keyword) {
        if(keyword == null) {
            model.addAttribute("boardList", boardService.boardList(pageable));
        } else {
            model.addAttribute("boardList", boardService.searchingBoardList(keyword, pageable));
        }

        return "home";
    }

    /**
     * 예외처리 페이지
     * @return 예외처리 페이지
     */
    @GetMapping("/denied")
    public String doDenied() {
        return "denied";
    }

    /**
     * 회원 가입
     * @return 회원 가입 페이지
     */
    @GetMapping("/signup")
    public String signupForm() {
        return "member/memberForm";
    }

    /**
     * 회원 가입 post
     * @param memberSaveRequestDTO
     * @param errors
     * @param model
     * @return 홈 페이지
     */
    @PostMapping("/signup")
    public String signup(@Valid MemberSaveRequestDTO memberSaveRequestDTO, Errors errors, Model model) {
        /* 검증 */
        if (errors.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 유지 */
            model.addAttribute("dto", memberSaveRequestDTO);
            /* 유효성 검사를 통과하지 못한 필드와 메세지 핸들링 */
            globalService.messageHandling(errors, model);
            /* 회원가입 페이지로 리턴 */
            return "/member/memberForm";
        }
        globalService.join(memberSaveRequestDTO);

        return "redirect:/";
    }

    /**
     * 로그인 페이지
     * @return 로그인 페이지
     */
    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }
}
