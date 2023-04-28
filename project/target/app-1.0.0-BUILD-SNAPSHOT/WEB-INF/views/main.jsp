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
    <link rel="stylesheet" href="/css/main.css">

    <script src="/js/1.12.4.js"></script>
    <script src="/js/main.js"></script>
    <script src="/js/youtube_open.js"></script>
    <script src="/js/bookmark_likesBtn.js"></script>
</head>
<body>
<jsp:include page="header.jsp" flush="false"/>

<main id="main">
    <div class="contents_area">
        <h3 class="subtitle">오늘의 추천음악</h3>
        <div class="container_main_youtube">
            <iframe class="main_youtube"
                    src="${todayMusicDto.t_youtubelink}?rel=0&fs=0&controls=1&disablekb=0&modestbranding=1&mute=1&autoplay=1"
                    title="YouTube video player"
                    allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture"
                    allowfullscreen></iframe>
            <div class="youtube_info">
                <div>${todayMusicDto.m_music} - ${todayMusicDto.m_album}</div>
                <div>${todayMusicDto.m_musician}</div>
                <div>${todayMusicDto.m_year}년 ${todayMusicDto.m_rank}위</div>
            </div>
        </div>

        <div class="chart_wrap">
            <div class="chart_section">
                <div class="chart_title_box">
                    <h3 class="subtitle">년도별 차트</h3>
                    <div class="chart_year">${randomYear}년</div>
                </div>

                <table class="chart">
                    <colgroup>
                        <col style="width:10%">
                        <col style="width:65%">
                        <col style="width:25%">
                    </colgroup>

                    <tbody>
                    <c:forEach var="musicDto" items="${yearMusicChart}">
                        <c:set var="bookmark" value="${ empty musicDto.mb_no ? 1:2 }" />
                        <c:set var="likes" value="${ empty musicDto.l_no ? 1:2 }" />

                        <tr class="chart_row">
                        <td class="rank">${musicDto.m_rank}</td>
                        <td class="album_info_td">
                            <div class="album_info">
                                <img src="/img/album/${musicDto.m_albumimg}">
                                <div class="album_txt">
                                    <div class="music">${musicDto.m_music}</div>
                                    <div class="artist">${musicDto.m_musician}</div>
                                </div>
                            </div>
                        </td>
                        <td class="chart_btn">
                            <div class="chart_btn_box">
                                <img class="youtube_btn" src="/img/youtube.png" alt="youtube" title="youtube" info="${musicDto.m_music} ${musicDto.m_musician}">
                                <img class="bookmark" src="/img/bookmark${bookmark}.png" alt="bookmark" title="bookmark" info="${musicDto.m_no}">
                                <img class="likes" src="/img/likes${likes}.png" alt="likes" title="likes" info="${musicDto.m_no}">
                            </div>
                        </td>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="new_section">
                <h3 class="subtitle">NEW</h3>
                <div class="new_slide_box">
                    <img class="banner" src="/img/main/fadeimg1.jpg" alt="NewJeans OMG">
                    <img class="banner" src="/img/main/fadeimg2.jpg" alt="김다다 춤">
                    <img class="banner" src="/img/main/fadeimg3.jpg" alt="ROSESIA 파도">
                    <img class="banner" src="/img/main/fadeimg4.jpg" alt="유겸 Ponytail">
                    <img class="banner" src="/img/main/fadeimg5.png" alt="event">

                    <img class="new_left_btn" src="/img/main/new_left.png" alt="leftbutton">
                    <img class="new_right_btn" src="/img/main/new_right.png" alt="rightbutton">

                    <div class="indicator_box">
                        <div class="play_btn_box">
                            <!-- 시작 버튼 -->
                            <svg width="8px" height="8px" viewBox="0 0 163.861 163.861" id="btn_start" class="svg_active">
                                <g>
                                    <path d="M34.857,3.613C20.084-4.861,8.107,2.081,8.107,19.106v125.637c0,17.042,11.977,23.975,26.75,15.509L144.67,97.275
                                    c14.778-8.477,14.778-22.211,0-30.686L34.857,3.613z" />
                                </g>
                            </svg>

                            <!-- 멈춤버튼 -->
                            <svg width="9px" height="9px" viewBox="0 0 45.975 45.975" id="btn_stop" class="svg_active">
                                <g>
                                    <path
                                            d="M13.987,0c-2.762,0-5,2.239-5,5v35.975c0,2.763,2.238,5,5,5s5-2.238,5-5V5C18.987,2.238,16.75,0,13.987,0z" />
                                    <path
                                            d="M31.987,0c-2.762,0-5,2.239-5,5v35.975c0,2.762,2.238,5,5,5s5-2.238,5-5V5C36.987,2.239,34.749,0,31.987,0z" />
                                </g>
                            </svg>
                        </div>
                    </div>


                </div>
                <div class="recommand_album_box">
                    <div class="recommand_album">
                        <img src="/img/main/recommand_album1.jfif" alt="End Theory 윤하">
                        <div class="recommand_album_txt">
                            <div class="recommand_title">RECOMMEND ALBUM</div>
                            <div class="recommand_album_name">End Theory</div>
                            <div class="recommand_artist">윤하</div>
                        </div>
                    </div>
                    <div class="recommand_album">
                        <img src="/img/main/recommand_album2.jfif" alt="ANTIFRAGILE LESSERAFIM">
                        <div class="recommand_album_txt">
                            <div class="recommand_title">RECOMMEND ALBUM</div>
                            <div class="recommand_album_name">ANTIFRAGILE</div>
                            <div class="recommand_artist">LESSERAFIM</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <h3 class="subtitle">에디터 추천</h3>
        <div class="editor_wrap">
            <c:forEach var="editorChoiceDto" items="${editorChoiceList}" varStatus="status">
            <div class="editor_box">
                <a href="/music/editorChoice/${status.index+1}">
                    <div class="editor_img_box">
                        <div class="editor_cover"></div>
                        <div class="editor_cover_content">
                            <img class="editor_playimg" src="/img/main/editor/editor_play.png" alt="">
                            <div class="editor_musicCnt">${editorChoiceDto.e_totalCnt}곡</div>
                        </div>
                        <c:forEach var="musicDto" items="${editorChoiceDto.m_albumimg}" varStatus="status" >
                            <c:choose>
                                <c:when test="${status.index eq 0}">
                                    <img class="main_img" src="/img/album/${musicDto.m_albumimg}" alt="">
                                </c:when>
                                <c:when test="${status.index eq 1}">
                                    <img class="left_img" src="/img/album/${musicDto.m_albumimg}" alt="">
                                </c:when>
                                <c:when test="${status.index eq 2}">
                                    <img class="right_img" src="/img/album/${musicDto.m_albumimg}" alt="">
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <div class="editor_txt_box">
                        <div class="editor_category">추천선곡</div>
                        <div class="editor_title">${editorChoiceDto.e_theme}</div>
                        <div class="editor_dj_box">
                            <img src="/img/main/dj_logo.png" alt="DJ logo">
                            <div class="editor_dj">${editorChoiceDto.e_editor}</div>
                        </div>
                    </div>
                </a>
            </div>
            </c:forEach>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" flush="false"/>
</body>
</html>
