package org.iptime.hoonyhoony.simple_board.controller;


import org.iptime.hoonyhoony.simple_board.controller.dto.BoardContent;
import org.iptime.hoonyhoony.simple_board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 공지사항 web용 contorller
 * @author JeongByeongHun
 * @date 2019.12.17
 * */
@Controller
public class BoardWebController {

    @Autowired
    private BoardService boardService;



    /**
     * 공지사항 입력 페이지 이동
     * @param boardContent(null)
     */
    @RequestMapping(value = "/board", method = RequestMethod.GET)
    public String moveBoardInput(BoardContent boardContent) {
        return "boardDetail";
    }

    /**
     * board 번호 입력시 바로 조회후 페이지 이동
     * @param id
     */
    @RequestMapping(value = "board/{id}", method = RequestMethod.GET)
    public String moveBoardDetail(@PathVariable Long id, Model model) {
        model.addAttribute("boardContent", boardService.findById(id));
        return "boardDetail";
    }


    /**
     * 전체 조회 ( 페이징)
     * @param pageable
     */
    @RequestMapping(value = {"/", "/boardList"}, method = RequestMethod.GET)
    public String moveBoardList(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("boardContents", boardService.getAllBoard(pageable));
        return "boardList";
    }

}
