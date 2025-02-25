package com.study.web.controller;

import com.study.web.model.BoardDTO;
import com.study.web.model.CategoryDTO;
import com.study.web.model.PageDTO;
import com.study.web.service.BoardService;
import com.study.web.service.CategoryService;
import com.study.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"/boards/free/*"})
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private CategoryService categoryService;

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

    @PostMapping(value = "process", params = "p=C")
    public String process(@ModelAttribute BoardDTO boardDTO) {

        int insetCnt = boardService.insertBoard(boardDTO);

        if (insetCnt > 0) {
            return "redirect:/boards/free/list";
        } else {
            return null;
        }
    }
}
