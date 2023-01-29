package com.lamp.app.controller;

import com.lamp.app.domain.EditorChoiceDto;
import com.lamp.app.domain.MusicDto;
import com.lamp.app.service.MainService;
import com.lamp.app.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/music")
public class MusicController {

    @Autowired
    MusicService musicService;
    @Autowired
    MainService mainService;

    @GetMapping("/musicSearch")
    public String searchList(Integer category, String search_word, Model model, HttpSession session) {
        // 헤더 프로필 이미지
        String id = String.valueOf(session.getAttribute("id"));
        if (id == null || id.equals("null") || id.equals("")) {
        } else {
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img", u_img);
        }

        if (category == null) category = 0;

        Map map = new HashMap();
        List list = new ArrayList<>();
        String[] arr = {"music", "musician", "album"};
        if (id == null || id.equals("null") || id.equals("")) {
            for (int i = 0; i < 3; i++) {
                map.put("category", category);
                map.put("search_cate", "m_" + arr[i]);
                map.put("search_word", search_word);
                list = musicService.musicSearch(map);
                model.addAttribute(arr[i] + "_list", list);
            }
        } else {
            for (int i = 0; i < 3; i++) {
                map.put("category", category);
                map.put("search_cate", "m_" + arr[i]);
                map.put("search_word", search_word);
                map.put("id", id);
                list = musicService.msBookmarkLikesBtn(map);
                model.addAttribute(arr[i] + "_list", list);
            }
        }
        return "music_search";
    }

    @GetMapping("/musicChart")
    public String musicSearch(HttpSession session, Model model, Integer year, Integer category, Integer rank) {
        if(year == null) {
            LocalDate localDate = LocalDate.now();
            int lastYear = localDate.getYear() - 1;
            year=lastYear;
        }
        if(category == null) category=0;
        if(rank == null) rank=0;

        Map map = new HashMap();
        List<MusicDto> list = new ArrayList<MusicDto>();
        String id = String.valueOf(session.getAttribute("id"));
        if(id == null || id.equals("null") || id.equals("")) {
            map.put("year", year);
            map.put("category", category);
            map.put("rank", rank);
            list = musicService.musicChart(map);
        } else {
            // 헤더 프로필 이미지
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img", u_img);

            map.put("year", year);
            map.put("category", category);
            map.put("rank", rank);
            map.put("id", id);
            list = musicService.musicChartSignin(map);
        }
//        System.out.println("year, category, rank = " + year + ", " + category+ ", " + rank);
//        for(MusicDto m:list) {
//            System.out.println(m);
//        }

        model.addAttribute("music_list", list);

        return "music_chart";
    }


    @GetMapping("/musicBookmark")
    public String searchList(Model model, HttpSession session, Integer category) {
        if(category == null) category = 0;
        String id = String.valueOf(session.getAttribute("id"));
        if (id == null || id.equals("null") || id.equals("")) {
            return "redirect:/";
        } else {
            // 프로필 이미지 가져오기
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img" ,u_img);

            // 북마크 음악가져오기
            Map map = new HashMap<>();
            map.put("id", id);
            map.put("category", category);
            List<MusicDto> list = musicService.bookmarkUserGet(map);
            model.addAttribute("music_list", list);
            return "music_bookmark";
        }
    }

    @PatchMapping("/bookmarkBtn/{bookmarkNo}")
    @ResponseBody
    public ResponseEntity<Integer> likesBtn(HttpSession session, @PathVariable int bookmarkNo) {

        String id = String.valueOf(session.getAttribute("id"));
        System.out.println("id, bookmarkNo = " + id + ", " + bookmarkNo);

        if(id == null || id.equals("null") || id.equals("")) {
            return new ResponseEntity<Integer>(2, HttpStatus.OK);
        } else {
            Map map = new HashMap();
            map.put("id", id);
            map.put("bookmarkNo", bookmarkNo);

            int bookmarkCnt = musicService.bookmarkGetOne(map);
            System.out.println("bookmarkCnt" + bookmarkCnt);
            if(bookmarkCnt == 0) {
                musicService.bookmarkAddOne(map);
                return new ResponseEntity<Integer>(0, HttpStatus.OK);
            } else if (bookmarkCnt == 1) {
                musicService.bookmarkRemoveOne(map);
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
            } else {
                return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PatchMapping("/likesBtn/{likesNo}")
    @ResponseBody
    public ResponseEntity<Integer[]> bookmarkBtn(HttpSession session, @PathVariable int likesNo) {

        String id = String.valueOf(session.getAttribute("id"));
        System.out.println("id, bookmarkNo = " + id + ", " + likesNo);

        Integer[] arr = new Integer[2];

        if(id == null || id.equals("null") || id.equals("")) {
            arr[0] = 2; // 로그인 경고, 2번 전송
            return new ResponseEntity<Integer[]>(arr, HttpStatus.OK);
        } else {
            Map map = new HashMap();
            map.put("id", id);
            map.put("likesNo", likesNo);

            int likesCnt = musicService.likesGetOne(map);
            System.out.println("likesCnt = " + likesCnt);
            if(likesCnt == 0) {
                int addResult = musicService.likesAddOne(map);
                System.out.println("addResult1 = " + addResult);
                arr[0] = 0; // 등록되어있는곡이 없으니 0 입력
                arr[1] = musicService.likesCnt(likesNo);
                System.out.println("totalCnt2 = " + arr[1]);

                return new ResponseEntity<Integer[]>(arr, HttpStatus.OK);
            } else if (likesCnt == 1) {
                int delResult1 = musicService.likesRemoveOne(map);
                System.out.println("delResult1 = " + delResult1);

                arr[0] = 1; // 등록되어있는곡이 1곡, 1 입력
                arr[1] = musicService.likesCnt(likesNo);
                System.out.println("totalCnt2 = " + arr[1]);
                return new ResponseEntity<Integer[]>(arr, HttpStatus.OK);
            } else { // 같은곡이 2곡이상 등록 시 오류
                return new ResponseEntity<Integer[]>(HttpStatus.BAD_REQUEST);
            }
        }
    }

    @GetMapping("/editorChoice/{editorChoice_no}")
    public String editorChoiceList(@PathVariable int editorChoice_no, Model model, HttpSession session) {
        // 헤더 프로필 이미지
        String id = String.valueOf(session.getAttribute("id"));
        if(id == null || id.equals("null") || id.equals("")) {}
        else {
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img" ,u_img);
        }
// 에디터 추천 타이틀
        EditorChoiceDto editorChoiceInfo = musicService.editor_choice_info(editorChoice_no);
        model.addAttribute("editorChoiceTitle", editorChoiceInfo.getE_theme());

//        에디터 추천 음악 리스트
        List<MusicDto> editorChoiceList = null;
        if(id == null || id.equals("null") || id.equals("")) {
            editorChoiceList = musicService.editor_choice_list(editorChoice_no);
        } else {
            Map map = new HashMap<>();
            map.put("no", editorChoice_no);
            map.put("id", id);
            editorChoiceList = musicService.eclBookmarkLikesBtn(map);
        }
        model.addAttribute("editorChoiceList", editorChoiceList);
        for(MusicDto m:editorChoiceList) {
            System.out.println(m);
        }
        System.out.println();

        return "editor_choice";
    }
}
