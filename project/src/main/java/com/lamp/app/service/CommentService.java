package com.lamp.app.service;

import com.lamp.app.domain.CommentDto;

import java.util.List;

public interface CommentService {
    // 댓글 모두 불러오기
    List<CommentDto> getCommentAll(int boardNo);

    // 댓글 작성하기
    int saveComment(CommentDto commentDto);
    // 댓글 작성 후 원 댓글번호 업데이트해주기
    int modifyCc_comment(CommentDto commentDto);
    // 대댓글 작성하기
    int saveCcomment(CommentDto commentDto);

    // 댓글 삭제하기
    int removeComment(int cc_no);
    // 대댓글 보유 여부 확인
    int checkCcomment(int cc_no);
    // 댓글 수정하기
    int modifyComment(CommentDto commentDto);
    // 댓글 하나 가져오기
    CommentDto getCommentOne(int cc_no);
}
