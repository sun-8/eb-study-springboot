
// 취소 버튼 이벤트
const backBtn = document.getElementById("backBtn");
backBtn.addEventListener("click", (event) => {
    event.preventDefault();

    history.back();
    return false;
})

const file1Info = document.getElementById("file1Info");
const file2Info = document.getElementById("file2Info");
const file3Info = document.getElementById("file3Info");

// 파일 업로드 시 이벤트 - form submit
// file1Info.addEventListener("change", (event) => {
//     event.preventDefault();
//
//     const file1Name = document.getElementById("file1Name");
//     file1Name.value = event.target.files[0].name;
// })
//
// file2Info.addEventListener("change", (event) => {
//     event.preventDefault();
//
//     const file2Name = document.getElementById("file2Name");
//     file2Name.value = event.target.files[0].name;
// })
//
// file3Info.addEventListener("change", (event) => {
//     event.preventDefault();
//
//     const file3Name = document.getElementById("file3Name");
//     file3Name.value = event.target.files[0].name;
// })

// 파일 업로드 시 이벤트 - js
file1Info.addEventListener("change", (event) => {
    event.preventDefault();

    const file1Name = document.getElementById("file1Name");
    const file1Id = document.getElementById("file1Id");
    const obj = {
        fileId : file1Id,
        fileName : file1Name
    }

    fileUpload(event.target.files[0], obj);
})

file2Info.addEventListener("change", (event) => {
    event.preventDefault();

    const file2Name = document.getElementById("file2Name");
    const file2Id = document.getElementById("file2Id");
    const obj = {
        fileId : file2Id,
        fileName : file2Name
    }

    fileUpload(event.target.files[0], obj);
})

file3Info.addEventListener("change", (event) => {
    event.preventDefault();

    const file3Name = document.getElementById("file3Name");
    const file3Id = document.getElementById("file3Id");
    const obj = {
        fileId : file3Id,
        fileName : file3Name
    }

    fileUpload(event.target.files[0], obj);
})

// 등록 버튼 이벤트
const regBtn = document.getElementById("regBtn");
regBtn.addEventListener("click", (event) => {
    event.preventDefault();

    let rtn = true;
    const form = document.getElementById("frm");

    const errorObj = {
        categoryId: document.getElementById("categoryError"),
        userName: document.getElementById("userNameError"),
        password: document.getElementById("passwordError"),
        title: document.getElementById("titleError"),
        contents: document.getElementById("contentsError")
    }
    errorObj.categoryId.innerText = '';
    errorObj.userName.innerText = '';
    errorObj.password.innerText = '';
    errorObj.title.innerText = '';
    errorObj.contents.innerText = '';

    const formObj = {
        categoryId: document.getElementById("categoryId"),
        userName: document.getElementById("userName"),
        password: document.getElementById("password"),
        passwordChk: document.getElementById("passwordChk"),
        title: document.getElementById("title"),
        contents: document.getElementById("contents")
    }

    // 카테고리 유효성
    if (formObj.categoryId.value === '') {
        errorObj.categoryId.innerText = '카테고리를 선택해주세요.';
        rtn = false;
    }
    // 작성자 유효성
    if (formObj.userName.value === '' ) {
        errorObj.userName.innerText = '작성자를 입력해주세요.';
        rtn = false;
    } else if (formObj.userName.value.length < 3 || formObj.userName.value.length >= 5) {
        errorObj.userName.innerText = '3글자 이상, 5글자 미만으로 입력해주세요.';
        rtn = false;
    }
    // 비밀번호 유효성
    if (formObj.password.value === '') {
        errorObj.password.innerText = '비밀번호를 입력해주세요.';
    } else if (!validatePassword(formObj.password.value)) {
        errorObj.password.innerText = '4글자 이상, 16글자 미만, 영문/숫자/특수문자 포함이 필수입니다.';
        rtn = false;
    } else if (formObj.passwordChk.value === '') {
        errorObj.password.innerText = '비밀번호 확인을 입력해주세요.';
        rtn = false;
    } else if (formObj.password.value !== formObj.passwordChk.value) {
        errorObj.password.innerText = '비밀번호가 일치하지 않습니다.';
        rtn = false;
    }
    // 제목 유효성
    if (formObj.title.value === '') {
        errorObj.title.innerText = '제목을 입력해주세요.';
        rtn = false;
    } else if (formObj.title.value.length < 4 || formObj.title.value.length >= 100) {
        errorObj.title.innerText = '4글자 이상, 100글자 미만으로 입력해주세요.';
        rtn = false;
    }
    // 내용 유효성
    if (formObj.contents.value === '') {
        errorObj.contents.innerText = '내용을 입력해주세요.';
        rtn = false;
    } else if (formObj.contents.value.length < 4 || formObj.contents.value.length >= 2000) {
        errorObj.contents.innerText = '4글자 이상, 2000글자 미만으로 입력해주세요.';
        rtn = false;
    }

    if (rtn) {
        if (confirm("글을 등록하시겠습니까?")) {
            let process = document.getElementById("process");
            process.value = "C";
            form.submit();
        }
    } else {
        return false;
    }

})

function validatePassword(value) {
    const pattern = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[~@#$!%*?&])[a-zA-Z\d~@#$!%*?&]{4,15}$/;
    return !/[ㄱ-ㅎㅏ-ㅣ가-힣]/.test(value) && pattern.test(value);
}