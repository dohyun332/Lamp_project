package com.lamp.app.controller;

import com.lamp.app.domain.UserDto;
import com.lamp.app.service.ProfileImgService;
import com.lamp.app.service.UserService;
import org.checkerframework.checker.units.qual.C;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.CookieGenerator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    ProfileImgService profileImgService;

    @GetMapping("/signin")
    public String signinForm(HttpServletRequest request) {
//        로그인전페이지로 세션이동
//        String toUrl = request.getHeader("referer");;
//        System.out.println(toUrl);
//        HttpSession session = request.getSession();
//        session.setAttribute("toUrl", toUrl);

        return "signin";
    }

    @PostMapping("/signin")
    public String signin(String u_id, String u_pw, boolean remember_userInfo, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String msg = null;

        if(!signinChk(u_id, u_pw)) {
            msg = "잘못된 아이디 또는 비밀번호 입니다.";
            msg = URLEncoder.encode(msg, "utf-8");
            return "redirect:/user/signin?msg=" + msg;
        }

        // 쿠키
//        Cookie cookie = new Cookie("id", u_id);
//        cookie.setDomain("localhost");
//        cookie.setPath("/");
//        cookie.setMaxAge(60*60*24*7);
//        if(!remember_userInfo) {
//            cookie.setMaxAge(0);
//        }
//        cookie.setHttpOnly(true);
//        response.addCookie(cookie);
        CookieGenerator cookieGenerator = new CookieGenerator();
        cookieGenerator.setCookieName("id");
        cookieGenerator.setCookieMaxAge(60*60*24*7);
        if(!remember_userInfo) {
            cookieGenerator.setCookieMaxAge(0);
        }
        cookieGenerator.addCookie(response, u_id);

        // 세션
        HttpSession session = request.getSession();
        session.setAttribute("id", u_id);

        // 마지막 로그인 날짜 업데이트
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String now = localDateTime.format(formatter);

        Map map = new HashMap();
        map.put("u_id", u_id);
        map.put("u_lastlogin", now);
        userService.lastLoginRenew(map);

        // 요청온 페이지로 반환
        String toUrl = String.valueOf(session.getAttribute("toUrl"));

        if(toUrl==null || toUrl.equals("null") || toUrl.equals("")) toUrl="";
        session.removeAttribute("toUrl");

        return "redirect:/" + toUrl;
    }

    private boolean signinChk(String u_id, String u_pw) {
        HashMap<String, String> map = new HashMap<>();
        map.put("u_id", u_id);
        map.put("u_pw", u_pw);
        int user_exist = userService.signin(map);

        if(user_exist == 1) return true;
        else if(user_exist == 0) return false;
        else {
            System.out.println("DB정보가 잘못되어 있습니다!!!!");
            return false;
        }
    }

    @GetMapping("/signout")
    public String signout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        // 세션삭제
        session.invalidate();
        // 쿠키삭제
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            CookieGenerator cookieGenerator = new CookieGenerator();
            cookieGenerator.setCookieName("id");
            cookieGenerator.setCookieMaxAge(0);
            cookieGenerator.addCookie(response, null);
