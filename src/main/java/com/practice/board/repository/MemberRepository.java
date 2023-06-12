package com.practice.board.repository;

import com.practice.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    /**
     * 이메일로 회원 찾기
     * @param email 회원 이메일
     * @return 회원 정보
     */
    Optional<Member> findByEmail(String email);

    /**
     * 유효성 검사 - 중복 체크
     * @param email 회원 이메일
     * @return 참/거짓
     */
    boolean existsByEmail(String email);
}
