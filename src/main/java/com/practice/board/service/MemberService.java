package com.practice.board.service;


import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.dto.MemberResponseDTO;

import java.util.List;

public interface MemberService {

    Long join(MemberSaveRequestDTO memberSaveRequestDTO);

    List<MemberResponseDTO> findMembers();
}
