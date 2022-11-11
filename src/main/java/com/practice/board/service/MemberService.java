package com.practice.board.service;


import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.dto.MemberResponseDTO;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Map;

public interface MemberService {

    /**
     * 회원 가입
     * @param memberSaveRequestDTO 회원 정보 DTO
     * @return 회원 id
     */
    Long join(MemberSaveRequestDTO memberSaveRequestDTO);

    /**
     * 회원 목록 조회
     * @return 회원 정보 목록
     */
    List<MemberResponseDTO> findMembers();

    /**
     * 회원가입 시, 유효성 및 중복 검사
     * @param errors
     * @return
     */
    Map<String, String> validateHandling(Errors errors);
}