//            for (Cookie c : cookies) {
//                if (c.getName().equals("id")) {
//                    System.out.println("c.getName() = " + c.getName());
//                    System.out.println("c.getValue() = " + c.getValue());
//                    c.setMaxAge(0);
//                }
//            }
        }

        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signupForm() {

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(UserDto userDto, String u_domain, MultipartFile u_img2) throws Exception {
        System.out.println(userDto);

        Map map = new HashMap();
        map = regexChk(userDto, u_domain);
        if(!((boolean)map.get("result"))) {
            String msg = (String)map.get("msg");
            msg = URLEncoder.encode(msg, "utf-8");

            return "redirect:/user/signup?msg=" + msg;
        }
//       날짜 처리
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");
        String now = localDateTime.format(formatter);

        userDto.setU_joindate(now);
        userDto.setU_lastlogin(now);

//      생년월일 처리
        String birthday = userDto.getU_birthday();
        int gender = userDto.getU_gender();

        if(gender == 1 || gender == 2) {
            userDto.setU_birthday("19" + birthday);
        } else if(gender == 3 || gender == 4) {
            userDto.setU_birthday("20" + birthday);
        }

//      이메일 처리
        String wholeEmail = userDto.getU_email() + "@" + u_domain;
        userDto.setU_email(wholeEmail);

//      프로필 이미지 업로드
        String dirPath = "F:\\220530 자바본강의\\221215 개인포트폴리오\\작업\\project\\src\\main\\webapp\\resources\\img\\user_profile";
        String filename = u_img2.getOriginalFilename();

        File destination = new File(dirPath + File.separator + filename);

        //파일명 앞부분, 파일명 확장자 구분
        String preFileName = filename.substring(0, filename.lastIndexOf("."));
        String sufFileName =filename.substring(filename.lastIndexOf(".")+1);

        int index = 1;
        // 중복되는 이름이 없을경우 원래 파일이름으로 저장되도록
        String new_filename = filename;
        while(destination.isFile()) {
            // 중복될 경우 새로운 파일 이름
            new_filename = preFileName + "(" + index + ")." + sufFileName;
            destination = new File(dirPath + File.separator + new_filename);

            index += 1;
        }
        u_img2.transferTo(destination);
        userDto.setU_img(new_filename);
        System.out.println(userDto);

        userService.signup(userDto);

        return "redirect:/";
    }

    private Map regexChk(UserDto userDto, String u_domain) {
        Map map = new HashMap();

        String id_regex = "^[a-z]{1}[a-z0-9]{3,11}$";
        String pw_regex = "(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d`~!@#$%^&*|\\'\\\";:₩/?]{8,20}|(?=.*[A-Za-z])(?=.*`~!@#$%^&*|\\'\\\";:₩/?)[A-Za-z0-9`~!@#$%^&*|\\'\\\";:₩/?]{8,20}";
        String name_regex = "^[가-힣]{2,5}$|^[a-z]{2,20}$";
        String birthday_regex = "^[0-9]{6}$";
        String gender_regex = "^[1-4]{1}$";
        String cell_regex = "^(010)[0-9]{8}$";
        String email_regex = "^[a-z]+([-_.0-9a-z]){2,}$";
        String domain_regex = "^[a-z]+[a-z0-9]+\\.[a-z]{2,3}$";

//        String pw_regex1 = "[a-zA-z]";
//        String pw_regex2 = "[0-9]";
//        String pw_regex3 = "[`~!@#$%^&*|\'\";:₩/?]";

//        boolean letter_chk = Pattern.matches(pw_regex1, userDto.getU_pw());
//        boolean num_chk = Pattern.matches(pw_regex2, userDto.getU_pw());
//        boolean special_chk = Pattern.matches(pw_regex3, userDto.getU_pw());

        if(!Pattern.matches(id_regex, userDto.getU_id())) {
            System.out.println("id = 오류");
            map.put("result", false);
            map.put("msg", "아이디(4~12자)는 영문 소문자로 시작, 숫자조합이 가능");

            return map;
        } else if(!Pattern.matches(pw_regex, userDto.getU_pw())) {
            System.out.println("pw = 오류");
            map.put("result", false);
            map.put("msg", "비밀번호(8~20자)는 영 대소문자와 숫자, 특수문자 중 2가지이상 조합");
            return map;
        } else if(!Pattern.matches(name_regex, userDto.getU_name())) {
            System.out.println("name = 오류");
            map.put("result", false);
            map.put("msg", "이름은 한글만(2~5자) 또는 영문만(2~20자) 입력가능");
            return map;
        } else if(!Pattern.matches(birthday_regex, userDto.getU_birthday())) {
            System.out.println("birthday = 오류");
            map.put("result", false);
            map.put("msg", "생년월일은 6자리의 숫자만 가능합니다.");
            return map;
        } else if(!Pattern.matches(gender_regex, ""+userDto.getU_gender())) {
            System.out.println("gender = 오류");
            map.put("result", false);
            map.put("msg", "성별은 1자리로 1,2,3,4의 값만 가능합니다.");
            return map;
        } else if (!Pattern.matches(cell_regex, userDto.getU_cell())) {
            System.out.println("cell = 오류");
            map.put("result", false);
            map.put("msg", "휴대전화번호는 010으로 시작하는 11자리의 숫자 입력 가능");
            return map;
        } else if (!Pattern.matches(email_regex, userDto.getU_email())) {
            System.out.println("email = 오류");
            map.put("result", false);
            map.put("msg", "이메일은(3글자이상) 영소문자로 시작 숫자, '.', '-', '_' 조합 가능");
            return map;
        } else if (!Pattern.matches(domain_regex, u_domain)) {
            System.out.println("domain = 오류");
            map.put("result", false);
            map.put("msg", "도메인은 영소문자로 시작하고 영소문자와 숫자 조합이 가능합니다. ex) aa(2자리이상).com(2~3자리)");
            return map;
        }
        map.put("result", true);
        map.put("msg", "회원가입이 완료되었습니다.");
        return map;

    }

//    아이디 중복체크
    @PostMapping("/idChk")
    public ResponseEntity<Integer> idChk(@RequestBody UserDto userDto) {
        String id = userDto.getU_id();

        try {
            int id_cnt = userService.idDuplicationChk(id);
            if(id_cnt == 0) {
                return new ResponseEntity<Integer>(0, HttpStatus.OK);
            } else if(id_cnt == 1) {
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
            } else {
                throw new Exception("DB failed");
            }
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(2, HttpStatus.BAD_REQUEST);
        }
    }

    //    이메일 중복체크
    @PostMapping("/emailChk")
    public ResponseEntity<Integer> emailChk(String email, String domain) {
        String whole_email = email + "@" + domain;
        try {
            int email_cnt = userService.emailDuplicationChk(whole_email);
            if(email_cnt == 0) {
                return new ResponseEntity<Integer>(0, HttpStatus.OK);
            } else if(email_cnt == 1) {
                return new ResponseEntity<Integer>(1, HttpStatus.OK);
            } else {
                throw new Exception("DB failed");
            }
        } catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Integer>(2, HttpStatus.BAD_REQUEST);
        }
    }

}
