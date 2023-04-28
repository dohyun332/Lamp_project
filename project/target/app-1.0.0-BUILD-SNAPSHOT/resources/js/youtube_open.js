
$(document).ready(function(){
    // 유튜브 팝업
    let youtube;
    $(".youtube_btn").click(function() {
        let search_word = $(this).attr("info");
        youtube_open(search_word);
    })

    function youtube_open(word) {
        if(typeof(youtube) == "object") youtube_close();

        let youtube_x = 948;
        let youtube_y = 533;

        let pos_x = window.screenLeft + ((window.innerWidth - youtube_x) / 2);
        let pos_y = window.screenTop + (window.outerHeight - youtube_y) / 2;

        youtube = window.open(`https://www.youtube.com/results?search_query=${word}`, `_blank`, `width=${youtube_x}, height=${youtube_y}, left=${pos_x}, top=${pos_y}`);
    }

    function youtube_close() {
        youtube.close();
    }
})