package com.study.web.mapper;

import com.study.web.model.MultiFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MultiFileMapper {

    /**
     * 날짜별 파일 총 개수
     * @param date
     * @return
     */
    public int cntMultiFileList(String date);

    /**
     * 파일 정보 조회
     * @param multiFileDTO
     * @return
     */
    public MultiFileDTO selectMultiFile(MultiFileDTO multiFileDTO);

    /**
     * 업로드 파일 저장
     * @param multiFileDTO
     * @return
     */
    public int insertMultiFile(MultiFileDTO multiFileDTO);
}
