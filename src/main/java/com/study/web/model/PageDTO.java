package com.study.web.model;

import lombok.Data;

@Data
public class PageDTO {
    private int nowPage = 1;
    private int startIdx = 0;
    private int pageSize = 10;
    private int dataCnt = 0;
    private int srchDataCnt = 0;
    private int startPage = 1;
    private int endPage = 1;
    private int lookPageCnt = 3; // ex) 1 2 3 ... 10


    // todo. setter 매개변수가 기존 변수와 상관 없는 변수여도 괜찮은가?
    // 괜춘. 작명을 다르게 할것 setter 개념 X, 하나의 메서드
    public void setStartIdx(int nowPage) {
        this.startIdx = (nowPage - 1) * this.pageSize;
    }

    public void setEndPage(int srchDataCnt) {
        this.endPage = (srchDataCnt % this.pageSize == 0) ? srchDataCnt / this.pageSize : srchDataCnt / this.pageSize + 1;
    }
}
