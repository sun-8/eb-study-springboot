
// 취소 버튼 이벤트
let backBtn = document.getElementById("backBtn");
backBtn.addEventListener("click", (event) => {
    event.preventDefault();

    history.back();
    return false;
})

let file1Info = document.getElementById("file1Info");
let file2Info = document.getElementById("file2Info");
let file3Info = document.getElementById("file3Info");

// 파일 업로드 시 이벤트 - form submit
// file1Info.addEventListener("change", (event) => {
//     event.preventDefault();
//
//     let file1Name = document.getElementById("file1Name");
//     file1Name.value = event.target.files[0].name;
// })
//
// file2Info.addEventListener("change", (event) => {
//     event.preventDefault();
//
//     let file2Name = document.getElementById("file2Name");
//     file2Name.value = event.target.files[0].name;
// })
//
// file3Info.addEventListener("change", (event) => {
//     event.preventDefault();
//
//     let file3Name = document.getElementById("file3Name");
//     file3Name.value = event.target.files[0].name;
// })

// 파일 업로드 시 이벤트 - js
file1Info.addEventListener("change", (event) => {
    event.preventDefault();

    let file1Name = document.getElementById("file1Name");
    let file1Id = document.getElementById("file1Id");
    let obj = {
        fileId : file1Id,
        fileName : file1Name
    }

    fileUpload(event.target.files[0], obj);
})

file2Info.addEventListener("change", (event) => {
    event.preventDefault();

    let file2Name = document.getElementById("file2Name");
    let file2Id = document.getElementById("file2Id");
    let obj = {
        fileId : file2Id,
        fileName : file2Name
    }

    fileUpload(event.target.files[0], obj);
})

file3Info.addEventListener("change", (event) => {
    event.preventDefault();

    let file3Name = document.getElementById("file3Name");
    let file3Id = document.getElementById("file3Id");
    let obj = {
        fileId : file3Id,
        fileName : file3Name
    }

    fileUpload(event.target.files[0], obj);
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