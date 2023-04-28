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
    <title>로그인</title>
    <link rel="icon" href="/img/favicon.svg">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/signin_up_header.css">
    <link rel="stylesheet" href="/css/signin.css">

    <script src="/js/1.12.4.js"></script>
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
        <div class="signIn_title">로그인</div>
        <form action="/user/signin" method="post">
        <div class="signIn_box">
            <div class="signIn_wrap">
                <div class="signIn_section">
                    <input type="text" class="id_pw" name="u_id" placeholder="아이디" value="rlaehgus">
                    <input type="password" class="id_pw" name="u_pw" placeholder="비밀번호" value="rlaehgus1">
                    <div class="alertMsg">${param.msg}</div>
                    <label for="signIn_chk">
                        <input type="checkbox" id="signIn_chk" name="remember_userInfo">
                        로그인 상태 유지
                    </label>
                    <button class="signIn_btn">로그인</button>
                </div>
                <img src="/img/signIn/loging_ad.png" alt="광고배너" title="광고배너">
            </div>
        </div>
        </form>
    </div>
</main>

</body>
</html>
