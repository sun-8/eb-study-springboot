package com.study.web.service;

import com.study.web.mapper.CategoryMapper;
import com.study.web.model.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CategoryService {
    Logger logger = Logger.getLogger(CategoryService.class.getName());

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 카테고리 모든 데이터 목록 조회
     * @return
     */
    public List<CategoryDTO> getCategoryList() {

        List<CategoryDTO> categoryList = categoryMapper.selectList();

        return categoryList;
    }
}
