package com.practice.board.controller;

import com.practice.board.dto.MemberLoginDTO;
import com.practice.board.dto.MemberResponseDTO;
import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.service.MemberService;
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
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 목록 조회
     *
     * @param model
     * @return 회원 목록 페이지
     */
    @GetMapping("/member/list")
    public String members(Model model) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }
}
