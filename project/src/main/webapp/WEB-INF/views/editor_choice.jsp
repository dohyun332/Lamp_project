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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>에디터 추천</title>
    <link rel="icon" href="/img/favicon.svg">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/header_footer.css">
    <link rel="stylesheet" href="/css/editor_choice.css">


    <script src="/js/1.12.4.js"></script>
    <script src="/js/header_footer.js"></script>
    <script src="/js/editor_choice.js"></script>
    <script src="/js/bookmark_likesBtn.js"></script>

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
                        <input type="text" placeholder="음악을 검색하세요." id="search_word" name="search_word" value="${param.search_word}">
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

<main id="main">
    <div class="contents_area">
        <h2 class="maintitle">에디터추천</h2>
        <div class="editor_choice_wrap">
            <h3 class="subtitle">${editorChoiceTitle}</h3>
            <table class="editor_choice_main">
                <colgroup>
                    <col style="width: 8%;">
                    <col style="width: 8%;">
                    <col style="width: 56%;">
                    <col style="width: 7%;">
                    <col style="width: 7%;">
                    <col style="width: 7%;">
                    <col style="width: 7%;">
                </colgroup>

                <tr class="editor_choice_main_head">
                    <th>년도</th>
                    <th>순위</th>
                    <th>곡정보</th>
                    <th>듣기</th>
                    <th>즐겨찾기</th>
                    <th>좋아요</th>
                    <th>좋아요수</th>
                </tr>
                <c:forEach var="musicDto" items="${editorChoiceList}">
                    <c:set var="bookmark" value="${ empty musicDto.mb_no ? 1:2 }" />
                    <c:set var="likes" value="${ empty musicDto.l_no ? 1:2 }" />
                    <c:set var="likes_totalCnt" value="${empty musicDto.l_totalCnt ? 0:musicDto.l_totalCnt}"/>
                    <tr class="editor_choice_main_row">
                        <th>${musicDto.m_year}</th>
                        <th>${musicDto.m_rank}</th>
                        <td class="music_info_td">
                            <img src="/img/album/${musicDto.m_albumimg}" class="album_img" alt="album" title="album">
                            <div class="music_info">
                                <div class="title_box">
                                    <c:choose>
                                        <c:when test="${musicDto.m_title eq 'TITLE'}">
                                            <img src="/img/title.png" alt="title_box" title="title_box">
                                        </c:when>
                                    </c:choose>
                                    <div class="music">${musicDto.m_music}</div>
                                </div>
                                <div class="album_box">
                                    <div class="musician">
                                            ${musicDto.m_musician}
                                        <div class="line"></div>
                                    </div>
                                    <div class="album_name">${musicDto.m_album}</div>
                                </div>
                            </div>
                        </td>
                        <td class="editor_choice_icon">
                            <img src="/img/youtube.png" class="youtube_btn" alt="youtube" title="youtube"
                                 info="${musicDto.m_music} ${musicDto.m_musician}">
                        </td>
                        <td class="editor_choice_icon">
                            <img src="/img/bookmark${bookmark}.png" class="bookmark" alt="bookmark" title="bookmark" info="${musicDto.m_no}">
                        </td>
                        <td class="editor_choice_icon">
                            <img src="/img/likes${likes}.png" class="likes" alt="likes" title="likes" info="${musicDto.m_no}">
                        </td>
                        <td class="editor_choice_icon">
                            <div class="likesTotalCnt">${likes_totalCnt}</div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>

</main>

<footer id="footer">
    <div id="top_footer">
        <div class="contents_area">
            <div class="footer_notice1">
                <div class="notice_box">
                    <div class="notice_title">공지사항</div>
                    <div class="notice_txt_box">
                        <div class="notice_txt">회원정책 관련하여 안내드립니다.</div>
                        <div class="notice_txt">"2022램프뮤직어워드 방송안내"</div>
                        <div class="notice_txt">개인정보처리방침 개정 ("23년시행)</div>
                        <div class="notice_txt">램프 서비스 시스템 점검 안내</div>
                        <div class="notice_txt">DJ 34기 합격안내</div>
                    </div>
                    <div class="notice_arrow_box">
                        <a class="notice_arrow1" title="left"></a>
                        <a class="notice_arrow2" title="right"></a>
                    </div>
                </div>
                <ul class="service_box">
                    <li title="이벤트">이벤트</li>
                    <li title="고객센터">고객센터</li>
                    <li title="이용안내">이용안내</li>
                    <li title="서비스 전체보기">서비스 전체보기 ></li>
                </ul>
            </div>
        </div>
    </div>

    <div id="bot_footer">
        <div class="contents_area">
            <div class="footer_notice2">
                <ul class="company_menu1">
                    <li>회사소개</li>
                    <li>|</li>
                    <li>이용약관</li>
                    <li>|</li>
                    <li>개인정보처리방침</li>
                    <li>|</li>
                    <li>청소년보호정책</li>
                    <li>|</li>
                    <li>위치기반 서비스 이용약관</li>
                    <li>|</li>
                    <li>서비스 이용문의</li>
                    <li>|</li>
                    <li>제휴문의</li>
                    <li>
                        <img src="/img/cooperation.png">
                    </li>
                </ul>

                <ul class="company_menu2">
                    <li>
                        <img src="/img/LAMP.png" title="(주)램프">
                    </li>
                    <li class="company_info">
                        <div>(주)램프</div>
                        <div>대표이사 ***  |  서울특별시 강남구 강남대로  |  사업자등록번호 123-45-*****  |  통신판매일신고 2013-서울강남-*****</div>
                        <div>개인정보보호책임자 OOO 본부장  |  서비스문의: 1234-**** / help@*****.co.kr </div>
                        <div>호스팅제공자: (주)램프 COPYRIGHTⓒLAMP MUSIC CORP ALL RIGHTS RESERVED.</div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>

</body>
</html>
