$(document).ready(function() {
    // 탑배너 초기화 작업
    $(".top_banner_txt").eq(0).css({
        top:"0px"
    });

    // 탑배너 슬라이드
    let tb_interval = 4000;
    let tb_index = 0;
    let tb_length = $(".top_banner_txt").length;
    console.log(tb_length);
    setInterval(function() {
        $(".top_banner_txt").eq((tb_index)%tb_length).animate({
            top:"-100%"
        }, 2000);
        $(".top_banner_txt").eq((tb_index+1)%tb_length).css({
            top:"100%"
        }).animate({
            top:"0"
        }, 2000);

        tb_index += 1;
    }, tb_interval)


    // 미리보기 전시 기능
    $("#search_word").focus(function() {
        $(".search_lookahead").css({
            display: "block"
        })
    })
    $("#search_word").blur(function() {
            $(".search_lookahead").css({
                display: "none"
            })
    })

    $(".search_lookahead_close").click(function() {
        $(".search_lookahead").css({
            display: "none"
        })
    })


    let search_lookahead_timeout;
    let titlebox = $(".search_lookahead_content").eq(0);
    let musicianbox = $(".search_lookahead_content").eq(1);
    let albumbox = $(".search_lookahead_content").eq(2);
    $("#search_word").keyup(function(e) {
        clearTimeout(search_lookahead_timeout);

        let search_word = $("#search_word").val();
        search_lookahead_timeout =setTimeout(function () {
            if(search_word == "") {
                titlebox.text("");
                titlebox.append('<div class="search_lookahead_none">' +
                    '검색결과가 없습니다.' +
                    '</div>');
                musicianbox.text("");
                musicianbox.append('<div class="search_lookahead_none">' +
                    '검색결과가 없습니다.' +
                    '</div>');
                albumbox.text("");
                albumbox.append('<div class="search_lookahead_none">' +
                    '검색결과가 없습니다.' +
                    '</div>');
            }
            else {
                $.ajax({
                    type: "get",
                    url: "/search_lookahead",
                    data: ({search_word:search_word}),
                    success: function (result) {
                        result.forEach(function(data, index) {
                            console.log(data, index);
                            if(data.length == 0) {
                                if(index == 0) {
                                    $(".search_lookahead_content").eq(index).text("");
                                    titlebox.append('<div class="search_lookahead_none">' +
                                                    '검색결과가 없습니다.' +
                                                    '</div>');
                                } else if(index == 1) {
                                    $(".search_lookahead_content").eq(index).text("");
                                    musicianbox.append('<div class="search_lookahead_none">' +
                                        '검색결과가 없습니다.' +
                                        '</div>');
                                } else if(index == 2) {
                                    $(".search_lookahead_content").eq(index).text("");
                                    albumbox.append('<div class="search_lookahead_none">' +
                                        '검색결과가 없습니다.' +
                                        '</div>');
                                }
                            }
                            else {
                                if(index == 0) {
                                    $(".search_lookahead_content").eq(index).text("");
                                    data.forEach(function(data2){
                                        titlebox.append('<div class="search_lookahead_info">' +
                                            `<img src="/img/album/${data2.m_albumimg}" alt="앨범이미지" title="${data2.m_album}" class="search_lookahead_album">` +
                                            `<div class="search_lookahead_music">${data2.m_music}</div>` +
                                            '<div class="line"></div>' +
                                            `<div class="search_lookahead_musician">${data2.m_musician}</div>` +
                                            '</div>');
                                    })
                                } else if(index == 1) {
                                    $(".search_lookahead_content").eq(index).text("");
                                    data.forEach(function(data2){
                                        musicianbox.append('<div class="search_lookahead_info">' +
                                            `<img src="/img/album/${data2.m_albumimg}" alt="앨범이미지" title="${data2.m_album}" class="search_lookahead_album">` +
                                            `<div class="search_lookahead_music">${data2.m_music}</div>` +
                                            '<div class="line"></div>' +
                                            `<div class="search_lookahead_musician">${data2.m_musician}</div>` +
                                            '</div>');
                                    })
                                } else if(index == 2) {
                                    $(".search_lookahead_content").eq(index).text("");
                                    data.forEach(function(data2){
                                        albumbox.append('<div class="search_lookahead_info">' +
                                            `<img src="/img/album/${data2.m_albumimg}" alt="앨범이미지" title="${data2.m_album}" class="search_lookahead_album">` +
                                            `<div class="search_lookahead_music">${data2.m_music}</div>` +
                                            '<div class="line"></div>' +
                                            `<div class="search_lookahead_musician">${data2.m_musician}</div>` +
                                            '</div>');
                                    })
                                }
                            }
                        })
                    },
                    error: function (result) {
                        alert("검색 미리보기 데이터에 오류가 발생하였습니다.");
                    }
                })
            }
        }, 300);
    })

    // 검색 미리보기 클릭 시
    let cate;
    let music;
    let musician;
    let album;
    let search_lookahead_el;
    $(".search_lookahead_content").mousedown(function(e) {
        cate = $(this).prev().text();
        search_lookahead_el = $(e.target);

        music = search_lookahead_el.context.firstElementChild.nextElementSibling.innerText;
        $("#search_word").val(music);

        // if(cate == "제목별") {
        //     music = search_lookahead_el.context.firstElementChild.nextElementSibling.innerText;
        //     $("#search_word").val(music);
        // } else if(cate == "가수별") {
        //     musician = search_lookahead_el.context.lastElementChild.innerText;
        //     $("#search_word").val(musician);
        // } else if(cate == "앨범별") {
        //     album = search_lookahead_el.context.firstElementChild.title;
        //     $("#search_word").val(album);
        // }
    })

    // 검색 기능
    $(".magnifier").click(function() {
        // $("#frm").attr("action", "/music/musicSearch");
        // $("#frm").attr("method", "get");
        $("#frm").submit();
    })


    // 로그인, 회원가입 화살표 전환 box none/block 전환
    let chk = 0;
    $(".signin_up_txt, .sign_arrow, .signout_box").click(function () {
        if (chk == 0) {
            $(".sign_arrow").css({
                transform: "rotateX(180deg)"
            })
            $(".signin_up_box, .signout_box").css({
                display: "block"
            })
            chk=1;
        } else {
            $(".sign_arrow").css({
                transform: "rotateX(0)"
            })
            $(".signin_up_box, .signout_box").css({
                display: "none"
            })
            chk=0;
        }
    })

    // footer 공지사항 슬라이드
    // 슬라이드 초기화
    $(".notice_txt").eq(0).css({
        left: "0"
    })
    let notice_slide_index = 0;
    let notice_slide_length = $(".notice_txt").length;
    
    $(".notice_arrow1").click(function() {
        $(".notice_arrow1, .notice_arrow2").css({
            pointerEvents: "none"
        })
        $(".notice_txt").eq(notice_slide_index%notice_slide_length).animate({
            left: "-100%",
            opacity: "0"
        }, 500)
        $(".notice_txt").eq((notice_slide_index+1)%notice_slide_length).css({
            left: "100%",
            opacity: "0"
        }).animate({
            left: "0",
            opacity: "1"
        }, 1000)
        notice_slide_index+=1;
        setTimeout(function() {
            $(".notice_arrow1, .notice_arrow2").css({
                pointerEvents: "auto"
            })
        }, 1000)
    });

    $(".notice_arrow2").click(function() {
        $(".notice_arrow1, .notice_arrow2").css({
            pointerEvents: "none"
        })
        $(".notice_txt").eq(notice_slide_index%notice_slide_length).animate({
            left: "100%",
            opacity: "0"
        }, 500)
        $(".notice_txt").eq((notice_slide_index-1)%notice_slide_length).css({
            left: "-100%",
            opacity: "0"
        }).animate({
            left: "0",
            opacity: "1"
        }, 1000)
        notice_slide_index-=1;
        setTimeout(function() {
            $(".notice_arrow1, .notice_arrow2").css({
                pointerEvents: "auto"
            })
        }, 1000)
    });
})