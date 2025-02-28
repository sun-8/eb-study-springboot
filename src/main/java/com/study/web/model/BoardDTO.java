package com.study.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardDTO {
    private int id;
    private String categoryId;
    private String categoryName;
    private String userName;
    private String password;
    private String title;
    private String contents;
    private int views;
    private String file1;
    private String file2;
    private String file3;
    private String regDttm;
    private String modDttm;

    // 검색조건
    private String srchRegDateStart;
    private String srchRegDateEnd;
    private String srchCategory;
    private String srchWord;

    public BoardDTO(int id) {
        this.id = id;
    }
}
