package com.lamp.app.controller;

import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;
import com.lamp.app.domain.TodayMusicDto;
import com.lamp.app.service.MainService;
import com.lamp.app.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class MainController {
    @Autowired
    MainService mainService;
    @Autowired
    MusicService musicService;

    @RequestMapping("/")
    public String main(Model model, HttpSession session, HttpServletRequest request) throws ParseException {
        // 자동로그인
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie c : cookies) {

                if(c.getName().equals("id")) {
//                    System.out.println("c.getName() = " + c.getName());
//                    System.out.println("c.getValue() = " + c.getValue());
                    session.setAttribute("id", c.getValue());
                }
            }
        }

        // 헤더 프로필 이미지
        String id = String.valueOf(session.getAttribute("id"));
        if(id == null || id.equals("null") || id.equals("")) {}
        else {
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img" ,u_img);
        }

        // 오늘의 뮤직
        int todayNo = (int)getTodayNo();

        TodayMusicDto todayMusicDto = mainService.getTodayMusic(todayNo);
        model.addAttribute("todayMusicDto", todayMusicDto);

        // 년도별 차트(랜덤)
        int year = 1990 + (int)(Math.random() * 24);
//        int year = 2000;
        model.addAttribute("randomYear", year);

        if(id == null || id.equals("null") || id.equals("")) {
            List<MusicDto> yearMusicChart = mainService.getYearMusicChart(year);
            model.addAttribute("yearMusicChart", yearMusicChart);
        } else {
            Map map = new HashMap<>();
            map.put("id", id);
            map.put("year",year);
            List<MusicDto> yearMusicChart = mainService.yearBookmarkLikesBtn(map);
//            for(MusicDto m : yearMusicChart) {
//                System.out.println(m);
//            }
            model.addAttribute("yearMusicChart", yearMusicChart);
        }

        // 에디터 추천
        List<EditorChoiceDto> editorChoiceList = mainService.editorChoiceGetAll();
        EditorChoiceDto editorChoiceDto;
        for(int i=0; i<editorChoiceList.size(); i++) {
            editorChoiceDto = (EditorChoiceDto)editorChoiceList.get(i);
            // 에디터추천 목록 총갯수 입력
            int editorListTotalCnt = mainService.editor_choice_totalCnt(i+1);
            editorChoiceDto.setE_totalCnt(editorListTotalCnt);

            // 에디터추천 메인이미지
            List imgList = mainService.editorChoiceGetImg(editorChoiceDto.getE_no());
            editorChoiceDto.setM_albumimg(imgList);
        }
//        int chk = 0;
//        for(EditorChoiceDto ec:editorChoiceList) {
//            if(chk==0) {
//                chk = 1;
//                for(Object img:(ec.getM_albumimg())) {
//                    System.out.println(img);
//                }
//            }
//        }
        model.addAttribute("editorChoiceList", editorChoiceList);


        return "main";
    }

    private long getTodayNo() throws ParseException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date_str = sdf.format(date);
        date = sdf.parse(date_str);

        long today_number = (date.getTime() / 86400000) % 5 + 1;

        return today_number;
    }

    // 검색 미리보기
    @GetMapping("/search_lookahead")
    public ResponseEntity<List<List>> search_lookahead(String search_word) {
        try {
            List<MusicDto> title = mainService.search_lookahead_title(search_word);
            List<MusicDto> musician = mainService.search_lookahead_musician(search_word);
            List<MusicDto> album = mainService.search_lookahead_album(search_word);

//            for(MusicDto t:title) {
//                System.out.println(t);
//            }
//            System.out.println("musician = " + musician);
//            System.out.println("album = " + album);
            List list = new ArrayList<List>();
            list.add(title);
            list.add(musician);
            list.add(album);

            return new ResponseEntity<List<List>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<List>>(HttpStatus.BAD_REQUEST);
        }

    }
}
