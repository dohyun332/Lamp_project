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
