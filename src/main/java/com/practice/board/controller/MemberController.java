package com.practice.board.controller;

import com.practice.board.dto.member.MemberPasswordUpdateDTO;
import com.practice.board.dto.member.MemberResponseDTO;
import com.practice.board.dto.member.MemberUsernameUpdateDTO;
import com.practice.board.service.GlobalService;
import com.practice.board.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/member")
public class MemberController {

    private final GlobalService globalService;
    private final MemberService memberService;

    /**
     * 회원 목록 조회
     *
     * @param model
     * @return 회원 목록 페이지
     */
    @GetMapping("/list")
    public String members(Model model) {
        List<MemberResponseDTO> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "/members/memberList";
    }

    /**
     * 회원 정보 조회
     * @param model
     * @param authentication 인증 정보
     * @return 회원 정보 페이지
     */
    @GetMapping("/info")
    public String memberInfo(Model model, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);

        return "/members/info";
    }

    /**
     * 회원 이름 변경
     * @param model
     * @param authentication 인증 정보
     * @return 회원 이름 변경 페이지
     */
    @GetMapping("/update/username")
    public String updateUsernameForm(Model model, Authentication authentication) {
        if (authentication == null) {
            return "/home";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        MemberResponseDTO member = memberService.findMember(userDetails.getUsername());
        model.addAttribute("member", member);

        return "/members/updateUsername";
    }

    /**
     * 회원 이름 변경 post
     * @param memberUsernameUpdateDTO
     * @param errors
     * @param model
     * @return 회원 정보 페이지
     */
    @PostMapping("/update/username")
    public String updateUsername(@Valid MemberUsernameUpdateDTO memberUsernameUpdateDTO, Errors errors, Model model, Authentication authentication) {
        if (authentication == null) {
            return "/home";
        }

        if (errors.hasErrors()) {
            model.addAttribute("member", memberUsernameUpdateDTO);
            globalService.messageHandling(errors, model);
            return "/members/updateUsername";
        }

        memberService.updateMemberUsername(memberUsernameUpdateDTO);

        return "redirect:/member/info";
    }

    /**
     * 회원 비밀번호 변경
     * @param model
     * @param authentication 인증 정보
     * @return 회원 비밀번호 변경 페이지
     */
    @GetMapping("/update/password")
    public String updatePasswordForm(Model model, Authentication authentication) {
        if (authentication == null) {
            return "/home";
        }

        return "/members/updatePassword";
    }

    @PostMapping("/update/password")
    public String updatePassword(@Valid MemberPasswordUpdateDTO memberPasswordUpdateDTO, Errors errors, Model model, Authentication authentication) {
        if (authentication == null) {
            return "/home";
        }

        if (errors.hasErrors()) {
            globalService.messageHandling(errors, model);
            return "/members/updatePassword";
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        memberService.updateMemberPassword(memberPasswordUpdateDTO, userDetails.getUsername());

        return null;
    }

}
