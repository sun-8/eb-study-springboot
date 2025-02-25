package com.study.web.mapper;

import com.study.web.model.MultiFileDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MultiFileMapper {

    /**
     * 업로드 파일 저장
     * @param multiFileDTO
     * @return
     */
    public int insertMultiFile(MultiFileDTO multiFileDTO);
}
