package com.practice.board.repository;

import com.practice.board.domain.Image;
import com.practice.board.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    Image findByMember(Member member);
}
