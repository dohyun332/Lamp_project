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
    <title>음악 게시판</title>
    <link rel="icon" href="/img/favicon.svg">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/music_board_read.css">


    <script src="/js/1.12.4.js"></script>
    <script src="/js/music_board_read.js"></script>


</head>
<body>

<jsp:include page="header.jsp" flush="false"/>

<main id="main">
    <div class="contents_area">
        <h2 class="maintitle">음악소통 게시판 읽기</h2>
        <div class="music_board_wrap">
            <div class="board_related_music">관련음악 : ${communityDto.c_music}</div>
            <div class="music_board_main">
                <div class="music_board_main_head">
                    <div class="board_title">${communityDto.c_title}</div>
                    <div class="board_writer">작성자 : ${communityDto.c_writer}</div>
                </div>
                <div class="music_board_main_body">
                    <div class="board_info">
                        <div class="board_created">등록일 : ${communityDto.c_created}
                            <div class="line_main"></div>
                        </div>
                        <div class="board_modified">수정일 : ${communityDto.c_modified}
                            <div class="line_main"></div>
                        </div>
                        <div class="board_hits">조회수 : ${communityDto.c_hits}</div>
                    </div>
                    <div class="board_content">${communityDto.c_content}</div>
                </div>
            </div>
            <div class="board_btnBox">
                <c:choose>
                    <c:when test="${communityDto.c_writer.equals(sessionScope.id)}">
                        <div><a href="/community/modify?boardNo=${communityDto.c_no}">수정</a></div>
                        <div><a href="/community/delete?boardNo=${communityDto.c_no}">삭제</a></div>
                    </c:when>
                    <c:otherwise></c:otherwise>
                </c:choose>

                <div>
                    <c:choose>
                        <c:when test="${empty param.search_category && empty param.search_word}">
                            <a href="/community/list?currPage=${param.currPage}&pageSize=${param.pageListCnt}">목록보기</a>
                        </c:when>
                        <c:otherwise>
                            <a href="/community/search?currPage=${param.currPage}&pageSize=${param.pageListCnt}&search_category=${param.search_category}&search_word=${param.search_word}">목록보기</a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div id="comment_wrap">
                <div class="comment_list_title">▶ 답글</div>
                <div id="comment_list" boardNo="${param.boardNo}">

                </div>
                <div class="comment_writeBox">
                    <div class="comment_write_txtBox">
                        <div class="comment_write_title">▶ 답글쓰기</div>
                        <div class="comment_write_subtitle">(150자)</div>
                    </div>
                    <textarea class="comment_write" placeholder="댓글을 입력하세요.(150자 이내)"></textarea>
                    <button id="comment_writeBtn" type="button" c_no="${communityDto.c_no}">답글작성</button>
                </div>
            </div>
        </div>
    </div>
</main>

<jsp:include page="footer.jsp" flush="false"/>

</body>
</html>
