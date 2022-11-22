package com.practice.board.service.Impl;

import com.practice.board.domain.Member;
import com.practice.board.dto.MemberResponseDTO;
import com.practice.board.repository.MemberRepository;
import com.practice.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public List<MemberResponseDTO> findMembers() {
        List<Member> all = memberRepository.findAll();
        List<MemberResponseDTO> members = new ArrayList<>();

        for (Member member: all) {
            MemberResponseDTO build = MemberResponseDTO.builder()
                    .member(member)
                    .build();
            members.add(build);
        }

        return members;
    }

    @Override
    public MemberResponseDTO findMember(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        MemberResponseDTO result = MemberResponseDTO.builder()
                .member(member)
                .build();

        return result;
    }
}
