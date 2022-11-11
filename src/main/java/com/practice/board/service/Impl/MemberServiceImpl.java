package com.practice.board.service.Impl;

import com.practice.board.domain.Member;
import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.dto.MemberResponseDTO;
import com.practice.board.repository.MemberRepository;
import com.practice.board.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원 중복체크
     * @param email 회원 이메일
     */
    private void validateDuplicateMember(String email) {
        memberRepository.findByEmail(email)
                .ifPresent((m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }));
    }

    @Override
    public Long join(MemberSaveRequestDTO memberSaveRequestDTO) {
        validateDuplicateMember(memberSaveRequestDTO.getEmail());   // 중복 회원 검증

        memberSaveRequestDTO.setPassword(passwordEncoder.encode(memberSaveRequestDTO.getPassword()));

        Member member = Member.builder()
                .email(memberSaveRequestDTO.getEmail())
                .username(memberSaveRequestDTO.getUsername())
                .password(memberSaveRequestDTO.getPassword())
                .build();

        return memberRepository.save(member).getId();
    }

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
}
