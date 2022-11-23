package com.practice.board.service.member;

import com.practice.board.domain.Member;
import com.practice.board.dto.member.MemberPasswordUpdateDTO;
import com.practice.board.dto.member.MemberResponseDTO;
import com.practice.board.dto.member.MemberUsernameUpdateDTO;
import com.practice.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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

    @Override
    public Long updateMemberUsername(MemberUsernameUpdateDTO memberUsernameUpdateDTO) {
        Member member = memberRepository.findByEmail(memberUsernameUpdateDTO.getEmail()).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));

        member.updateUsername(memberUsernameUpdateDTO.getUsername());
        memberRepository.save(member);

        return member.getId();
    }

    @Override
    public Long updateMemberPassword(MemberPasswordUpdateDTO memberPasswordUpdateDTO, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));


        return null;
    }
}
