
// 취소 버튼 이벤트
let backBtn = document.getElementById("backBtn");
backBtn.addEventListener("click", (event) => {
    event.preventDefault();

    history.back();
    return false;
})

// 등록 버튼 이벤트
let regBtn = document.getElementById("regBtn");
regBtn.addEventListener("click", (event) => {
    event.preventDefault();

    let form = document.getElementById("frm");

    let categoryId = document.getElementById("categoryId");
    let userName = document.getElementById("userName");
    let password = document.getElementById("password");
    let passwordChk = document.getElementById("passwordChk");
    let title = document.getElementById(("title"));
    let contents = document.getElementById("contents");

    if (categoryId.value === "") {
        alert('카테고리를 선택해주세요.');
        return false;
    } else if (userName.value === "") {
        alert('작성자를 입력해주세요.');
        return false;
    } else if (password.value === "") {
        alert('비밀번호를 입력해주세요.');
        return false;
    } else if (passwordChk.value === "") {
        alert('비밀번호 확인을 입력해주세요.');
        return false;
    } else if (password.value !== passwordChk.value) {
        alert('비밀번호가 일치하지 않습니다.');
        return false;
    } else if (title.value === "") {
        alert('제목을 입력해주세요.');
        return false;
    } else if (contents.value === "") {
        alert('내용을 입력해주세요.');
        return false;
    } else if (confirm("글을 등록하시겠습니까?")) {
        let process = document.getElementById("process");
        process.value = "C";
        form.submit();
    }
})