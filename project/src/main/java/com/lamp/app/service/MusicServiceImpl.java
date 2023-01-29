package com.lamp.app.service;

import com.lamp.app.dao.MusicDao;
import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    MusicDao musicDao;

    // 음악검색 리스트
    @Override
    public List<MusicDto> musicSearch(Map map) {
        return musicDao.musicSearch(map);
    }

    // 음악검색 즐겨찾기 좋아요 좋아요 수 초기화
    @Override
    public List<MusicDto> msBookmarkLikesBtn(Map map){
        return musicDao.msBookmarkLikesBtn(map);
    }

    //    음악차트
    // 음악차트 비로그인 시
    @Override
    public List<MusicDto> musicChart(Map map) {
        return musicDao.musicChart(map);
    }
    // 음악차트 로그인 시
    @Override
    public List<MusicDto> musicChartSignin(Map map) {
        return musicDao.musicChartSignin(map);
    }

    // 즐겨찾기
    @Override
    public int bookmarkGetOne(Map map) {
        return musicDao.bookmarkSelectOne(map);
    }
    @Override
    public int bookmarkRemoveOne(Map map) {
        return musicDao.bookmarkDeleteOne(map);
    }
    @Override
    public int bookmarkAddOne(Map map) {
        return musicDao.bookmarkInsertOne(map);
    }
    @Override
    public List<MusicDto> bookmarkUserGet(Map map) {
        return musicDao.bookmarkUserSelect(map);
    }

    // 좋아요
    @Override
    public int likesGetOne(Map map) {
        return musicDao.likesSelectOne(map);
    }
    @Override
    public int likesRemoveOne(Map map) {
        return musicDao.likesDeleteOne(map);
    }
    @Override
    public int likesAddOne(Map map) {
        return musicDao.likesInsertOne(map);
    }
    @Override
    public Integer likesCnt(int likesNo) {
        return musicDao.likesCnt(likesNo);
    }

    // 에디터 추천 리스트
    // 에디터 추천 리스트 제목
    @Override
    public EditorChoiceDto editor_choice_info(int no) {
        return musicDao.editor_choice_info(no);
    }
    @Override
    public List<MusicDto> editor_choice_list(int no) {
        return musicDao.editor_choice_list(no);
    }
    // 에디터 추천 리스트 즐겨찾기 좋아요 좋아요수 초기화
    @Override
    public List<MusicDto> eclBookmarkLikesBtn(Map map) {
        return musicDao.eclBookmarkLikesBtn(map);
    }


}
