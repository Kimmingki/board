package com.practice.board.service.board;

import com.practice.board.domain.Board;
import com.practice.board.domain.Member;
import com.practice.board.dto.board.BoardWriteRequestDTO;
import com.practice.board.repository.BoardRepository;
import com.practice.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public Long saveBoard(BoardWriteRequestDTO boardWriteRequestDTO, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        Board result = Board.builder()
                .title(boardWriteRequestDTO.getTitle())
                .content(boardWriteRequestDTO.getContent())
                .member(member)
                .build();
        boardRepository.save(result);

        return result.getId();
    }
}
