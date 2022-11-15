package com.practice.board.service.Impl;

import com.practice.board.domain.Member;
import com.practice.board.domain.Role;
import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.repository.MemberRepository;
import com.practice.board.security.PrincipalDetails;
import com.practice.board.service.GlobalService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GlobalServiceImpl implements GlobalService, UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /* 유효성 및 중복 검사 */
    @Transactional(readOnly = true)
    @Override
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        /* 유효성 및 중복 검사에 실패한 필드 목록을 받음 */
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }

        return validatorResult;
    }

    @Override
    public Long join(MemberSaveRequestDTO memberSaveRequestDTO) {
        memberSaveRequestDTO.setPassword(passwordEncoder.encode(memberSaveRequestDTO.getPassword()));

        Member member = Member.builder()
                .email(memberSaveRequestDTO.getEmail())
                .username(memberSaveRequestDTO.getUsername())
                .password(memberSaveRequestDTO.getPassword())
                .role(Role.USER)
                .build();

        return memberRepository.save(member).getId();
    }

    /**
     * 로그인 서비스
     * @param email the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일 입니다."));
//
//        MemberResponseDTO result = MemberResponseDTO.builder()
//                .member(member)
//                .build();

        return new PrincipalDetails(memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("존재하지 않는 이메일입니다.")));
    }
}
