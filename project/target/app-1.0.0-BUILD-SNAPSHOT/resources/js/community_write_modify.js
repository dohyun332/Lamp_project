$(document).ready(function() {
    // 글쓰기, 수정 시 최대 입력 글자 수
    $(".c_music").on("keyup blur",function() {
        if($(".c_music").val().length > 10) {
            alert("최대 10자까지 입력가능합니다.");
            $(".c_music").val($(".c_music").val().substring(0, 10));
        }
    })

    $(".c_title").on("keyup blur", function() {
        if($(".c_title").val().length > 50) {
            alert("최대 50자까지 입력가능합니다.");
            $(".c_title").val($(".c_title").val().substring(0, 50));
        }
    })
})