<%--
  Created by IntelliJ IDEA.
  User: rlaeh
  Date: 2023-01-15
  Time: 오전 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>램프에 오신것을 환영합니다.</title>
    <link rel="icon" href="/img/favicon.svg">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/header_footer.css">

    <script src="/js/1.12.4.js"></script>
    <script src="/js/header_footer.js"></script>
</head>
<body>

<header id="header">
    <div class="top_banner">
        <div class="top_banner_txt">여기는 90년~2000년대 노래 저장소 LAMP입니다.</div>
        <div class="top_banner_txt">시대별 음악차트를 확인하실 수 있고</div>
        <div class="top_banner_txt">이용자들과 음악소통 게시판에서 의견을 나눌 수 있으며</div>
        <div class="top_banner_txt">SNS로 나의 일상을 공유 할 수 있습니다.</div>
        <div class="top_banner_txt">새로 시작하는 한해 모두 좋은 일만 있기를 바랍니다.</div>
    </div>
    <div id="nav_wrap">
        <div class="contents_area">
            <form id="frm" action="/music/musicSearch" method="get">
                <div class="search_wrap">
                    <div class="logo">
                        <a href="/"><img src="/img/LAMP.png" alt="logo" title="logo"></a>
                    </div>
                    <div class="search_box">
                        <input type="text" placeholder="음악을 검색하세요." id="search_word" name="search_word">
                        <div class="magnifier"></div>
                        <div class="search_lookahead">
                            <div class="search_lookahead_titlebox">
                                <div class="search_lookahead_title">제목별</div>
                                <div class="search_lookahead_content">
                                    <div class="search_lookahead_none">
                                        검색결과가 없습니다.
                                    </div>
                                </div>
                            </div>
                            <div class="search_lookahead_musicianbox">
                                <div class="search_lookahead_title">가수별</div>
                                <div class="search_lookahead_content">
                                    <div class="search_lookahead_none">
                                        검색결과가 없습니다.
                                    </div>
                                </div>
                            </div>
                            <div class="search_lookahead_albumbox">
                                <div class="search_lookahead_title">앨범별</div>
                                <div class="search_lookahead_content">
                                    <div class="search_lookahead_none">
                                        검색결과가 없습니다.
                                    </div>
                                </div>
                            </div>
                            <div class="search_lookahead_closebox">
                                <div class="search_lookahead_close">닫기</div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="nav_menu">
                <div class="nav_menu1">
                    <div class="nav">
                        <a href="/music/musicChart">시대별 음악차트</a>
                    </div>
                    <div class="nav">
                        <a href="/community/list">음악 게시판</a>
                    </div>
                    <div class="nav">
                        <a href="#">LAMP SNS</a>
                    </div>
                </div>
                <div class="signin_up">
                    <c:choose>
                        <c:when test="${ sessionScope.id ne null }">
                            <div class="signin_up_txt" onselectstart="return false" title="회원id">${sessionScope.id} 님
                            </div>
                            <div class="sign_arrow"></div>
                            <div class="signout_box" onselectstart="return false"><a href="/user/signout">로그아웃</a></div>
                            <div><a href="/music/musicBookmark">마이뮤직</a></div>
                            <img src="/img/user_profile/${u_img}" alt="프로필이미지" title="프로필이미지" class="profile_img">
                        </c:when>
                        <c:when test="${ sessionScope.id eq null }">
                            <div class="signin_up_txt" onselectstart="return false" title="로그인/회원가입">로그인/회원가입</div>
                            <div class="sign_arrow"></div>
                            <div class="signin_up_box">
                                <div class="signin">
                                    <a href="/user/signin" onselectstart="return false">LAMP 로그인</a>
                                </div>
                                <div class="signup">
                                    <a href="/user/signup" onselectstart="return false">회원가입</a>
                                </div>
                            </div>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
    </div>
</header>

</body>
</html>
