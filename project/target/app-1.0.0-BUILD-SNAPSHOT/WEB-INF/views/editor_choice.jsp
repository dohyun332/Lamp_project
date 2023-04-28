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
    <link rel="stylesheet" href="/css/editor_choice.css">

    <script src="/js/1.12.4.js"></script>
    <script src="/js/youtube_open.js"></script>
    <script src="/js/bookmark_likesBtn.js"></script>

</head>
<body>

<jsp:include page="header.jsp" flush="false"/>

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

<jsp:include page="footer.jsp" flush="false"/>

</body>
</html>
