package com.practice.board.service;


import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.dto.MemberResponseDTO;

import java.util.List;

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
}
