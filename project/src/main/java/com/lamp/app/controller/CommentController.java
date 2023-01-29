package com.lamp.app.controller;

import com.lamp.app.domain.CommentDto;
import com.lamp.app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    // 댓글 수정
    @PatchMapping("/modify")
    public ResponseEntity<Integer> modify2(HttpSession session, @RequestBody  CommentDto commentDto) {
        System.out.println(commentDto);
        String id = String.valueOf(session.getAttribute("id"));

        if(id == null || id.equals("null") || id.equals("")) {
            return new ResponseEntity<Integer>(0, HttpStatus.OK);
        }

        int cc_no = commentDto.getCc_no();
        CommentDto commentDtoDB;
        try {
            commentDtoDB = commentService.getCommentOne(cc_no);
            String comment_id = commentDtoDB.getCc_writer();
            if(!id.equals(comment_id)) {
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
            } else {
                LocalDateTime localDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String cc_modified = formatter.format(localDateTime);
                commentDto.setCc_modified(cc_modified);
                if(commentService.modifyComment(commentDto) != 1) {
                    throw new Exception();
                }
                System.out.println(commentDto);
                return new ResponseEntity<Integer>(2, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }
    }

    // 수정 버튼 클릭 시 작성자 확인
    @GetMapping("/modify")
    public ResponseEntity<Integer> modify1(HttpSession session, int cc_no) {
        String id = String.valueOf(session.getAttribute("id"));
        System.out.println("cc_no = " + cc_no);
        if(id == null || id.equals("null") || id.equals("")) {
            return new ResponseEntity<Integer>(0, HttpStatus.OK);
        }
        CommentDto commentDto;
        try {
            commentDto = commentService.getCommentOne(cc_no);
            String comment_id = commentDto.getCc_writer();
            if(!id.equals(comment_id)) {
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
         }

        return new ResponseEntity<Integer>(2, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<Integer> delete(HttpSession session, int cc_no) {
        String id = String.valueOf(session.getAttribute("id"));

        if(id == null || id.equals("null") || id.equals("")) {
            return new ResponseEntity<Integer>(0, HttpStatus.OK);
        }
        try {
            CommentDto commentDto = commentService.getCommentOne(cc_no);
            String comment_id = commentDto.getCc_writer();
            if(!id.equals(comment_id)) {
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
            } else {
                // 원댓글의 경우 대댓글이 있으면 삭제 불가
                if(commentDto.getCc_no() == commentDto.getCc_comment()) {
                    if(commentService.checkCcomment(cc_no) >= 2) {
                        return new ResponseEntity<Integer>(2, HttpStatus.OK);
                    }
                }
                if(commentService.removeComment(cc_no) != 1) {
                    throw new Exception();
                }
                return new ResponseEntity<Integer>(3, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }


    }

    // 댓글, 대댓글 작성
    @PostMapping("/write")
    public ResponseEntity<Integer> write(HttpSession session, @RequestBody CommentDto commentDto) {
        String id = String.valueOf(session.getAttribute("id"));

        if(id == null || id.equals("null") || id.equals("")) {
            return new ResponseEntity<Integer>(0, HttpStatus.OK);
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String cc_created = formatter.format(localDateTime);

        commentDto.setCc_writer(id);
        commentDto.setCc_created(cc_created);
        commentDto.setCc_modified(cc_created);
        // 받아온 cc_no은 cc_comment에 넣기 위해 받아온거임
        if(commentDto.getCc_no() != null) {
            commentDto.setCc_comment(commentDto.getCc_no());
            commentDto.setCc_no(null);
        }
        try {
            if(commentDto.getCc_comment() == null) {
                if(commentService.saveComment(commentDto) != 1) {
                    throw new Exception();
                }
                if(commentService.modifyCc_comment(commentDto) != 1){
                    throw new Exception();
                }
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
            } else {
                if(commentService.saveCcomment(commentDto) != 1){
                    throw new Exception();
                }
                return new ResponseEntity<Integer>(2, HttpStatus.OK);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/list")
    public ResponseEntity<List<CommentDto>> list(int boardNo) {
        System.out.println("boardNo = " + boardNo);
        try {
            List<CommentDto> list = commentService.getCommentAll(boardNo);

            return new ResponseEntity<List<CommentDto>>(list, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>>(HttpStatus.BAD_REQUEST);
        }

    }
}
