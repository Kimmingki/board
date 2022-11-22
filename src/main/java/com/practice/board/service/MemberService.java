package com.practice.board.service;


import com.practice.board.dto.MemberResponseDTO;

import java.util.List;

public interface MemberService {

    /**
     * 회원 목록 조회
     * @return 회원 정보 목록
     */
    List<MemberResponseDTO> findMembers();

    /**
     * 회원 정보 조회
     * @return 회원 정보
     */
    MemberResponseDTO findMember(String email);
}
