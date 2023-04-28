<%@ page import="java.time.LocalDate" %><%--
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
    <title>음악 차트</title>
    <link rel="icon" href="/img/favicon.svg">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/music_chart.css">

    <script src="/js/1.12.4.js"></script>
    <script src="/js/music_chart.js"></script>
    <script src="/js/youtube_open.js"></script>
    <script src="/js/bookmark_likesBtn.js"></script>

    <c:set var="nationChk1" value="${ param.category==null || param.category==0 ? ' nation_sel':''}" />
    <c:set var="nationChk2" value="${ param.category==1 ? ' nation_sel':''}" />
    <% LocalDate localDate = LocalDate.now();
        int lastYear = localDate.getYear() - 1; %>
    <c:set var="lastYear" value="<%=lastYear%>" />
    <c:set var="select_year" value="${ empty param.year ? lastYear:param.year}" />

    <c:set var="rank50BtnChk1" value="${ param.rank==null || param.rank==0 ? ' rank50Btn_sel':''}" />
    <c:set var="rank50BtnChk2" value="${ param.rank==50 ? ' rank50Btn_sel':''}" />
    <c:set var="select_category" value="${ empty param.category ? 0:param.category}" />
</head>
<body>

<jsp:include page="header.jsp" flush="false"/>

<main id="main">
    <div class="contents_area">
        <h2 class="maintitle">시대별 음악차트</h2>
        <div class="music_bookmark_wrap">
            <div class="music_chart_title">
                <div class="nation_box">
                    <div class="internal${nationChk1}">
                        <a href="/music/musicChart?category=0&year=${select_year}">국내</a>
                    </div>
                    <div class="external${nationChk2}">
                        <a href="/music/musicChart?category=1&year=${select_year}">국외</a>
                    </div>
                </div>
                <h3 class="music_chart_year">${select_year}년</h3>
                <span class="calendar_box">
                        <img class="calendar" src="/img/music_chart/calendar.png" alt="달력 아이콘">
                        <div class="year_popup">
                            <img class="year_popup_close" src="/img/music_chart/close_x.gif" alt="">
                            <div class="year_popup_title">
                                <span class="prev_arrow"></span>
                                <span class="year_around_selected"></span>
                                <span class="next_arrow"></span>
                            </div>
                            <table class="year_tb" border="1">
                                <colgroup>
                                    <col style="width:60px">
                                    <col style="width:60px">
                                    <col style="width:60px">
                                    <col style="width:60px">
                                    <col style="width:60px">
                                </colgroup>
                                <tr>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                    <td class="year_list">
                                        <a class="year_list_a" href="#"></a>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </span>
            </div>

            <table class="music_chart_main">
                <colgroup>
                    <col style="width: 8%;">
                    <col style="width: 8%;">
                    <col style="width: 56%;">
                    <col style="width: 7%;">
                    <col style="width: 7%;">
                    <col style="width: 7%;">
                    <col style="width: 7%;">
                </colgroup>

                <tr class="music_chart_main_head">
                    <th>년도</th>
                    <th>순위</th>
                    <th>곡정보</th>
                    <th>듣기</th>
                    <th>즐겨찾기</th>
                    <th>좋아요</th>
                    <th>좋아요수</th>
                </tr>
                <c:forEach var="musicDto" items="${music_list}">
                    <c:set var="bookmark" value="${empty musicDto.mb_no?1:2}"/>
                    <c:set var="likes" value="${empty musicDto.l_no?1:2}"/>
                    <c:set var="likes_totalCnt" value="${empty musicDto.l_totalCnt?0:musicDto.l_totalCnt}"/>
                    <tr class="music_chart_main_row">
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
                        <td class="music_chart_icon">
                            <img src="/img/youtube.png" class="youtube_btn" alt="youtube" title="youtube"
                                 info="${musicDto.m_music} ${musicDto.m_musician}">
                        </td>
                        <td class="music_chart_icon">
                            <img src="/img/bookmark${bookmark}.png" class="bookmark" alt="bookmark" title="bookmark"
                                 info="${musicDto.m_no}">
                        </td>
                        <td class="music_chart_icon">
                            <img src="/img/likes${likes}.png" class="likes" alt="likes" title="likes"
                                 info="${musicDto.m_no}">
                        </td>
                        <td class="music_chart_icon">
                            <div class="likesTotalCnt">${likes_totalCnt}</div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="rank50Box">
            <div class="rank50Btn ${rank50BtnChk1}">
                <a href="/music/musicChart?category=${select_category}&year=${select_year}&rank=0">1 ~ 50위</a>
            </div>
            <div class="rank50Btn ${rank50BtnChk2}">
                <a href="/music/musicChart?category=${select_category}&year=${select_year}&rank=50">51 ~ 100위</a>
            </div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" flush="false"/>

</body>
</html>
