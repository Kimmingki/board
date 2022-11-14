package com.practice.board.service;

import com.practice.board.dto.MemberLoginDTO;
import com.practice.board.dto.MemberResponseDTO;
import com.practice.board.dto.MemberSaveRequestDTO;
import org.springframework.validation.Errors;

import java.util.Map;

public interface GlobalServcie {

    /**
     * 회원가입 시, 유효성 및 중복 검사
     * @param errors
     * @return
     */
    Map<String, String> validateHandling(Errors errors);

    /**
     * 회원가입
     * @param memberSaveRequestDTO 회원 정보 DTO
     * @return 회원 id
     */
    Long join(MemberSaveRequestDTO memberSaveRequestDTO);


    /**
     * 로그인
     * @param memberLoginDTO 로그인 정보
     * @return
     */
    MemberResponseDTO login(MemberLoginDTO memberLoginDTO);
}
