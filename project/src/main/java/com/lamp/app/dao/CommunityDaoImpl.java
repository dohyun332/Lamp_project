package com.lamp.app.dao;

import com.lamp.app.domain.CommunityDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CommunityDaoImpl implements CommunityDao {

    @Autowired
    SqlSession sqlsession;

    // 게시물 총갯수
    @Override
    public int boardTotalSelect() {
        return sqlsession.selectOne("communityMapper.boardTotalCount");
    }
    // 게시물 10건 가져오기(게시글리스트 보기)
    @Override
    public List<CommunityDto> selectBoardList(Map map) {
        return sqlsession.selectList("communityMapper.selectBoardList", map);
    }

    // 게시물 읽기
    @Override
    public CommunityDto selectBoardOne(int boardNo) {
        return sqlsession.selectOne("communityMapper.selectBoardOne", boardNo);
    }
    // 조회수 증가
    @Override
    public int updateHits(int boardNo) {
        return sqlsession.update("communityMapper.updateHits", boardNo);
    }

    //게시글 쓰기
    @Override
    public int insertBoard(CommunityDto communityDto) {
        return sqlsession.insert("communityMapper.insertBoard", communityDto);
    }
    // 게시글 삭제
    @Override
    public int deleteBoard(int c_no) {
        return sqlsession.delete("communityMapper.deleteBoard", c_no);
    }

    // 게시글 수정
    @Override
    public int updateBoard(CommunityDto communityDto) {
        return sqlsession.update("communityMapper.updateBoard", communityDto);
    }

    // 게시글 검색
    @Override
    public List<CommunityDto> searchBoardList(Map map) {
        return sqlsession.selectList("communityMapper.searchBoardList", map);
    }

    // 게시글 검색 총 갯수
    @Override
    public int searchTotalCount(Map map) {
        return sqlsession.selectOne("communityMapper.searchTotalCount", map);
    }


}
