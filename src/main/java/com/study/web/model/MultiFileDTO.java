package com.study.web.model;

import lombok.Data;

@Data
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

    public MultiFileDTO() {}

    public MultiFileDTO(String fileId) {
        this.fileId = fileId;
    }
}
