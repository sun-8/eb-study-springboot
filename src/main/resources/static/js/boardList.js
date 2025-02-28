
// 검색 기능
const searchBtn = document.getElementById("searchBtn");
searchBtn.addEventListener("click", (event) => {
    event.preventDefault();

    const form = document.getElementById("srchForm");
    const nowPage = document.getElementById("nowPage");
    const srchRegDateStart = document.getElementById("srchRegDateStart");
    const srchRegDateEnd = document.getElementById("srchRegDateEnd");

    // 검색조건 적용 후 검색 시 페이지 초기화
    nowPage.value = 1;

    if (srchRegDateStart.value > srchRegDateEnd.value) {
        alert("등록 시작 날짜가 종료 날짜보다 크면 안됩니다.");
        return false;
    } else {
        form.submit();
    }
});

// 페이지 이동 기능
const pageLi = document.querySelectorAll("#page > li");
pageLi.forEach((v)=> {
    v.addEventListener('click',(event)=> {
        event.preventDefault();

        const form = document.getElementById("srchForm");
        const nowPage = document.getElementById("nowPage");
        const startPage = document.getElementById("startPage");
        const endPage = document.getElementById("endPage");

        if (v.id === "goBefore") { // value가 startPage보다 작으면 startPage로
            nowPage.value = (v.value < startPage.value) ? startPage.value : v.value;
        } else if (v.id === "goAfter") { // value가 endPage보다 크면 endPage로
            nowPage.value = (v.value > endPage.value) ? endPage.value : v.value;
        } else {
            nowPage.value = v.value;
        }

        form.submit();
    })
});

// 등록버튼 이동 기능
const regBtn = document.getElementById("regBtn");
regBtn.addEventListener('click', (event) => {
    event.preventDefault();

   location.href="/boards/free/write";
});

// 게시글 상세 이동 기능
const tr = document.querySelectorAll("tbody > tr");
tr.forEach((v) => {
    v.addEventListener("click", (event) => {
        event.preventDefault();

        const seq = event.target.parentElement.dataset.key;
        location.href="/boards/free/" + seq;
    })
})