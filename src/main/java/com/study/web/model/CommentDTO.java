package com.study.web.model;

import lombok.Data;

@Data
public class CommentDTO {
    private int id;
    private String regDttm;
    private String comments;

    public CommentDTO() {}

    public CommentDTO(int id) {
        this.id = id;
    }
}
