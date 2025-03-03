package com.study.web.mapper;

import com.study.web.model.BoardDTO;
import com.study.web.model.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardMapper {

    /**
     * 게시글 총 개수
     * @return
     */
    int selectAllCnt();

    /**
     * 게시글 총 개수 (검색조건)
     * @param boardDTO
     * @return
     */
    int selectCnt(BoardDTO boardDTO);

    /**
     * 게시판 목록 조회 (검색조건, 페이징)
     * @return
     */
    List<BoardDTO> selectList(@Param("srchBoardDTO") BoardDTO srchBoardDTO,
                              @Param("pageDTO") PageDTO pageDTO);

    /**
     * 게시글 조회
     * @param boardDTO
     * @return
     */
    BoardDTO selectBoard(BoardDTO boardDTO);

    /**
     * 게시글 등록
     * @param boardDTO
     * @return
     */
    int insertBoard(BoardDTO boardDTO);

    /**
     * 게시글 수정
     * @param boardDTO
     * @return
     */
    int updateBoard(BoardDTO boardDTO);

    /**
     * 게시글 비밀번호 확인
     * @param boardDTO
     * @return
     */
    int chkPassword(BoardDTO boardDTO);

    /**
     * 게시글 삭제
     * @param boardDTO
     * @return
     */
    int deleteBoard(BoardDTO boardDTO);

    /**
     * 조회수 중가
     * @param boardDTO
     * @return
     */
    int plusViews(BoardDTO boardDTO);
}
