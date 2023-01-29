$(document).ready(function() {
    // 년도별 차트 마우스 호버 효과
    //초기화
    $(".chart tr").eq(0).css({
        height: "100px",
    })
    $(".album_info").eq(0).css({
        display:"flex"
    })
    $(".album_info > img").eq(0).css({
        display:"block"
    })
    $(".album_txt").eq(0).css({
        display:"block"
    })
    $(".music").eq(0).css({
        marginBottom:"10px"
    })

    $(".chart_row").mouseenter(function() {
        // 초기화
        $(".chart tr").css({
            height: "50px",
        })
        $(".album_info").css({
            display:"block"
        })
        $(".album_info > img").css({
            display:"none"
        })
        $(".album_txt").css({
            display:"flex"
        })
        $(".music").css({
            marginBottom:"0px"
        })

        // 마우스 enter된 항목
        $(this).find(".chart tr").css({
            height: "100px",
        })
        $(this).find(".album_info").css({
            display:"flex"
        })
        $(this).find(".album_info > img").css({
            display:"block"
        })
        $(this).find(".album_txt").css({
            display:"block"
        })
        $(this).find(".music").css({
            marginBottom:"10px"
        })
    })

    // New 섹션 슬라이드
    // 초기화
    let index_no = 0;
    let banner_length = $(".banner").length;
    let new_slide_interval;

    $(".banner").eq(0).css({
        display:"block"
    })
    for(let i=0;i<banner_length;i++) {
        $(".indicator_box").prepend('<div class="indicator"></div>');
    }
    $(".indicator").eq(0).addClass("indicator_active");

    // 오토슬라이드
    let new_slide_chk = "play";
    auto_new_slide();
    $(".play_btn_box").click(function() {
        if(new_slide_chk == "stop") {
            auto_new_slide_play();
        } else if(new_slide_chk = "play") {
            auto_new_slide_stop();
        }
    })
    
    // New 섹션 슬라이드 좌우 버튼
    $(".new_left_btn").click(function() {
        auto_new_slide_stop();
        new_slide(index_no, (index_no-1));
    })
    $(".new_right_btn").click(function() {
        auto_new_slide_stop();
        new_slide(index_no, (index_no+1));
    })

    // New 섹션 슬라이드, 인디게이터
    $(".indicator").click(function() {
        auto_new_slide_stop();
        btn_lock();
        $(".indicator").removeClass("indicator_active");
        $(this).addClass("indicator_active");
        let new_indicator_index = $(this).index();
        new_slide(index_no, new_indicator_index);
    })

    function new_slide(index, next_index) {
        btn_lock();
        $(".indicator").removeClass("indicator_active");
        $(".indicator").eq(next_index % banner_length).addClass("indicator_active");

        $(".banner").eq(index % banner_length).fadeOut(2000);
        $(".banner").eq(next_index % banner_length).fadeIn(2000);
        index_no=next_index;
    }

    function auto_new_slide() {
        new_slide_interval = setInterval(function() {
            new_slide(index_no, (index_no+1))
        }, 4000)
    }

    function auto_new_slide_stop() {
        clearInterval(new_slide_interval);
        $("#btn_stop").css({
            display: "none"
        })
        $("#btn_start").css({
            display: "block"
        })
        new_slide_chk = "stop";
    }

    function auto_new_slide_play() {
        auto_new_slide();
        $("#btn_stop").css({
            display: "block"
        })
        $("#btn_start").css({
            display: "none"
        })
        new_slide_chk = "play";
    }

    function btn_lock() {
        $(".new_left_btn, .new_right_btn, .indicator").css({
            pointerEvents:"none"
        })
        setTimeout(function() {
            $(".new_left_btn, .new_right_btn, .indicator").css({
                pointerEvents:"auto"
            })
        }, 2000)
    }

    // 유튜브 팝업
    let youtube = "";
    $(".youtube_btn").click(function() {
        let search_word = $(this).attr("info");
        youtube_open(search_word);
    })

    function youtube_open(word) {
        if(typeof(youtube) == "object") youtube_close();

        let youtube_x = 948;
        let youtube_y = 533;

        let pos_x = window.screenLeft + ((window.innerWidth - youtube_x) / 2);
        let pos_y = (window.outerHeight - youtube_y) / 2;

        youtube = window.open(`https://www.youtube.com/results?search_query=${word}`, `_blank`, `width=${youtube_x}, height=${youtube_y}, left=${pos_x}, top=${pos_y}`);
    }

    function youtube_close() {
        youtube.close();
    }

    // 에디터 추천항목 배경색
    let editor_img_box_cnt = $(".editor_img_box").length;
    for(let i=0; i<editor_img_box_cnt; i++) {
        if(i % 2 == 0) $(".editor_img_box").eq(i).css({backgroundColor: "#9eb9cd"})
        if(i % 2 == 1) $(".editor_img_box").eq(i).css({backgroundColor: "#c4a9b6"})
    }


})

