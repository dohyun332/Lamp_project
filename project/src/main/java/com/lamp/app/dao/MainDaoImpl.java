package com.lamp.app.dao;

import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;
import com.lamp.app.domain.TodayMusicDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MainDaoImpl implements MainDao {
    @Autowired
    SqlSession sqlSession;

    // 헤더 검색 미리보기
    @Override
    public List<MusicDto> search_lookahead_title(String search_word) {
        return sqlSession.selectList("mainMapper.search_lookahead_title", search_word);
    }
    public List<MusicDto> search_lookahead_musician(String search_word) {
        return sqlSession.selectList("mainMapper.search_lookahead_musician", search_word);
    }
    public List<MusicDto> search_lookahead_album(String search_word) {
        return sqlSession.selectList("mainMapper.search_lookahead_album", search_word);
    }

    // 메인 오늘의 음악
    @Override
    public TodayMusicDto selectTodayMusic(int t_no) {
        return sqlSession.selectOne("mainMapper.todayMusic", t_no);
    }

    // 프로필 이미지
    @Override
    public String profile_img(String u_id) {
        return sqlSession.selectOne("mainMapper.profile_img", u_id);
    }

    // 년도별 뮤직차트(랜덤년도별 10위)
    @Override
    public List<MusicDto> selectYearMusicChart(int year) {
        return sqlSession.selectList("mainMapper.year_music_chart", year);
    }
    // 년도별 뮤직차트 좋아요 즐겨찾기 초기화

    @Override
    public List<MusicDto> yearBookmarkLikesBtn(Map map) {
        return sqlSession.selectList("mainMapper.yearBookmarkLikesBtn", map);
    }

    // 메인 에디터 추천
    @Override
    public List<EditorChoiceDto> editorChoiceSelectAll() {
        return sqlSession.selectList("mainMapper.editor_choice");
    };
    @Override
    public int editor_choice_totalCnt(int no) {
        return sqlSession.selectOne("mainMapper.editor_choice_totalCnt", no);
    }
    @Override
    public List<MusicDto> editorChoiceSelectImg(int no) {
        return sqlSession.selectList("mainMapper.editor_choice_main", no);
    }

}