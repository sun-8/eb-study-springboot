package com.study.web.service;

import com.study.util.CommonUtil;
import com.study.web.mapper.MultiFileMapper;
import com.study.web.model.MultiFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;

@Service
public class MultiFileService {

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

        String fileName = file.getOriginalFilename();
        String fileFolder = CommonUtil.localNowDate("yyyyMMdd");
        String filePath = System.getProperty("user.dir") + uploadPath + fileFolder;
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

        // 파일 저장
        MultiFileDTO multiFileDTO = new MultiFileDTO();
        multiFileDTO.setFileId(fileId);
        multiFileDTO.setFileFolder(fileFolder);
        multiFileDTO.setFileName(fileName);
        multiFileDTO.setFilePath(filePath);
        multiFileDTO.setFileSize(fileSize);
        multiFileDTO.setFileType(fileType);
        multiFileDTO.setFileExtend(fileExtend);
        int insertCnt = this.insertMultiFile(multiFileDTO);

        File saveFile = new File(filePath, fileId);
        // 파일 업로드
        file.transferTo(saveFile);

        return multiFileDTO;
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
     * 업로드 파일 저장
     * @param multiFileDTO
     * @return
     */
    public int insertMultiFile(MultiFileDTO multiFileDTO) {

        int cnt = multiFileMapper.insertMultiFile(multiFileDTO);

        return cnt;
    }
}
