package com.study.web.controller;

import com.study.util.CommonUtil;
import com.study.web.model.*;
import com.study.web.service.BoardService;
import com.study.web.service.CategoryService;
import com.study.web.service.CommentService;
import com.study.web.service.MultiFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = {"/boards/free/*"})
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private MultiFileService multiFileService;

    /**
     * 게시판 목록 화면
     * @param model
     * @param srchBoardDTO
     * @param pageDTO
     * @return
     */
    @GetMapping("list")
    public String list(Model model,
                       @ModelAttribute BoardDTO srchBoardDTO,
                       @ModelAttribute PageDTO pageDTO) {

        // category 목록
        List<CategoryDTO> categoryList = categoryService.getCategoryList();

        // 검색조건
        if (CommonUtil.isEmpty(srchBoardDTO.getSrchRegDateStart())
                && CommonUtil.isEmpty(srchBoardDTO.getSrchRegDateEnd())) {
            srchBoardDTO.setSrchRegDateEnd(CommonUtil.localNowDate());
            srchBoardDTO.setSrchRegDateStart(CommonUtil.localDatePlusY(srchBoardDTO.getSrchRegDateEnd(), -1));
        }

        // 페이징
        pageDTO.setStartIdx(pageDTO.getNowPage());
        pageDTO.setDataCnt(boardService.cntBoardList());
        pageDTO.setSrchDataCnt(boardService.cntBoardList(srchBoardDTO));
        pageDTO.setEndPage(pageDTO.getSrchDataCnt());

        // 게시판 목록
        List<BoardDTO> boardList = boardService.getBoardList(srchBoardDTO, pageDTO);

        model.addAttribute("pageDTO", pageDTO);
        model.addAttribute("srchBoardDTO", srchBoardDTO);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("boardList", boardList);

        return "boards/free/list";
    }

    /**
     * 게시판 상세 화면
     * @param model
     * @param seq
     * @return
     */
    @GetMapping("{seq}")
    public String detail(Model model,
                         @PathVariable("seq") Integer seq) {

        // 조회수 증가
        int plusViews = boardService.plusViews(new BoardDTO(seq));
        // 상세 조회
        BoardDTO boardDTO = boardService.getBoard(new BoardDTO(seq));
        // 파일 정보
        if (!CommonUtil.isEmpty(boardDTO.getFile1())) {
            MultiFileDTO file1Info = multiFileService.getMultiFile(new MultiFileDTO(boardDTO.getFile1()));
            model.addAttribute("file1Info", file1Info);
        }
        if (!CommonUtil.isEmpty(boardDTO.getFile2())) {
            MultiFileDTO file2Info = multiFileService.getMultiFile(new MultiFileDTO(boardDTO.getFile2()));
            model.addAttribute("file2Info", file2Info);
        }
        if (!CommonUtil.isEmpty(boardDTO.getFile3())) {
            MultiFileDTO file3Info = multiFileService.getMultiFile(new MultiFileDTO(boardDTO.getFile3()));
            model.addAttribute("file3Info", file3Info);
        }

        model.addAttribute("boardDTO", boardDTO);

        return "boards/free/view";
    }

    /**
     * 게시판 등록 화면
     * @param model
     * @return
     */
    @GetMapping("write")
    public String write(Model model) {
        // category 목록
        List<CategoryDTO> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "boards/free/write";
    }

//    /**
//     * 게시판 등록 처리 - form submit 에서 파일 업로드
//     * @param boardDTO
//     * @param fileMap
//     * @return
//     */
//    @PostMapping(value = "process", params = "p=C")
//    public String insertData(@ModelAttribute BoardDTO boardDTO,
//                             @RequestParam Map<String, MultipartFile> fileMap) {
//
//        int insertCnt = boardService.insertBoard(boardDTO, fileMap);
//
//        return "redirect:/boards/free/list";
//    }

    /**
     * 게시판 등록 처리 - js 에서 파일 업로드
     * @param boardDTO
     * @param fileMap
     * @return
     */
    @PostMapping(value = "process", params = "p=C")
    public String insertData(@ModelAttribute BoardDTO boardDTO,
                             @RequestParam Map<String, String> fileMap) {

        int insertCnt = boardService.insertBoard2(boardDTO, fileMap);

        return "redirect:/boards/free/list";
    }

    /**
     * 게시글에 대한 댓글 목록
     * @param commentDTO
     * @return
     */
    @ResponseBody
    @PostMapping("selectCommentList")
    public List<CommentDTO> selectCommentList(@RequestBody CommentDTO commentDTO) {

        List<CommentDTO> list = commentService.getCommentList(commentDTO);

        return list;
    }

    /**
     * 게시글에 대한 댓글 등록
     * @param commentDTO
     * @return
     */
    @ResponseBody
    @PostMapping("insertComment")
    public CommentDTO insertComment(@RequestBody CommentDTO commentDTO) {

        CommentDTO rtnDTO = commentService.insertComment(commentDTO);

        return rtnDTO;
    }

}
