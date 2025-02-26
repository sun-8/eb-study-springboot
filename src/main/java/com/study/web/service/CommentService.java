package com.study.web.service;

import com.study.web.mapper.CommentMapper;
import com.study.web.model.CommentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    /**
     * 게시글에 대한 댓글 목록
     * @return
     */
    public List<CommentDTO> getCommentList(CommentDTO commentDTO) {

        List<CommentDTO> commentList = commentMapper.selectList(commentDTO);

        return commentList;
    }

    /**
     * 게시글에 대한 댓글 등록
     * @param commentDTO
     * @return
     */
    public CommentDTO insertComment(CommentDTO commentDTO) {

        int cnt = commentMapper.insertBoard(commentDTO);

        return new CommentDTO(commentDTO.getId());
    }
}
