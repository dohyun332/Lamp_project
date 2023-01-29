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
    let music_chart_year = +($(".music_chart_year").text().slice(0, 4));
    let around_year = Math.floor(music_chart_year / 10) * 10;
    let year_tb;

    // 달력 초기화
    year_tb = `<table class="year_tb" border="1">
                <colgroup>
                    <col style="width:60px">
                    <col style="width:60px">
                    <col style="width:60px">
                    <col style="width:60px">
                    <col style="width:60px">
                </colgroup>
                <tr>`;
    for(let i=around_year; i<=around_year+9; i++) {
        if(i<music_chart_year) {
            year_tb+=`<td class="year_list"><a href="#">${i+"년"}</a></td>`;
        } else if(i==music_chart_year) {
            year_tb+=`<td class="year_list year_selected"><a href="#">${i+"년"}</a></td>`;
        } else if(i>music_chart_year) {
            if(i>last_year) {
                year_tb+=`<td class="year_list year_blocked"><a href="#">${i+"년"}</a></td>`;
            } else {
                year_tb+=`<td class="year_list year_selected"><a href="#">${i+"년"}</a></td>`;
            }
        }
        if(i==(around_year+4)) {
            year_tb+= `</tr>
            <tr> `;
        }
    }
    year_tb+= `</tr>`;
    $(".year_popup").append(year_tb);

    // 달력 좌우 버튼
    $(".prev_arrow").click(function() {
        if(around_year > 1990) {
            $(".year_tb").remove();
            around_year -= 10;
            $(".year_around_selected").text((around_year + "년"));
            
        }


    })

    // $(".next_arrow")
})