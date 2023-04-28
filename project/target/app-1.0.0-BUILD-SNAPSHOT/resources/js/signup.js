$(document).ready(function() {
    let u_id = $("#u_id");
    let u_pw1 = $("#u_pw1");
    let u_pw2 = $("#u_pw2");
    let u_name = $("#u_name");
    let u_birthday = $("#u_birthday");
    let u_gender = $("#u_gender");
    let u_cell = $("#u_cell");
    let u_email = $("#u_email");
    let u_domain = $("#u_domain");
    let u_img = $("#u_img");
    let service_chk = $("#service_chk");
    let personal_chk = $("#personal_chk");

    let id_regex = /^[a-z]{4,12}$|^[a-z]{1}[a-z0-9]{3,11}$/;
    let birthday_Chk;
    let name_regex = /^[가-힣]{2,5}$|^[a-zA-Z]{2,20}$/;
    let cell_regex = /^(010)[0-9]{8}$/;
    let email_regex = /^[a-z]+([-_.0-9a-z]){2,}$/;
    let domain_regex = /^[a-z]+[a-z0-9]+\.[a-z]{2,3}$/;

    u_birthday.keyup(function () {
        if (u_birthday.val().length >= 7) {
            u_birthday.val(u_birthday.val().slice(0, 6));
        }
    })

    u_gender.keyup(function () {
        if (u_gender.val().length >= 2) {
            u_gender.val(u_gender.val().slice(0, 1));
        }
    })

    u_cell.keyup(function () {
        if (u_cell.val().length >= 12) {
            u_cell.val(u_cell.val().slice(0, 11));
        }
    })

    let id_btn_chk = 0;
    let email_btn_chk = 0;

    $("#u_id").focus(function () {
        id_btn_chk = 0;
    })

    $(".id_chk").click(function() {
        let id_value = $("#u_id").val();

        $.ajax({
            type: "post",
            url: "/user/idChk",
            headers: { "content-type" : "application/json"},
            data: JSON.stringify({u_id:id_value}),
            success : function(result) {
                alert(result);
                if(result == 0) {
                    id_btn_chk = 1;
                    alert("사용가능한 id 입니다.");
                } else if (result == 1) {
                    alert("이미 사용중인 id 입니다. 다른아이디를 선택하세요");
                }
            },
            error : function() {
                alert("에러가 발생하였습니다. 관리자에게 문의하세요");
            }
        });

        $("#u_email, #u_domain, #u_domain_select").focus(function () {
            email_btn_chk = 0;
        })
    });

    $(".email_chk").click(function() {
        let email_value = $("#u_email").val();
        let domain_value = $("#u_domain").val();

        $.ajax({
            type: "post",
            url: "/user/emailChk",
            data: { email:email_value,
                    domain:domain_value
            },
            success : function(result) {
                if(result == 0) {
                    email_btn_chk = 1;
                    alert("사용가능한 email 입니다.");
                } else if (result == 1) {
                    alert("이미 사용중인 email 입니다. 다른아이디를 선택하세요");
                }
            },
            error : function() {
                alert("에러가 발생하였습니다. 관리자에게 문의하세요");
            }
        });
    })




    $(".signUp_btn").click(function () {
        let pw_num = u_pw1.val().search(/[0-9]/g);
        let pw_eng = u_pw1.val().search(/[a-z]/ig);
        let pw_special = u_pw1.val().search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
        let pw_length_chk = (u_pw1.val().length >= 8) && (u_pw1.val().length <= 20);

        console.log("u_email.val()", u_email.val())

        if (u_id.val() == "") {
            alert("아이디를 입력해주세요");
            u_id.focus();
            return;
        } else if (!id_regex.test(u_id.val())) {
            alert("아이디는 4~12자/영문 소문자로 시작하는 영문소문자, 숫자조합 가능");
            u_id.focus();
            return;
        } else if (id_btn_chk == 0) {
            alert("아이디 중복검사를 수행해 주세요");
            u_id.focus();
            return;
        } else if (u_pw1.val() == "") {
            alert("비밀번호를 입력해주세요");
            u_pw1.focus();
            return;
        } else if (!(((pw_num >= 0) && (pw_eng >= 0) && pw_length_chk) ||
            ((pw_eng >= 0) && (pw_special >= 0) && pw_length_chk) ||
            ((pw_num >= 0) && (pw_eng >= 0) && (pw_special >= 0) && pw_length_chk))) {
            alert("비밀번호는 영문 대문자와 소문자, 숫자, 특수문자 중 2가지이상을 조합하여 8~20자로 입력해주세요.");
            u_pw1.focus();
            return;
        } else if (u_pw2.val() == "" || u_pw2.val() == "") {
            alert("비밀번호확인을 입력해주세요");
            u_pw2.focus();
            return;
        } else if (u_pw1.val() != u_pw2.val()) {
            alert("비밀번호와 비밀번호 확인은 동일하게 입력해주세요");
            u_pw2.focus();
            return;
        } else if (u_name.val() == "") {
            alert("이름을 입력해주세요");
            u_name.focus();
            return;
        } else if (!(name_regex.test(u_name.val()))) {
            alert("이름은 한글(2~5자)으로만 또는 영문(2~20자)으로만 입력해주세요");
            u_name.focus();
            return;
        } else if (u_birthday.val() == "") {
            alert("생년월일을 입력해주세요");
            u_birthday.focus();
            return;
        } else if (u_birthday.val().length != 6) {
            alert("생년월일은 6자리를 입력해주세요");
            u_birthday.focus();
            return;
        } else if (u_gender.val() == "") {
            alert("성별을 입력해주세요");
            u_gender.focus();
            return;
        } else if (!(+u_gender.val() >= 1 && +u_gender.val() <= 4)) {
            alert("성별은 1,2,3,4중 한가지 값만 입력해주세요");
            u_gender.focus();
            return;
        } else if (((birthday_Chk = birthdayChk(u_birthday.val())) >= 1)) {
            console.log(birthday_Chk);
            if (birthday_Chk == 1) {
                alert("올바른 년도와 성별값(1,2,3,4)을 입력해주세요");
                u_birthday.focus();
                return;
            } else if (birthday_Chk == 2) {
                alert("월은 1월이상 12월 이하로 입력해주세요");
                u_birthday.focus();
                return;
            } else if (birthday_Chk == 3) {
                alert("년월에 맞는 일자를 입력해주세요");
                u_birthday.focus();
                return;
            }
        } else if (u_cell.val() == "010" || u_cell.val() == "") {
            alert("휴대전화 번호를 입력하세요");
            u_cell.focus();
            return;
        } else if (!cell_regex.test(u_cell.val())) {
            alert("휴대전화번호는 010으로 시작하는 11자리의 숫자만 입력가능합니다.");
            u_cell.focus();
            return;
        } else if (u_email.val() == "") {
            alert("이메일을 입력하세요");
            u_email.focus();
            return;
        } else if (!email_regex.test(u_email.val())) {
            alert("올바른 이메일을 입력해주세요(소문자로 시작하고 소문자 숫자, '.', '-', '_' 가능(3글자이상))");
            u_email.focus();
            return;
        } else if (domain_regex == "") {
            alert("도메인을 입력하세요");
            u_domain.focus();
            return;
        } else if (!domain_regex.test(u_domain.val())) {
            alert("올바른 도메인을 입력해주세요(소문자로 시작하는 소문자 또는 숫자 조합 (ex) aa(2자리이상).com(2~3자리))");
            u_domain.focus();
            return;
        } else if (email_btn_chk == 0) {
            alert("이메일 중복검사를 수행해 주세요");
            u_domain.focus();
            return;
        } else if (u_img.val() == "") {
            alert("프로필 이미지 파일을 업로드해주세요");
            return;
        } else if (!service_chk.prop("checked")) {
            alert("서비스 약관에 동의해주세요.");
            return;
        } else if (!personal_chk.prop("checked")) {
            alert("개인정보 수집/이용에 동의해주세요.");
            return;
        }

        let frm = $("#frm");
        frm.attr("action", "/user/signup");
        frm.attr("method", "post");
        frm.submit();
    })

    function birthdayChk(birthday) {
        let preyear;
        if (+(u_gender.val()) == 1 || +(u_gender.val()) == 2) {
            preyear = 19;
        } else {
            preyear = 20;
        }

        let birthday_year = +(preyear + "" + birthday.slice(0, 2));
        let birthday_month = +(birthday.slice(2, 4));
        let birthday_day = +(birthday.slice(4));

        let last_day = new Date(birthday_year, birthday_month, 0);

        if (!(birthday_year >= 1900 && birthday_year <= 2023)) {
            return 1;
        } else if (!(birthday_month >= 1 && birthday_month <= 12)) {
            return 2;
        } else if (!((birthday_day <= +last_day.getDate()) && (birthday_day >= 1))) {
            return 3;
        } else {
            return 0;
        }
    }

    // 파일업로드 사진파일만 제한
    $("#u_img").change(function() {
        let pathpoint = $(this).val().lastIndexOf('.');
        let filepoint = $(this).val().substring(pathpoint + 1,$(this).val().length);
        let filetype = filepoint.toLowerCase();
        let maxSize = 5 * 1024 * 1024;
        let fileSize = u_img[0].files[0].size;

        if (!(filetype == "jpg" || filetype == "gif" || filetype == "png" || filetype == "jpeg" || filetype == "bmp")) {
            alert("이미지파일만 선택할 수 있습니다.");
            $(this).val("");
        } else if (fileSize > maxSize) {
            alert("5MB이상의 파일을 업로드 할 수 없습니다.");
            $(this).val("");
        } else if (filetype == 'bmp') {
            let upload = confirm("BMP파일은 웹상에서 사용하기엔 적절한 포맷이 아닙니다.\n그래도 계속하시겠습니까?");
            if (!upload) $(this).val("");
        }

        if(u_img[0].files[0] != null) {
            const reader = new FileReader();
            reader.onload = function(e) {
                $("#profile_img").css({
                    display: "block"
                })
                $("#profile_img").attr("src", e.target.result);
            }
            reader.readAsDataURL(u_img[0].files[0]);
        } else {
            $("#profile_img").css({
                display: "none"
            })
            $("#profile_img").attr("src", "");
        }
    })
    function fileCheck2(obj) {
        let imgFile = $("#u_img").val();
        let fileForm = /(.*?)\.(jpg|jpeg|png|gif|bmp)$/;
        let fileForm_bmp = /(.*?)\.(bmp)$/;
    }

    // 이메일 도메인
    let domain_select = $(".domain_select").eq(0);
    $(".domain_select").eq(0).change(function () {
        $(".domain").eq(0).val(domain_select.val());
        if (domain_select.val() == "") {
            $(".domain").prop('type', 'text');
            domain_select.css({
                width: "20%",
                borderRadius: "0 10px 10px 0",
                color: "transparent"
            })
            $(".domain_select option").css({
                color: "black"
            })
        } else {
            $(".domain").prop('type', 'hidden');
            domain_select.css({
                width: "100%",
                borderRadius: "10px",
                color: "black"
            })
        }
    })

    // 약관동의 전체선택 버튼
    $(".agreement_all").click(function () {
        let chk = $(".agreement_all").prop("checked");
        if (!chk) $(".agreement_chk").prop("checked", false);
        else $(".agreement_chk").prop("checked", true);

    })

    $(".agreement_chk").click(function () {
        let total = $(".agreement_chk").length;
        let total_chk = $(".agreement_chk:checked").length;
        if (total == total_chk) {
            $(".agreement_all").prop("checked", true);
        }
        else {
            $(".agreement_all").prop("checked", false);
        }
    })
})

