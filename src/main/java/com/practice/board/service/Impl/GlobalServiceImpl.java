package com.practice.board.service.Impl;

import com.practice.board.domain.Member;
import com.practice.board.domain.Role;
import com.practice.board.dto.MemberSaveRequestDTO;
import com.practice.board.repository.MemberRepository;
import com.practice.board.service.GlobalServcie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GlobalServiceImpl implements GlobalServcie {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    /* 회원가입 시, 유효성 및 중복 검사 */
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
}