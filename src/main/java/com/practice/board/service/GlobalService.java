package com.practice.board.service;

import com.practice.board.dto.member.MemberSaveRequestDTO;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;

import java.util.Map;

public interface GlobalService {

    /**
     * 회원가입 시, 유효성 및 중복 검사
     * @param errors
     * @return
     */
    Map<String, String> validateHandling(Errors errors);

    /**
     * 중복 검사 및 유효성 검사 에러 메세지 핸들링
     * @param errors
     * @param model
     */
    void messageHandling(Errors errors, Model model);

    /**
     * 회원가입
     * @param memberSaveRequestDTO 회원 정보 DTO
     * @return 회원 id
     */
    Long join(MemberSaveRequestDTO memberSaveRequestDTO);
}
