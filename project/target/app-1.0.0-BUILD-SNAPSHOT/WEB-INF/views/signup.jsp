<%--
  Created by IntelliJ IDEA.
  User: rlaeh
  Date: 2023-01-15
  Time: 오전 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="icon" href="/img/favicon.svg">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/signin_up_header.css">
    <link rel="stylesheet" href="/css/signup.css">

    <script src="/js/1.12.4.js"></script>
    <script src="/js/signup.js"></script>
</head>
<body>
<header id="header">
    <div class="contents_area">
        <div class="nav_wrap">
            <div class="logo">
                <a href="/">
                    <img src="/img/LAMP.png" alt="logo" title="logo">
                </a>
            </div>
            <ul class="nav_menu">
                <li class="nav_menu_li">
                    <a href="/user/signin">로그인</a>
                </li>
                <li class="nav_menu_li">
                    <a href="/user/signup">회원가입</a>
                </li>
            </ul>
        </div>
    </div>
</header>

<main id="main">
    <div class="contents_area">
        <div class="signUp_title">회원 가입</div>
        <form id="frm" enctype="multipart/form-data">
            <div class="signUp_box">
                <div class="signUp_wrap">
                    <div class="alertmsg_red">${param.msg}</div>
                    <div class="signUp_subtitle">아이디</div>
                    <div class="id_box">
                        <!-- id input tag -->
                        <input type="text" id="u_id" class="input_item input_id" name="u_id" placeholder="아이디" value="">
                        <div class="duplicate_chk id_chk">중복확인</div>
                    </div>
                    <div class="alertmsg alertmsg_id">
                        4~12자/영문 소문자 시작하는 영문소문자, 숫자조합가능
                    </div>

                    <div class="signUp_subtitle">비밀번호</div>
                    <div class="pw_box">
                        <!-- pw input tag -->
                        <input type="password" class="input_item" id="u_pw1" placeholder="비밀번호" value="">
                        <input type="password" class="input_item" id="u_pw2" placeholder="비밀번호 확인" name="u_pw" value="">
                    </div>
                    <div class="alertmsg alertmsg_pw">
                        영문 대문자와 소문자를 포함하며 숫자, 특수문자 중 2가지이상을 조합하여 8~20자로 입력해주세요.
                    </div>

                    <div class="signUp_subtitle">이름</div>
                    <!-- name input tag -->
                    <input type="text" class="input_item"  placeholder="이름" id="u_name" name="u_name" value="">
                    <div class="alertmsg alertmsg_name"></div>

                    <div class="signUp_subtitle">생년월일</div>
                    <div class="birth_box">
                        <!-- birthday input tag -->
                        <input type="text" class="input_item" id="u_birthday" name="u_birthday" placeholder="생년월일 6자리">
                        <div class="subtxt">-</div>
                        <!-- gender input tag -->
                        <input type="text" class="input_item" id="u_gender" name="u_gender" placeholder="성별">
                        <div class="subtxt">* * * * * *</div>
                    </div>
                    <div class="alertmsg alertmsg_birth"></div>
                    <div class="alertmsg alertmsg_gender"></div>

                    <div class="signUp_subtitle">휴대전화 번호</div>
                    <!-- 휴대폰 input tag -->
                    <input type="text" class="input_item" value="010" id="u_cell" name="u_cell">
                    <div class="alertmsg alertmsg_cell">"-"을 제외한 11자리를 입력해주세요.</div>

                    <div class="signUp_subtitle">이메일</div>
                    <!-- email input tag -->
                    <input type="text" class="input_item" name="u_email" id="u_email" placeholder="이메일">
                    <div class="domain_wrap">
                        <div class="subtxt">@</div>
                        <div class="domain_box">
                            <!-- domain input tag -->
                            <input type="hidden" class="input_item domain" name="u_domain" id="u_domain" placeholder="도메인 주소">
                            <select class="domain_select" id="u_domain_select">
                                <option disabled hidden selected>도메인 입력</option>
                                <option value="">직접입력</option>
                                <option value="gmail.com">gmail.com</option>
                                <option value="naver.com">naver.com</option>
                                <option value="daum.net">daum.net</option>
                                <option value="nate.com">nate.com</option>
                            </select>
                            <img src="/img/signUp/arrow.png" alt="도메인확장" title="도메인확장" class="email_arrow">
                        </div>
                        <div class="duplicate_chk email_chk">중복확인</div>
                    </div>
                    <div class="alertmsg alertmsg_email"></div>


                    <div class="signUp_subtitle profile_subtitle">프로필 이미지</div>
                    <div class="alertmsg alertmsg_profile">5MB이하 파일만 가능합니다.</div>
                    <div class="profile_box">
                        <img alt="프로필이미지" title="프로필이미지" id="profile_img">
                        <!-- 프로필 이미지 input -->
                        <input type="file" accept="image/jpg, image/jepg, image/png, image/bmp" id="u_img" name="u_img2">
                    </div>



                    <div class="agreement_box">
                        <label for="agreement_all">
                            <input type="checkbox" id="agreement_all" class="agreement_all">
                            전체동의
                        </label>
                        <label for="service_chk">
                            <input type="checkbox" id="service_chk" class="agreement_chk">
                            [필수] 서비스 약관 동의
                        </label>
                        <label for="personal_chk">
                            <input type="checkbox" id="personal_chk" class="agreement_chk">
                            [필수] 개인정보 수집/이용 동의
                        </label>
                    </div>
                    <div class="alertmsg alertmsg_agreement"></div>

                    <button type="button" class="signUp_btn">로그인</button>
                </div>
            </div>
        </form>
    </div>
    <script>



    </script>
</main>

</body>
</html>
