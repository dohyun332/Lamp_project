package com.lamp.app.controller;

import com.lamp.app.domain.CommunityDto;
import com.lamp.app.domain.Pagination;
import com.lamp.app.service.CommunityService;
import com.lamp.app.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    CommunityService communityService;
    @Autowired
    MainService mainService;

    // 게시글 검색
    @GetMapping("/search")
    public String search(Model model, HttpSession session, String search_category, String search_word, Integer currPage, Integer pageSize) {
        String id = String.valueOf(session.getAttribute("id"));
        if(!(id == null || id.equals("null") || id.equals(""))) {
            // 유저 프로필 이미지
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img", u_img);
        }
        try {
            System.out.println("info = " + search_category+ ","+search_word+ ","+currPage+","+pageSize);

            if(currPage == null) currPage = 1;
            if(pageSize == null) pageSize = 10;

            Map map = new HashMap();
            map.put("search_category", search_category);
            map.put("search_word", search_word);
            map.put("startNo", (currPage-1)*pageSize);
            map.put("getSize", pageSize);

            List<CommunityDto> list = communityService.searchBoardList(map);
//            for(CommunityDto cd:list) {
//                System.out.println(cd);
//            }

            int totalCount = communityService.searchTotalCount(map);
            System.out.println("totalCount = " + totalCount);
            Pagination pagination = new Pagination(totalCount, currPage, pageSize);
            System.out.println(pagination);

            model.addAttribute("community_list", list);
            model.addAttribute("pagination", pagination);

        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

        return "music_board_search";
    }

    // 게시글 쓰기 저장
    @PostMapping("/write")
    public String writeSave(CommunityDto communityDto, HttpSession session) {
        String id = String.valueOf(session.getAttribute("id"));
        communityDto.setC_writer(id);
        // 시간 설정
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String c_created = formatter.format(localDateTime);

        communityDto.setC_created(c_created);
        communityDto.setC_modified(c_created);

        try {
            if(communityService.writeBoard(communityDto) != 1) {
                throw new Exception();
            }
        } catch(Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "redirect:/community/list";
    }

    // 게시글 쓰기 페이지 이동
    @GetMapping("/write")
    public String writePage(Model model, HttpSession session) {
        String id = String.valueOf(session.getAttribute("id"));
        if(id == null || id.equals("null") || id.equals("")) {
            return "redirect:/";
        }
        // 유저 프로필 이미지
        String u_img = mainService.getProfile_img(id);
        model.addAttribute("u_img" ,u_img);

        return "music_board_write";
    }

    // 게시글 수정
    @PostMapping("/modify")
    public String modifySave(Integer boardNo, HttpSession session, CommunityDto communityDto) {
        String id = String.valueOf(session.getAttribute("id"));
        try {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String now = formatter.format(localDateTime);
            communityDto.setC_modified(now);
            communityDto.setC_no(boardNo);

            // id 체크용
            CommunityDto communityDtoChk = communityService.getBoardOne(boardNo);
            if(communityDtoChk.getC_writer().equals(id)) {
                if(communityService.modifyBoard(communityDto) != 1) {
                    throw new Exception();
                }
            } else {
                return "redirect:/community/list";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

        return "redirect:/community/read?boardNo=" + boardNo;
    }

    @GetMapping("/modify")
    public String modifyPage(HttpSession session, Model model, Integer boardNo) {
        String id = String.valueOf(session.getAttribute("id"));
        if(id == null || id.equals("null") || id.equals("")) {
            return "redirect:/";
        }
        try {
            // 유저 프로필 이미지
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img" ,u_img);

            CommunityDto communityDto = communityService.getBoardOne(boardNo);
            System.out.println("boardNo = " + boardNo);
            System.out.println("id = " + id + ", " + communityDto.getC_writer());
            if(!communityDto.getC_writer().equals(id)) {
                return "redirect:/community/list";
            }
            model.addAttribute("communityDto", communityDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

        return "music_board_modify";
    }

    // 게시글 삭제
    @GetMapping("/delete")
    public String delete(Integer boardNo, HttpSession session) {
        System.out.println(boardNo);
        String id = String.valueOf(session.getAttribute("id"));
        if(id == null || id.equals("null") || id.equals("")) {
            return "redirect:/community/list";
        }
        try {
            CommunityDto communityDto = communityService.getBoardOne(boardNo);
            if(communityDto.getC_writer().equals(id)) {
                if(communityService.removeBoard(boardNo) != 1) {
                    throw new Exception();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
        return "redirect:/community/list";
    }


    // 게시글 읽기
    @GetMapping("/read")
    public String read(Model model, HttpSession session, Integer boardNo) {
        String id = String.valueOf(session.getAttribute("id"));
        if(!(id == null || id.equals("null") || id.equals(""))) {
            // 유저 프로필 이미지
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img" ,u_img);
        }

        try {
            CommunityDto communityDto = communityService.readBoardOne(boardNo);
            model.addAttribute("communityDto", communityDto);
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }

        return "music_board_read";
    }

    // 게시글목록 불러오기
    @GetMapping("/list")
    public String list(Model model, HttpServletRequest request, Integer currPage, Integer pageSize) {
        HttpSession session = request.getSession();
        String id = String.valueOf(session.getAttribute("id"));
        if(!(id == null || id.equals("null") || id.equals(""))) {
            // 유저 프로필 이미지
            String u_img = mainService.getProfile_img(id);
            model.addAttribute("u_img" ,u_img);
        }

        try {
            if(currPage == null) currPage=1;
            if(pageSize == null) pageSize=10;

            int totalCount = communityService.boardTotalCount();

            Pagination pagination = new Pagination(totalCount, currPage, pageSize);

            Map map = new HashMap();
            map.put("startNo", (currPage-1)*pageSize);
            map.put("getSize", pageSize);
            List list = communityService.getBoardList(map);

            model.addAttribute("pagination", pagination);
            model.addAttribute("community_list", list);

            return "music_board";

        } catch(Exception e) {
            e.printStackTrace();
            return "0";
        }

    }
}
