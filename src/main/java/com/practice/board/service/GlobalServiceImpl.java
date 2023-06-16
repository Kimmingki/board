package com.practice.board.service;

import com.practice.board.domain.Image;
import com.practice.board.domain.Member;
import com.practice.board.domain.Role;
import com.practice.board.dto.member.MemberSaveRequestDTO;
import com.practice.board.repository.ImageRepository;
import com.practice.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GlobalServiceImpl implements GlobalService {

    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final PasswordEncoder passwordEncoder;

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
    public void messageHandling(Errors errors, Model model) {
        Map<String, String> validatorResult = validateHandling(errors);
        for (String key : validatorResult.keySet()) {
            model.addAttribute(key, validatorResult.get(key));
        }
    }

    @Override
    @Transactional
    public Long join(MemberSaveRequestDTO memberSaveRequestDTO) {
        memberSaveRequestDTO.setPassword(passwordEncoder.encode(memberSaveRequestDTO.getPassword()));

        Member member = Member.builder()
                .email(memberSaveRequestDTO.getEmail())
                .username(memberSaveRequestDTO.getUsername())
                .password(memberSaveRequestDTO.getPassword())
                .role(Role.ROLE_USER)
                .build();

        Image image = Image.builder()
                .url("/profileImages/anonymous.png")
                .member(member)
                .build();

        Long id = memberRepository.save(member).getId();
        imageRepository.save(image);

        return id;
    }
}
