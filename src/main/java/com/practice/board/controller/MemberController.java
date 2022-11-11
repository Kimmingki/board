package com.practice.board.controller;

import com.practice.board.dto.MemberResponseDTO;
import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * Home 화면
     * @return 홈페이지
     */
    @GetMapping("/")
    public String Home() {
        return "home";
    }

    /**
     * 회원 목록 조회
     * @param model
     * @return 회원 목록 페이지
     */
    @GetMapping("/members")
    public String members(Model model) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }

    /**
     * 회원 가입
     * @return 회원 가입 페이지
     */
    @GetMapping("/members/new")
    public String createMemberForm() {
        return "members/createMemberForm";
    }

    /**
     * 회원 가입 post
     * @param memberSaveRequestDTO 회원 정보
     * @return 홈페이지
     */
    @PostMapping("/members/new")
    public String createMember(MemberSaveRequestDTO memberSaveRequestDTO) {
        Long memberId = memberService.join(memberSaveRequestDTO);
        return "home";
    }
}
