
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
let delBtn = document.getElementById("delBtn");
delBtn.addEventListener("click", (event) => {
    event.preventDefault();

})

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