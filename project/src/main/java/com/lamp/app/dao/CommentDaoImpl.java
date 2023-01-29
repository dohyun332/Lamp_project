package com.lamp.app.dao;

import com.lamp.app.domain.CommentDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {

    @Autowired
    SqlSession sqlSession;

    // 댓글 모두 불러오기
    @Override
    public List<CommentDto> selectCommentAll(int boardNo) {
        return sqlSession.selectList("commentMapper.selectCommentAll", boardNo);
    }

    // 댓글 작성하기
    @Override
    public int insertComment(CommentDto commentDto) {
        return sqlSession.insert("commentMapper.insertComment", commentDto);
    }
    // 댓글 작성 후 원 댓글번호 업데이트해주기
    @Override
    public int updateCc_comment(CommentDto commentDto) {
        return sqlSession.update("commentMapper.updateCc_comment", commentDto);
    }
    // 대댓글 작성하기
    @Override
    public int insertCcomment(CommentDto commentDto) {
        return sqlSession.insert("commentMapper.insertCcomment", commentDto);
    }

    // 댓글 삭제하기
    @Override
    public int deleteComment(int cc_no) {
        return sqlSession.delete("commentMapper.deleteComment", cc_no);
    }
    // 대댓글 보유 여부 확인
    @Override
    public int checkCcomment(int cc_no) {
        return sqlSession.selectOne("commentMapper.checkCcomment", cc_no);
    }
    // 댓글 수정하기
    @Override
    public int updateComment(CommentDto commentDto) {
        return sqlSession.update("commentMapper.updateComment", commentDto);
    }
    // 댓글 하나 가져오기
    @Override
    public CommentDto selectCommentOne(int cc_no) {
        return sqlSession.selectOne("commentMapper.selectCommentOne", cc_no);
    }
}
