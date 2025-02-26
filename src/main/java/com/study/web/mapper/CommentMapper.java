package com.study.web.mapper;

import com.study.web.model.CommentDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    /**
     * 게시글에 대한 댓글 목록
     * @param commentDTO
     * @return
     */
    List<CommentDTO> selectList(CommentDTO commentDTO);

    /**
     * 게시글에 대한 댓글 등록
     * @param commentDTO
     * @return
     */
    int insertBoard(CommentDTO commentDTO);

}
