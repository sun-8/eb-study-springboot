
// 파일 업로드
function fileUpload(file, obj) {
    let formData = new FormData();
    formData.append("file", file);

    let xhr = new XMLHttpRequest();

    // 요청 초기화
    xhr.open('POST', '/upload', true);

    // 요청 완료 후 처리
    xhr.onreadystatechange = () => {
        // 정상 처리
        if (xhr.readyState == 4 && xhr.status == 200) {
            obj.originName.value = file.name;
            alert('파일 업로드에 성공했습니다.');
        } else {
            obj.inputFile.value = "";
            alert('파일 업로드에 실패했습니다.');
        }
    }

    // 파일 전송
    xhr.send(formData);
}