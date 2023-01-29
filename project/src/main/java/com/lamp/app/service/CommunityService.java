package com.lamp.app.service;

import com.lamp.app.domain.CommunityDto;

import java.util.List;
import java.util.Map;

public interface CommunityService {
    // 커뮤니티 게시판 리스트 가져오기
    // 게시물 총갯수
    int boardTotalCount();
    // 게시물 10건 가져오기(게시글리스트 보기)
    List<CommunityDto> getBoardList(Map map);

    // 게시물 읽기
    CommunityDto readBoardOne(int boardNo);

    //게시글 쓰기
    int writeBoard(CommunityDto communityDto);
    // 게시글 삭제
    int removeBoard(int c_no);
    // 게시글 수정
    int modifyBoard(CommunityDto communityDto);
    // 게시글 하나 가져오기
    CommunityDto getBoardOne(int boardNo);
    // 게시글 검색
    List<CommunityDto> searchBoardList(Map map);
    // 게시글 검색 총 갯수
    int searchTotalCount(Map map);

}
