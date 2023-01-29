package com.lamp.app.dao;

import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MusicDaoImpl implements MusicDao {

    @Autowired
    SqlSession sqlSession;

    // 음악검색 리스트
    @Override
    public List<MusicDto> musicSearch(Map map) {
        return sqlSession.selectList("musicMapper.musicSearch", map);
    }

    // 음악검색 즐겨찾기 좋아요 좋아요 수 초기화
    @Override
    public List<MusicDto> msBookmarkLikesBtn(Map map){
        return sqlSession.selectList("musicMapper.msBookmarkLikesBtn", map);
    }

    //    음악차트
    // 음악차트 비로그인 시
    @Override
    public List<MusicDto> musicChart(Map map) {
        return sqlSession.selectList("musicMapper.musicChart", map);
    }
    // 음악차트 로그인 시
    @Override
    public List<MusicDto> musicChartSignin(Map map) {
        return sqlSession.selectList("musicMapper.musicChartSignin", map);
    }

    // 즐겨찾기
    @Override
    public int bookmarkSelectOne(Map map) {
        return sqlSession.selectOne("musicMapper.bookmarkSelectOne", map);
    }
    @Override
    public int bookmarkDeleteOne(Map map) {
        return sqlSession.delete("musicMapper.bookmarkDeleteOne", map);
    }
    @Override
    public int bookmarkInsertOne(Map map) {
        return sqlSession.insert("musicMapper.bookmarkInsertOne", map);
    }
    @Override
    public List<MusicDto> bookmarkUserSelect(Map map) {
        return sqlSession.selectList("musicMapper.bookmarkUserSelect", map);
    }

    // 좋아요
    @Override
    public int likesSelectOne(Map map) {
        return sqlSession.selectOne("musicMapper.likesSelectOne", map);
    }
    @Override
    public int likesDeleteOne(Map map) {
        return sqlSession.delete("musicMapper.likesDeleteOne", map);
    }
    @Override
    public int likesInsertOne(Map map) {
        return sqlSession.insert("musicMapper.likesInsertOne", map);
    }
    @Override
    public Integer likesCnt(int likesNo) {
        return sqlSession.selectOne("musicMapper.likesCnt", likesNo);
    }

    // 에디터 추천 리스트
    // 에디터 추천 리스트 제목
    @Override
    public EditorChoiceDto editor_choice_info(int no){
        return sqlSession.selectOne("musicMapper.editor_choice_info", no);
    }
    @Override
    public List<MusicDto> editor_choice_list(int no) {
        return sqlSession.selectList("musicMapper.editor_choice_list", no);
    }
    // 에디터 추천 리스트 즐겨찾기 좋아요 좋아요수 초기화
    @Override
    public List<MusicDto> eclBookmarkLikesBtn(Map map) {
        return sqlSession.selectList("musicMapper.eclBookmarkLikesBtn", map);
    }


}
