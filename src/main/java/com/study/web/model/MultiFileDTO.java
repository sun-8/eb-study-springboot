package com.study.web.model;

import lombok.Data;

@Data
public class MultiFileDTO {

    private String fileId;
    private String fileName;
    private String filePath;
    private long fileSize;
    private String fileType;
    private String fileExtend;
    private String regDttm;
    private String modDttm;
}
