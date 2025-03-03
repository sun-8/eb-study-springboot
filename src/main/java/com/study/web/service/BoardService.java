package com.study.web.service;

import com.study.util.CommonUtil;
import com.study.web.mapper.BoardMapper;
import com.study.web.model.BoardDTO;
import com.study.web.model.MultiFileDTO;
import com.study.web.model.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class BoardService {
    Logger logger = Logger.getLogger(BoardService.class.getName());

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private MultiFileService multiFileService;

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
     * 게시판 등록 - form submit 에서 파일 업로드
     * @param boardDTO
     * @return
     */
    public int insertBoard(BoardDTO boardDTO, Map<String, MultipartFile> fileMap) {

        // 파일 업로드 및 값 셋팅
        for(Map.Entry<String, MultipartFile> entry : fileMap.entrySet()) {
            MultipartFile file = entry.getValue();
            try {
                MultiFileDTO multiFileDTO = multiFileService.upload(file);

                if (multiFileDTO != null) {
                    switch (entry.getKey()) {
                        case "file1Info":
                            boardDTO.setFile1(multiFileDTO.getFileId());
                            break;
                        case "file2Info":
                            boardDTO.setFile2(multiFileDTO.getFileId());
                            break;
                        case "file3Info":
                            boardDTO.setFile3(multiFileDTO.getFileId());
                            break;
                        default:
                            break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int cnt = 0;
        // 유효성 검증
        if (!(CommonUtil.isEmpty(boardDTO.getCategoryId())
                || CommonUtil.isEmpty(boardDTO.getUserName())
                || CommonUtil.isEmpty(boardDTO.getPassword())
                || CommonUtil.isEmpty(boardDTO.getTitle())
                || CommonUtil.isEmpty(boardDTO.getContents()))) {
            cnt = boardMapper.insertBoard(boardDTO);
        }

        return cnt;
    }

    /**
     * 게시판 등록 - js 에서 파일 업로드
     * @param boardDTO
     * @param fileMap
     * @return
     */
    public int insertBoard2(BoardDTO boardDTO, Map<String, String> fileMap) {

        // 파일 업로드 한 값 셋팅 todo. name 배열로 받기
        if (!CommonUtil.isEmpty(fileMap.get("file1Id"))) {
            boardDTO.setFile1(fileMap.get("file1Id"));
        }
        if (!CommonUtil.isEmpty(fileMap.get("file2Id"))) {
            boardDTO.setFile2(fileMap.get("file2Id"));
        }
        if (!CommonUtil.isEmpty(fileMap.get("file3Id"))) {
            boardDTO.setFile3(fileMap.get("file3Id"));
        }

        int cnt = 0;
        // 유효성 검증 todo. validate annotation
        if (!(CommonUtil.isEmpty(boardDTO.getCategoryId())
                || CommonUtil.isEmpty(boardDTO.getUserName())
                || CommonUtil.isEmpty(boardDTO.getPassword())
                || CommonUtil.isEmpty(boardDTO.getTitle())
                || CommonUtil.isEmpty(boardDTO.getContents()))) {
            cnt = boardMapper.insertBoard(boardDTO);
        }

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
     * 게시판 비밀번호 확인
     * @param boardDTO
     * @return
     */
    public int chkPassword(BoardDTO boardDTO) {

        int cnt = boardMapper.chkPassword(boardDTO);

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
