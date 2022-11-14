package com.practice.board.controller;

import com.practice.board.dto.MemberLoginDTO;
import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.service.GlobalServcie;
import com.practice.board.validator.CheckEmailValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class GlobalController {

    private final GlobalServcie globalServcie;
    private final CheckEmailValidator checkEmailValidator;

    /* 유효성 검증 */
    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkEmailValidator);
    }

    public void messageHandling(Errors errors, Model model) {
        Map<String, String> validatorResult = globalServcie.validateHandling(errors);
        for (String key : validatorResult.keySet()) {
            model.addAttribute(key, validatorResult.get(key));
        }
    }

    /**
     * Home 화면
     *
     * @return 홈페이지
     */
    @GetMapping("/")
    public String Home() {
        return "home";
    }

    /**
     * 회원 가입
     *
     * @return 회원 가입 페이지
     */
    @GetMapping("/signup")
    public String signupForm() {
        return "members/memberForm";
    }

    /**
     * 회원 가입 post
     *
     * @param memberSaveRequestDTO 회원 정보
     * @return 홈페이지
     */
    @PostMapping("/signup")
    public String signup(@Valid MemberSaveRequestDTO memberSaveRequestDTO, Errors errors, Model model) {
        /* 검증 */
        if (errors.hasErrors()) {
            /* 회원가입 실패 시 입력 데이터 유지 */
            model.addAttribute("dto", memberSaveRequestDTO);
            /* 유효성 검사를 통과하지 못한 필드와 메세지 핸들링 */
            messageHandling(errors, model);
            /* 회원가입 페이지로 리턴 */
            return "/members/memberForm";
        }
        globalServcie.join(memberSaveRequestDTO);

        return "home";
    }

    /**
     * 로그인 페이지
     * @return
     */
    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }

    /**
     * 로그인 post
     * @param memberLoginDTO 로그인 정보
     * @return
     */
    @PostMapping("/login")
    public String login(@Valid MemberLoginDTO memberLoginDTO, Errors errors, Model model) {
        /* 검증 */
        if (errors.hasErrors()) {
            /* 로그인 실패 시 입력 데이터 유지 */
            model.addAttribute("dto", memberLoginDTO);
            /* 유효성 검사를 통과하지 못한 필드와 메세지 핸들링 */
            messageHandling(errors, model);
            /* 로그인 페이지로 리턴 */
            return "loginForm";
        }
        // TODO: login 로직 구현

        return "home";
    }
}
