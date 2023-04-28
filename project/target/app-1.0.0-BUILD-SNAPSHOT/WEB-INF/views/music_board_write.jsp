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
    <link rel="stylesheet" href="/css/music_board_write.css">

    <script src="/js/1.12.4.js"></script>
    <script src="/js/community_write_modify"></script>

</head>
<body>

<jsp:include page="header.jsp" flush="false"/>

<main id="main">
    <div class="contents_area">
        <h2 class="maintitle">음악소통 게시판 글쓰기</h2>
        <form action="/community/write" method="post">
            <div class="music_board_wrap">

                <div class="music_board_row">
                    <div class="board_nav">아이디</div>
                    <input type="text" class="c_writer" name="c_writer" value="${sessionScope.id}" readonly>
                    <div class="board_nav">관련음악</div>
                    <input type="text" class="c_music" name="c_music" value="" placeholder="최대 10자까지 입력이 가능합니다.">
                </div>

                <div class="music_board_row">
                    <div class="board_nav">제목</div>
                    <input type="text" class="c_title" name="c_title" value="" placeholder="최대 50자까지 입력이 가능합니다.">
                </div>

                <div class="music_board_content">
                    <div class="board_nav">내용</div>
                    <textarea name="c_content" id="c_content"></textarea>
                </div>

                <div class="board_btnBox">
                    <button>글쓰기</button>
                    <div><a href="/community/list?currPage=${param.currPage}&pageSize=${param.pageSize}">목록보기</a></div>
                </div>
            </div>
        </form>
    </div>
</main>

<jsp:include page="footer.jsp" flush="false"/>

</body>
</html>
