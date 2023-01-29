$(document).ready(function() {
    // 답글 2줄이상 시
    // console.log($(".comment_content").eq(0).prop("scrollHeight"), $(".comment_content").eq(1).prop("scrollHeight"));
    // 초기화
    replyHeight();
    function replyHeight() {
        $(".comment_content").each(function(index, item) {
            if($(this).prop("scrollHeight") >= 100) {
                $(this).height(75);
            } else if($(this).prop("scrollHeight") >= 50) {
                $(this).height(50);
            }
        });
    }

    $(".comment_content").keyup(function() {
        $(".comment_content").each(function(index, item) {
            if($(this).prop("scrollHeight") >= 100) {
                $(this).height(75);
            } else if($(this).prop("scrollHeight") >= 50) {
                $(this).height(50);
            } else {
                $(this).height(30);
            }
        });
    });

    // 답글 150자 이내
    $(".comment_write").keyup(function() {
        let comment_value = $(".comment_write").val();

        if(comment_value.length > 200) {
            alert("150자를 초과하여 입력이 불가합니다.");
            $(".comment_write").val(comment_value.slice(0, 151));
            $(".comment_write").focus();
        }
    })

    // 초기화 처음 댓글 전체 불러오기
    const boardNo = $("#comment_list").attr("boardNo");
    showList();
    function showList() {
        $.ajax({
            type: 'get',
            url: '/comment/list?boardNo='+boardNo,
            success : function(result) {
                $("#comment_list").html(inputList(result));
            },
            error : function() {
                alert("데이터베이스에 이상이 있습니다. 관리자에게 문의해주세요");
            }
        });
    }

    let inputList = function(comments) {
        let list = "";
        let firstRowChk = 0;
        if(comments.length == 0) {
            list = `<div class="comment_list_row1">
                        <div class="comment_none">댓글이 없습니다.</div>
                    </div>`;
            return list;
        }
        comments.forEach(function(comment) {
            if(comment.cc_no == comment.cc_comment) {
                if(firstRowChk!=0) {
                    list += `</div>`;
                }
                firstRowChk=1;
                list += `<div class="comment_row_wrap">
                            <div class="comment_list_row1" cc_no="${comment.cc_no}" c_no="${comment.c_no}">
                                <div class="comment_writer">${comment.cc_writer}</div>
                                <textarea class="comment_content" readonly>${comment.cc_content}</textarea>
                                <div class="comment_btnBox">
                                    <button class="comment_modifyBtn" type="button" cc_no="${comment.cc_no}">수정</button>
                                    <button class="comment_deleteBtn" type="button" cc_no="${comment.cc_no}">삭제</button>
                                    <button class="comment_replyBtn" type="button">답글</button>
                                </div>
                                <div class="comment_dateBox">
                                    <div class="comment_created">
                                        작성 : ${comment.cc_created}
                                    </div>
                                    <div class="comment_modified">
                                        수정 : ${comment.cc_modified}
                                    </div>
                                </div>
                            </div>`;
            } else {
                list += `<div class="comment_list_row2">
                            <img src="/img/music_board/reply.gif" alt="대댓글이미지">
                            <div class="comment_writer">${comment.cc_writer}</div>
                            <textarea class="comment_content" readonly>${comment.cc_content}</textarea>
                            <div class="comment_btnBox">
                                <button class="comment_modifyBtn" type="button" cc_no="${comment.cc_no}">수정</button>
                                <button class="comment_deleteBtn" type="button" cc_no="${comment.cc_no}">삭제</button>
                            </div>
                            <div class="comment_dateBox">
                                <div class="comment_created">
                                    작성 : ${comment.cc_created}
                                </div>
                                <div class="comment_modified">
                                    수정 : ${comment.cc_modified}
                                </div>
                            </div>
                        </div>`;
            }
        })
        list += `</div>`;
        return list;
    }

    // 답글 버튼, 수정 버튼 js
    let comment_list;
    let replyBtnChk = 0;
    $("#comment_list").click(function (e){
        comment_list = $(e.target);
        if(comment_list.hasClass("comment_replyBtn")) {
            if(replyBtnChk==0) {
                comment_list.parents(".comment_row_wrap").append(
                    `<div class="comment_list_row2">
                <img src="/img/music_board/reply.gif" alt="">
                <textarea class="reply_content"></textarea>
                <button class="reply_confirmBtn" type="button">입력</button>
                <div class="blank"></div>
            </div>`);
                replyBtnChk=1;
            } else {
                comment_list.parents(".comment_list_row1").nextAll(".comment_list_row2").last().remove();
                replyBtnChk=0;
            }
        }
    })

    // 댓글 입력하기
    $("#comment_writeBtn").click(function () {
        let comment_write = $("#comment_writeBtn").prev().val();
        if(comment_write.length == 0) {
            alert("댓글을 입력하세요");
            return;
        }
        let c_no = $("#comment_writeBtn").attr("c_no");
        $.ajax({
            type: "post",
            url: "/comment/write",
            headers: {"content-type":"application/json"},
            data: JSON.stringify({c_no:c_no, cc_content:comment_write}),
            success: function (result) {
                if(result == 0) alert("로그인 후에 사용해주세요");
                else if(result == 1) {
                    showList();
                    alert("댓글입력이 완료되었습니다.1");
                }
            },
            error: function () {
                alert("데이터베이스에 이상이 있습니다. 관리자에게 문의해주세요");
            }
        })
    })

    $("#comment_list").click(function (e) {
        comment_list = $(e.target);
        // 대댓글 입력하기
        if(comment_list.hasClass("reply_confirmBtn")) {
            let reply_content = comment_list.prev().val();
            if(reply_content.length==0) {
                alert("댓글을 입력하세요");
                return;
            }
            let c_no = comment_list.parent().prevAll(".comment_list_row1").attr("c_no");
            let cc_no = comment_list.parent().prevAll(".comment_list_row1").attr("cc_no");
            console.log("info", reply_content, c_no, cc_no);
            $.ajax({
                type: "post",
                url: "/comment/write",
                headers: {"content-type":"application/json"},
                data: JSON.stringify({c_no:c_no, cc_content:reply_content,cc_no:cc_no}),
                success: function (result) {
                    if(result == 0) alert("로그인 후에 사용해주세요");
                    else if(result == 2) {
                        showList();
                        alert("댓글입력이 완료되었습니다.2");
                    }
                },
                error: function () {
                    alert("데이터베이스에 이상이 있습니다. 관리자에게 문의해주세요");
                }
            })
            // 댓글 삭제하기
        } else if(comment_list.hasClass("comment_deleteBtn")) {
            let cc_no = comment_list.attr("cc_no")
            $.ajax({
                type: "delete",
                url: "/comment/delete?cc_no="+cc_no,
                success: function (result) {
                    if(result == 0) alert("로그인 후에 사용해주세요");
                    else if(result == 1) alert("본인이 작성한 댓글만 삭제 가능합니다.");
                    else if(result == 2) {
                        alert("대댓글이 있을경우 댓글 삭제가 불가합니다.");
                    }
                    else if(result == 3) {
                        showList();
                        alert("댓글삭제가 완료되었습니다.");
                    }
                },
                error: function () {
                    alert("데이터베이스에 이상이 있습니다. 관리자에게 문의해주세요");
                }
            })
            // 댓글 수정
        } else if(comment_list.hasClass("comment_modifyBtn")){
            let cc_no = comment_list.attr("cc_no");
            let comment_content = comment_list.parent().prev();
            let comment_contentVal = comment_list.parent().prev().val();
            if(comment_content.attr("readonly")) {
                $.ajax({
                    type: "get",
                    url: "/comment/modify?cc_no=" + cc_no,
                    success: function(result) {
                        alert("1: 양식 수정");
                        if(result == 0) alert("로그인 후에 사용해주세요");
                        else if(result == 1) alert("본인이 작성한 댓글만 삭제 가능합니다.");
                        else if(result == 2) {
                            let comment_content = comment_list.parent().prev();
                            comment_content.attr("readonly", false);
                            comment_content.css({
                                border: "1px solid"
                            })
                            let comment_contentVal = comment_content.val();
                            comment_content.focus();
                            comment_content.val("");
                            comment_content.val(comment_contentVal);
                        }
                    },
                    error: function() {
                        alert("데이터베이스에 이상이 있습니다. 관리자에게 문의해주세요");
                    }
                })
            } else {
                alert("2: 데이터 베이스 수정 -> " + comment_contentVal);
                if(comment_contentVal.length == 0) {
                    alert("댓글 내용이 없습니다.")
                    return;
                }
                $.ajax({
                    type: "patch",
                    url: "/comment/modify",
                    headers: {"content-type":"application/json"},
                    data: JSON.stringify({cc_no:cc_no, cc_content:comment_contentVal}),
                    success: function(result) {
                        if(result == 0) alert("로그인 후에 사용해주세요");
                        else if(result == 1) alert("본인이 작성한 댓글만 삭제 가능합니다.");
                        else if(result == 2) {
                            showList();
                            alert("댓글수정이 완료되었습니다.");
                        }
                    },
                    error: function() {
                        alert("데이터베이스에 이상이 있습니다. 관리자에게 문의해주세요");
                    }
                })
            }
        }
    })

    let tmp1;
    $("#comment_list").mousedown(function (e) {
        console.log("다운 동작");
        tmp1 = $(e.target).parents("#comment_list").find(".comment_content");
        if($(e.target).parent().prev().attr("readonly")) {
            tmp1.attr("readonly", true);
            tmp1.css({
                border: "none"
            })
        }
    })
})