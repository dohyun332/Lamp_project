package com.lamp.app.service;

import com.lamp.app.dao.MainDao;
import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;
import com.lamp.app.domain.TodayMusicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MainServiceImpl implements MainService {
    @Autowired
    MainDao mainDao;

    // 헤더 검색 미리보기
    @Override
    public List<MusicDto> search_lookahead_title(String search_word) {
        return mainDao.search_lookahead_title(search_word);
    }
    @Override
    public List<MusicDto> search_lookahead_musician(String search_word) {
        return mainDao.search_lookahead_musician(search_word);
    }
    @Override
    public List<MusicDto> search_lookahead_album(String search_word) {
        return mainDao.search_lookahead_album(search_word);
    }

    // 헤더 프로필 이미지
    @Override
    public String getProfile_img(String u_id) {
        return mainDao.profile_img(u_id);
    }

    // 메인 오늘의 뮤직
    @Override
    public TodayMusicDto getTodayMusic(int t_no) {
        return mainDao.selectTodayMusic(t_no);
    }

    // 년도별 뮤직차트(랜덤년도별 10위)
    @Override
    public List<MusicDto> getYearMusicChart(int year) {
        return mainDao.selectYearMusicChart(year);
    }
    //    년도별 뮤직차트(좋아요, 즐겨찾기 초기화)
    @Override
    public List<MusicDto> yearBookmarkLikesBtn(Map map) {
        return mainDao.yearBookmarkLikesBtn(map);
    }

    // 메인 에디터 추천
    @Override
    public List<EditorChoiceDto> editorChoiceGetAll() {
        return mainDao.editorChoiceSelectAll();
    }
    @Override
    public int editor_choice_totalCnt(int no) {
        return mainDao.editor_choice_totalCnt(no);
    }
    @Override
    public List<MusicDto> editorChoiceGetImg(int no) {
        return mainDao.editorChoiceSelectImg(no);
    };

}
