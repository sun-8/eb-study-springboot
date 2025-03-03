
const key = document.getElementById("key");
// 댓글 목록 출력
selectCommentList(key.value);

// 파일 다운로드 이벤트
const download = document.querySelectorAll(".download");
download.forEach((v) => {
   v.addEventListener("click", (event) => {
       event.preventDefault();

       const fileId = event.target.dataset.file;
       location.href = "/download?fileId=" + fileId;
   })
});

// 댓글 등록 버튼 이벤트
const regBtn = document.getElementById("regBtn");
regBtn.addEventListener("click", (event) => {
    event.preventDefault();

    const regTextarea = document.getElementById("regTextarea");

    if (regTextarea.value === "") {
        alert('댓글을 입력해 주세요.');
        return false;
    } else {
        const obj = {
            id : key,
            comments : regTextarea
        }
        insertComment(obj);
    }
})

// 목록 버튼 이벤트
const listBtn = document.getElementById("listBtn");
listBtn.addEventListener("click", (event) => {
    event.preventDefault();

    location.href="/boards/free/list";
})

// 수정 버튼 이벤트
const modBtn = document.getElementById("modBtn");
modBtn.addEventListener("click", (event) => {
    event.preventDefault();

    location.href="/boards/free/modify?id=" + key;
})

// 삭제 버튼 이벤트
const delBtn = document.getElementById("delBtn");
const dialog = document.querySelector("dialog");
const dialogCancel = document.querySelector("dialog #cancel");
const dialogDelete = document.querySelector("dialog #delete");
delBtn.addEventListener("click", (event) => {
    event.preventDefault();
    // 비밀번호 입력 레이어 열기
    dialog.showModal();
})
dialogCancel.addEventListener("click", (event) => {
    event.preventDefault();
    // 비밀번호 입력 레이어 닫기
    dialog.close();
});
dialogDelete.addEventListener("click", (event) => {
    event.preventDefault();

    const password = document.getElementById("password");
    // 비밀번호 확인 및 삭제
    boardDelete(password);
});

// 댓글 목록 출력
async function selectCommentList(key) {
    try {
        const commentData = await fetch('/boards/free/selectCommentList?id=' + key, {
            method: 'GET',
        })
        const result = await commentData.json();

        const commentList = document.getElementById("commentList");
        // 기존 댓글 목록 제거
        commentList.textContent = '';
        const list = [];

        result.forEach((item, idx) => {
            list.push(`
            <span class="font12 txtDarkslategrey">${item.regDttm}</span><br>
            <span style="white-space: pre-wrap;">${item.comments}</span>
            <hr>
            `)
        })
        // 댓글 목록 추가
        commentList.innerHTML = list.join('');

    } catch (error) {
        alert('댓글 로드 중에 오류가 발생했습니다.');
    }
}

// 댓글 등록 처리
async function insertComment(obj) {
    try {
        const commentData = await fetch('/boards/free/insertComment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                "id": obj.id.value,
                "comments": obj.comments.value
            }),
        })
        const result = await commentData.json();

        // 댓글 입력창 초기화
        obj.comments.value = "";
        // 댓글 목록 재출력
        selectCommentList(result.id);

    } catch (error) {
        alert('댓글 등록에 실패했습니다.');
    }
}

// 비밀번호 확인 및 삭제
function boardDelete(password) {
    const errorText = document.getElementById("errorText");
    errorText.innerText = "";

    if (password.value === '') {
        errorText.innerText = "비밀번호를 입력해주세요.";
        return false;
    } else if (chkPassword(password)) {
        const xhr = new XMLHttpRequest();
        xhr.open('POST', '/boards/free/process', false);
        xhr.send(JSON.stringify({"p": "D", "id": key.value}));
    }
}

// 비밀번호 확인 - todo.
function chkPassword(password) {
    let rtn = false;

    const xhr = new XMLHttpRequest();
    const url = '/boards/free/chkPassword?id=' + key.value + '&password=' + password.value;
    xhr.open('GET', url, false);
    if (xhr.status === 200) {
        const response = xhr.responseText;
        if (response === "YES") {
            rtn = true;
        } else {
            const errorText = document.getElementById("errorText");
            errorText.innerText = "비밀번호가 일치하지 않습니다.";
        }
    } else {
        console.log(xhr.status);
        alert('비밀번호 확인을 할 수 없습니다.');
    }
    return rtn;
}