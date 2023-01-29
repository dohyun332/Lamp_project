$(document).ready(function() {
    // 국내, 국외버튼
    $(".nation").click(function() {
        $(".nation").css({
            backgroundColor: "#efefef",
            color: "black"
        })
        $(this).css({
            backgroundColor: "gray",
            color: "white"
        })
    })

    // 달력버튼(년도 선택)
    let calendar_chk = 0;
    $(".calendar").click(function() {
        if(calendar_chk == 0) {
            $(".year_popup").css({
                display: "block"
            })
            calendar_chk = 1;
        } else if(calendar_chk == 1) {
            $(".year_popup").css({
                display: "none"
            })
            calendar_chk = 0;
        }
    })

    $(".year_popup_close").click(function() {
        $(".year_popup").css({
            display: "none"
        })
        calendar_chk = 0;
    })

    let dt = new Date();
    let last_year = dt.getFullYear() - 1;
    let now_around_year = Math.floor(dt.getFullYear() / 10) * 10;
    let music_chart_year = +($(".music_chart_year").text().slice(0, 4));
    let around_year = Math.floor(music_chart_year / 10) * 10;

    // 달력 초기화
    $(".year_around_selected").text(around_year + "년");
    for(let i=0; i<=9; i++) {
        $(".year_list_a").eq(i).text((around_year+i)+"년");
        $(".year_list_a").eq(i).attr("href", "/music/musicChart?year="+(around_year + i));
        if((around_year+i) == music_chart_year) {
            $(".year_list").eq(i).addClass("year_selected");
        }
        if((around_year+i)>last_year) {
            $(".year_list").eq(i).addClass("year_blocked");
        }
    }

    // 달력 좌우 버튼
    $(".prev_arrow").click(function() {
        if (around_year > 1990) {
            around_year -= 10;
            $(".year_around_selected").text((around_year + "년"));
            $(".year_list").removeClass("year_selected");
            $(".year_list").removeClass("year_blocked");

            for (let i = 0; i <= 9; i++) {
                $(".year_list_a").eq(i).text((around_year + i) + "년");
                $(".year_list_a").eq(i).prop("href", "/music/musicChart?year="+(around_year + i));
                if ((around_year + i) == music_chart_year) {
                    $(".year_list").eq(i).addClass("year_selected");
                }
                if ((around_year + i) > last_year) {
                    $(".year_list").eq(i).addClass("year_blocked");
                }
            }
        }
    })

    $(".next_arrow").click(function() {
        if (around_year < now_around_year) {
            around_year += 10;
            $(".year_around_selected").text((around_year + "년"));
            $(".year_list").removeClass("year_selected");
            $(".year_list").removeClass("year_blocked");

            for (let i = 0; i <= 9; i++) {
                $(".year_list_a").eq(i).text((around_year + i) + "년");
                $(".year_list_a").eq(i).prop("href", "/music/musicChart?year="+(around_year + i));
                if ((around_year + i) == music_chart_year) {
                    $(".year_list").eq(i).addClass("year_selected");
                }
                if ((around_year + i) > last_year) {
                    $(".year_list").eq(i).addClass("year_blocked");
                }
            }
        }
    })

    // 음악차트 유튜브 팝업 컨트롤
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

})