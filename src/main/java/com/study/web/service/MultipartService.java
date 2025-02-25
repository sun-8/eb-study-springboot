package com.study.web.service;

import com.study.web.mapper.MultiFileMapper;
import com.study.web.model.MultiFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.UUID;

@Service
public class MultipartService {

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
        String filePath = System.getProperty("user.dir") + uploadPath;
        long fileSize = file.getSize();
        String fileContentType = file.getContentType();
        StringTokenizer st = new StringTokenizer(fileContentType, "/");
        String fileType = st.nextToken();
        String fileExtend = st.nextToken();
        UUID uuid = UUID.randomUUID();
        String fileId = uuid + "_" + fileName;

        // 파일 저장
        MultiFileDTO multiFileDTO = new MultiFileDTO();
        multiFileDTO.setFileId(fileId);
        multiFileDTO.setFileName(fileName);
        multiFileDTO.setFilePath(filePath);
        multiFileDTO.setFileSize(fileSize);
        multiFileDTO.setFileType(fileType);
        multiFileDTO.setFileExtend(fileExtend);
        int uploadCnt = this.insertMultiFile(multiFileDTO);

        File saveFile = new File(filePath, fileId);
        // 파일 업로드
        file.transferTo(saveFile);

        return multiFileDTO;
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
