package com.study.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDTO {
    private int id;
    private String regDttm;
    private String comments;

    public CommentDTO(int id) {
        this.id = id;
    }
}
