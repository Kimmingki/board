package com.practice.board.service.comment;

import com.practice.board.domain.Board;
import com.practice.board.domain.Comment;
import com.practice.board.domain.Member;
import com.practice.board.dto.comment.CommentRequestDTO;
import com.practice.board.dto.comment.CommentResponseDTO;
import com.practice.board.repository.BoardRepository;
import com.practice.board.repository.CommentRepository;
import com.practice.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public Long writeComment(CommentRequestDTO commentRequestDTO, Long boardId, String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("이메일이 존재하지 않습니다."));
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));
        Comment result = Comment.builder()
                .content(commentRequestDTO.getContent())
                .board(board)
                .member(member)
                .build();
        commentRepository.save(result);

        return result.getId();
    }

    @Override
    public CommentResponseDTO commentList(Long id) {
        Comment comment = commentRepository.findByBoard(id);

        return CommentResponseDTO.builder()
                .comment(comment)
                .build();
    }
}
