package com.study.web.mapper;

import com.study.web.model.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    /**
     * 카테고리 목록 조회
     * @return
     */
    List<CategoryDTO> selectList();
}
