package com.lamp.app.service;

import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;

import java.util.List;
import java.util.Map;

public interface MusicService {
    // 음악검색
    List<MusicDto> musicSearch(Map map);
    // 음악검색 즐겨찾기 좋아요 좋아요 수 초기화
    List<MusicDto> msBookmarkLikesBtn(Map map);

    //    음악차트
    // 음악차트 비로그인 시
    List<MusicDto> musicChart(Map map);
    // 음악차트 로그인 시
    List<MusicDto> musicChartSignin(Map map);

    // 즐겨찾기
    int bookmarkGetOne(Map map);
    int bookmarkRemoveOne(Map map);
    int bookmarkAddOne(Map map);
    List<MusicDto> bookmarkUserGet(Map map);

    // 좋아요
    int likesGetOne(Map map);
    int likesRemoveOne(Map map);
    int likesAddOne(Map map);
    Integer likesCnt(int likesNo);

    // 에디터 추천목록
    // 에디터 추천 제목
    EditorChoiceDto editor_choice_info(int no);
    List<MusicDto> editor_choice_list(int no);
    // 에디터 추천 목록 북마크 좋아요 좋아요 수 초기화
    List<MusicDto> eclBookmarkLikesBtn(Map map);


}
