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
    <link rel="stylesheet" href="/css/music_board.css">


</head>
<body>

<jsp:include page="header.jsp" flush="false"/>

<main id="main">
    <div class="contents_area">
        <h2 class="maintitle">음악소통 게시판</h2>
        <div class="music_board_wrap">
            <form id="board_frm" action="/community/search">
                <div class="board_searchBox">
                    <label for="c_title">
                        <input type="radio" id="c_title" name="search_category" value="c_title" checked>제목
                    </label>
                    <label for="c_writer">
                        <input type="radio" id="c_writer" name="search_category" value="c_writer">작성자
                    </label>
                    <label for="c_content">
                        <input type="radio" id="c_content" name="search_category" value="c_content">내용
                    </label>
                    <div class="searchtxt_box">
                        <input type="text" name="search_word" id="board_search_word">
                        <img src="/img/magnifier.png" alt="검색돋보기" title="검색돋보기" class="magnifier_main">
                    </div>
                </div>
            </form>
            <table class="music_board_main">
                <colgroup>
                    <col style="width: 8%;">
                    <col style="width: 15%;">
                    <col style="width: 41%;">
                    <col style="width: 12%;">
                    <col style="width: 12%;">
                    <col style="width: 12%;">
                </colgroup>

                <tr class="music_board_main_head">
                    <th>번호</th>
                    <th>관련곡명</th>
                    <th>제목</th>
                    <th>작성자</th>
                    <th>등록일</th>
                    <th>조회수</th>
                </tr>
                <c:choose>
                    <c:when test="${empty community_list}">
                        <tr class="music_board_main_body">
                            <th colspan="6">게시물이 존재하지 않습니다.</th>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="communityDto" items="${community_list}" varStatus="status">
                            <tr class="music_board_main_body">
                                <th>${communityDto.c_no}</th>
                                <th>${communityDto.c_music}</th>
                                <td class="board_title">
                                    <a href="/community/read?currPage=${pagination.currPage}&pageSize=${pagination.pageListCnt}&boardNo=${communityDto.c_no}">${communityDto.c_title}</a>
                                </td>
                                <td class="board_writer">${communityDto.c_writer}</td>
                                <td class="board_created">${communityDto.c_created}</td>
                                <td class="board_hits">${communityDto.c_hits}</td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </table>
            <c:if test="${not empty sessionScope.id}">
            <div class="boardBtn">
                <button class="board_writeBtn" type="button">
                    <a href="/community/write?currPage=${pagination.currPage}&pageSize=${pagination.pageListCnt}">글쓰기</a>
                </button>
            </div>
            </c:if>
        </div>

        <div class="pagination">
            <c:if test="${pagination.showPPrev}">
                <div class="pprev_arrow"><a href="/community/list?currPage=1&pageSize=${pagination.pageListCnt}"><<</a></div>
            </c:if>
            <c:if test="${pagination.showPrev}">
                <div class="prev_arrow"><a href="/community/list?currPage=${pagination.beginPage-pagination.naviSize}&pageSize=${pagination.pageListCnt}"><</a></div>
            </c:if>
            <c:forEach var="i" begin="${pagination.beginPage}" end="${pagination.endPage}">
                <c:set var="currPageChk" value="${pagination.currPage==i ? 'current_page':''}"/>
                <div class="paginationNo ${currPageChk}"><a href="/community/list?currPage=${i}&pageSize=${pagination.pageListCnt}">${i}</a></div>
            </c:forEach>
            <c:if test="${pagination.showNext}">
                <div class="next_arrow"><a href="/community/list?currPage=${pagination.beginPage+pagination.naviSize}&pageSize=${pagination.pageListCnt}">></a></div>
            </c:if>
            <c:if test="${pagination.showNNext}">
                <div class="nnext_arrow"><a href="/community/list?currPage=${pagination.totalPage}&pageSize=${pagination.pageListCnt}">>></a></div>
            </c:if>
        </div>
    </div>

</main>

<jsp:include page="footer.jsp" flush="false"/>

<script src="/js/1.12.4.js"></script>
<script src="/js/header_footer.js"></script>
<script>
    $(document).ready(function() {
        $(".magnifier_main").click(function() {
            $("#board_frm").submit();
        })
    })
</script>
</body>
</html>
