package com.lamp.app.service;

import com.lamp.app.dao.CommentDao;
import com.lamp.app.domain.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    // 댓글 모두 불러오기
    @Override
    public List<CommentDto> getCommentAll(int boardNo) {
        return commentDao.selectCommentAll(boardNo);
    }

    // 댓글 작성하기
    @Override
    public int saveComment(CommentDto commentDto) {
        return commentDao.insertComment(commentDto);
    }
    // 댓글 작성 후 원 댓글번호 업데이트해주기
    @Override
    public int modifyCc_comment(CommentDto commentDto) {
        return commentDao.updateCc_comment(commentDto);
    }
    // 대댓글 작성하기
    @Override
    public int saveCcomment(CommentDto commentDto) {
        return commentDao.insertCcomment(commentDto);
    }

    // 댓글 삭제하기
    @Override
    public int removeComment(int cc_no) {
        return commentDao.deleteComment(cc_no);
    }
    // 대댓글 보유 여부 확인
    @Override
    public int checkCcomment(int cc_no) {
        return commentDao.checkCcomment(cc_no);
    }
    // 댓글 수정하기
    @Override
    public int modifyComment(CommentDto commentDto) {
        return commentDao.updateComment(commentDto);
    }
    // 댓글 하나 가져오기
    @Override
    public CommentDto getCommentOne(int cc_no) {
        return commentDao.selectCommentOne(cc_no);
    }
}
