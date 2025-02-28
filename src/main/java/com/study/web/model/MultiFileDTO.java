package com.study.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MultiFileDTO {

    private String fileId;
    private String fileFolder;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileType;
    private String fileExtend;
    private String regDttm;
    private String modDttm;

    public MultiFileDTO(String fileId) {
        this.fileId = fileId;
    }
}
