package com.practice.board.service.Impl;

import com.practice.board.domain.Member;
import com.practice.board.dto.MemberFormDTO;
import com.practice.board.repository.MemberRepository;
import com.practice.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long join(MemberFormDTO memberFormDTO) {
        Member member = Member.builder()
                .email(memberFormDTO.getEmail())
                .username(memberFormDTO.getUsername())
                .password(memberFormDTO.getPassword())
                .build();

        return memberRepository.save(member).getId();
    }
}
