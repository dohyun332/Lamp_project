package com.lamp.app.service;

import com.lamp.app.dao.CommunityDao;
import com.lamp.app.domain.CommunityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommunityServiceImpl implements CommunityService {
    @Autowired
    CommunityDao communityDao;

    // 커뮤니티 게시판 리스트 가져오기
    // 게시물 총갯수
    @Override
    public int boardTotalCount() {
        return communityDao.boardTotalSelect();
    }
    // 게시물 10건 가져오기(게시글리스트 보기)
    @Override
    public List<CommunityDto> getBoardList(Map map) {
        return communityDao.selectBoardList(map);
    }

//    게시물 읽기
    @Override
    public CommunityDto readBoardOne(int boardNo) {
        communityDao.updateHits(boardNo);
        return communityDao.selectBoardOne(boardNo);
    }

    //게시글 쓰기
    @Override
    public int writeBoard(CommunityDto communityDto) {
        return communityDao.insertBoard(communityDto);
    }
    // 게시글 삭제
    @Override
    public int removeBoard(int c_no) {
        return communityDao.deleteBoard(c_no);
    }
    // 게시글 수정
    @Override
    public int modifyBoard(CommunityDto communityDto) {
        return communityDao.updateBoard(communityDto);
    }
    //    게시물 하나 가져오기
    @Override
    public CommunityDto getBoardOne(int boardNo) {
        return communityDao.selectBoardOne(boardNo);
    }
    // 게시글 검색
    @Override
    public List<CommunityDto> searchBoardList(Map map) {
        return communityDao.searchBoardList(map);
    }
    // 게시글 검색 총 갯수
    @Override
    public int searchTotalCount(Map map) {
        return communityDao.searchTotalCount(map);
    }


}
