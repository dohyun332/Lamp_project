package com.lamp.app.dao;

import com.lamp.app.domain.CommunityDto;

import java.util.List;
import java.util.Map;

public interface CommunityDao {

    // 게시물 총갯수
    int boardTotalSelect();
    // 게시물 10건 가져오기(게시글리스트 보기)
    List<CommunityDto> selectBoardList(Map map);

    // 게시물 읽기
    CommunityDto selectBoardOne(int boardNo);
    // 조회수 증가
    int updateHits(int boardNo);
    //게시글 쓰기
    int insertBoard(CommunityDto communityDto);
    // 게시글 삭제
    int deleteBoard(int c_no);
    // 게시글 수정
    int updateBoard(CommunityDto communityDto);
    // 게시글 검색
    List<CommunityDto> searchBoardList(Map map);
    // 게시글 검색 총 갯수
    int searchTotalCount(Map map);

    // 댓글
}
