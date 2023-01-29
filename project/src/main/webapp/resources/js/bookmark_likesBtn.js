$(document).ready(function() {
    // 북마크 버튼 클릭 시
    $(".bookmark").click(function() {
        $(".bookmark").css({
            pointerEvents: "none"
        });
        setTimeout(function(){
            $(".bookmark").css({
                pointerEvents: "auto"
            });
        }, 1000);
        let bookmarkBtn = $(this);
        let bookmarkNo = $(this).attr("info");
        // alert(bookmarkNo);
        $.ajax({
            type: "patch",
            url: "/music/bookmarkBtn/"+bookmarkNo,
            success: function(result) {
                console.log(result);
                if(result == 2) {
                    alert("로그인 이후에 사용해 주세요");
                } else if(result == 0) {
                    alert("해당 곡이 즐겨찾기에 등록되었습니다.");
                    bookmarkBtn.attr("src", "/img/bookmark2.png");
                } else if(result == 1) {
                    alert("해당 곡이 즐겨찾기에 삭제되었습니다.");
                    bookmarkBtn.attr("src", "/img/bookmark1.png");
                }
            },
            error: function(result) {
                console.log(result);
                alert("즐겨찾기 db에 문제가 있습니다. 관리자에게 문의하세요");
            }
        })
    })

    // 좋아요 버튼 클릭시
    $(".likes").click(function() {
        $(".likes").css({
            pointerEvents: "none"
        });
        setTimeout(function(){
            $(".likes").css({
                pointerEvents: "auto"
            });
        }, 1000);
        let likesBtn = $(this);
        let likesNo = $(this).attr("info");
        // alert(bookmarkNo);
        $.ajax({
            type: "patch",
            url: "/music/likesBtn/"+likesNo,
            success: function(result) {
                console.log(result);
                let selectCnt = result[0];
                let likestotalCnt = result[1];
                if(selectCnt == 2) {
                    alert("로그인 이후에 사용해 주세요");
                } else if(selectCnt == 0) {
                    likesBtn.attr("src", "/img/likes2.png");
                    likesBtn.parents("tr").find(".likesTotalCnt").text(likestotalCnt);
                } else if(selectCnt == 1) {
                    likesBtn.attr("src", "/img/likes1.png");
                    likesBtn.parents("tr").find(".likesTotalCnt").text(likestotalCnt);
                }
            },
            error: function(result) {
                console.log(result);
                alert("즐겨찾기 db에 문제가 있습니다. 관리자에게 문의하세요");
            }
        })
    })







})