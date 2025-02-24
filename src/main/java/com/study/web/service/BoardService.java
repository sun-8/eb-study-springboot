package com.study.web.service;

import com.study.web.mapper.BoardMapper;
import com.study.web.model.BoardDTO;
import com.study.web.model.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Service
public class BoardService {
    Logger logger = Logger.getLogger(BoardService.class.getName());

    @Autowired
    private BoardMapper boardMapper;

    /**
     * 게시글 총 개수
     * @return
     */
    public int cntBoardList() {

        int cnt = boardMapper.selectAllCnt();

        return cnt;
    }

    /**
     * 게시글 총 개수 (검색조건)
     * @param boardDTO
     * @return
     */
    public int cntBoardList(BoardDTO boardDTO) {

        int cnt = boardMapper.selectCnt(boardDTO);

        return cnt;
    }

    /**
     * 게시판 목록 조회 (검색조건, 페이징)
     * @param srchBoardDTO
     * @param pageDTO
     * @return
     */
    public List<BoardDTO> getBoardList(BoardDTO srchBoardDTO, PageDTO pageDTO) {

        List<BoardDTO> boardList = boardMapper.selectList(srchBoardDTO, pageDTO);

        return boardList;
    }

    /**
     * 게시판 조회
     * @param boardDTO
     * @return
     */
    public BoardDTO getBoard(BoardDTO boardDTO) {

        BoardDTO board = boardMapper.selectBoard(boardDTO);

        return board;
    }

    /**
     * 게시판 등록
     * @param boardDTO
     * @return
     */
    public int insertBoard(BoardDTO boardDTO) {

        int cnt = boardMapper.insertBoard(boardDTO);

        return cnt;
    }

    /**
     * 게시판 수정
     * @param boardDTO
     * @return
     */
    public int updateBoard(BoardDTO boardDTO) {

        int cnt = boardMapper.updateBoard(boardDTO);

        return cnt;
    }

    /**
     * 게시판 삭제
     * @param boardDTO
     * @return
     */
    public int deleteBoard(BoardDTO boardDTO) {

        int cnt = boardMapper.deleteBoard(boardDTO);

        return cnt;
    }

    /**
     * 조회수 증가
     * @param boardDTO
     * @return
     */
    public int plusViews(BoardDTO boardDTO) {

        int cnt = boardMapper.plusViews(boardDTO);

        return cnt;
    }
}
