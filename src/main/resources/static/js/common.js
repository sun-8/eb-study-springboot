
// 파일 업로드
// function fileUpload(file, obj) {
//     let formData = new FormData();
//     formData.append("file", file);
//
//     let xhr = new XMLHttpRequest();
//
//     // 요청 초기화
//     xhr.open('POST', '/upload', true);
//
//     // 요청 완료 후 처리
//     xhr.onreadystatechange = () => {
//         // 요청 완료
//         if (xhr.readyState == 4) {
//             if (xhr.status == 200) {
//                 let response = JSON.parse(xhr.responseText);
//
//                 obj.fileName.value = response.fileName;
//                 obj.fileId.value = response.fileId;
//             } else {
//                 obj.fileId.value = "";
//                 alert('파일 업로드에 실패했습니다.');
//             }
//         }
//     }
//
//     // 파일 전송
//     xhr.send(formData);
// }
async function fileUpload(file, obj) {
    let formData = new FormData();
    formData.append("file", file);

    try {
        let xhr = await fetch('/upload', {
            method: 'POST',
            body: formData,
        })

        if (!xhr) throw new Error('오류!!!');

        let result = await xhr.json();

        obj.fileName.value = result.fileName;
        obj.fileId.value = result.fileId;

    } catch (error) {
        obj.fileId.value = "";
        alert('파일 업로드에 실패했습니다.');
    }
}