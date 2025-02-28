package com.study.web.service;

import com.study.util.CommonUtil;
import com.study.web.mapper.MultiFileMapper;
import com.study.web.model.MultiFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.StringTokenizer;
import java.util.logging.Logger;

@Service
public class MultiFileService {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${system.upload-path}")
    private String uploadPath;

    @Autowired
    private MultiFileMapper multiFileMapper;

    /**
     * 파일 업로드
     * @param file
     * @throws IOException
     */
    public MultiFileDTO upload(MultipartFile file) throws IOException {

        if (file == null || file.isEmpty()) {
            return null;
        }

        // 파일 정보 셋팅
        MultiFileDTO multiFileDTO = this.fileInfoSetting(file);
        // 파일 저장
        int insertCnt = this.insertMultiFile(multiFileDTO);

        File saveFile = new File(multiFileDTO.getFilePath(), multiFileDTO.getFileId());
        // 파일 업로드
        file.transferTo(saveFile);

        return multiFileDTO;
    }

    /**
     * 파일 다운로드
     * @param fileId
     * @return
     */
    public ResponseEntity<Resource> download(String fileId) throws IOException {

        MultiFileDTO multiFileDTO = this.getMultiFile(new MultiFileDTO(fileId));

        Resource resource = new FileSystemResource(multiFileDTO.getFilePath() + "\\" + fileId);

        // URL 인코딩을 사용하여 파일 이름을 처리 - 한글 처리
        String encodedFileName = URLEncoder.encode(multiFileDTO.getFileName(), "UTF-8")
                .replaceAll("\\+", "%20"); // '+' 대신 공백을 %20으로 변경

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(multiFileDTO.getFileType() + "/" + multiFileDTO.getFileExtend()));
        headers.setContentDispositionFormData("attachment", encodedFileName);

        return ResponseEntity.ok().headers(headers).body(resource);
    }

    /**
     * 날짜별 파일 총 개수
     * @param date
     * @return
     */
    public int cntMultiFileList(String date) {

        int cnt = multiFileMapper.cntMultiFileList(date);

        return cnt;
    }

    /**
     * 파일 조회
     * @param multiFileDTO
     * @return
     */
    public MultiFileDTO getMultiFile(MultiFileDTO multiFileDTO) {

        MultiFileDTO rtnDTO = multiFileMapper.selectMultiFile(multiFileDTO);

        return rtnDTO;
    }

    /**
     * 업로드 파일 저장
     * @param multiFileDTO
     * @return
     */
    public int insertMultiFile(MultiFileDTO multiFileDTO) {

        int cnt = multiFileMapper.insertMultiFile(multiFileDTO);

        return cnt;
    }

    /**
     * 파일 정보 셋팅
     * @param file
     * @return
     */
    public MultiFileDTO fileInfoSetting(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        String fileFolder = CommonUtil.localNowDate("yyyyMMdd");
        String filePath = uploadPath + fileFolder;
        // 오늘일자 폴더 없으면 생성
        File filePathFolder = new File(filePath);
        if (!filePathFolder.exists()) {
            filePathFolder.mkdir();
        }
        long fileSize = file.getSize();
        String fileContentType = file.getContentType();
        StringTokenizer st = new StringTokenizer(fileContentType, "/");
        String fileType = st.nextToken();
        String fileExtend = st.nextToken();
        // 날짜별 파일 개수
        int fileCnt = this.cntMultiFileList(fileFolder);
        String fileId = fileFolder + "_" + (fileCnt + 1) + "_" + fileName;

        MultiFileDTO multiFileDTO = new MultiFileDTO();
        multiFileDTO.setFileId(fileId);
        multiFileDTO.setFileFolder(fileFolder);
        multiFileDTO.setFileName(fileName);
        multiFileDTO.setFilePath(filePath);
        multiFileDTO.setFileSize(fileSize);
        multiFileDTO.setFileType(fileType);
        multiFileDTO.setFileExtend(fileExtend);

        return multiFileDTO;
    }
}
