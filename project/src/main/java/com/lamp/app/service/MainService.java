package com.lamp.app.service;

import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;
import com.lamp.app.domain.TodayMusicDto;

import java.util.List;
import java.util.Map;

public interface MainService {
    // 헤더 검색 미리보기
    List<MusicDto> search_lookahead_title(String search_word);
    List<MusicDto> search_lookahead_musician(String search_word);
    List<MusicDto> search_lookahead_album(String search_word);

    // 헤더 프로필 이미지
    String getProfile_img(String u_id);

    // 메인 오늘의 뮤직
    TodayMusicDto getTodayMusic(int t_no);

    // 년도별 뮤직차트(랜덤년도별 10위)
    List<MusicDto> getYearMusicChart(int year);
//    년도별 뮤직차트(좋아요, 즐겨찾기 초기화)
    List<MusicDto> yearBookmarkLikesBtn(Map map);

    // 메인 에디터 추천
    List<EditorChoiceDto> editorChoiceGetAll();
    int editor_choice_totalCnt(int no);
    List<MusicDto> editorChoiceGetImg(int no);
}
