package org.iptime.hoonyhoony.simple_board.controller;

import lombok.extern.slf4j.Slf4j;
import org.iptime.hoonyhoony.simple_board.controller.dto.BoardContent;
import org.iptime.hoonyhoony.simple_board.exception.CustomException;
import org.iptime.hoonyhoony.simple_board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.iptime.hoonyhoony.simple_board.exception.ErrorCode.INTERNAL_ERROR;

/**
 * 공지사항 api용 contorller
 * @author JeongByeongHun
 * @date 2019.12.19
 * */
@Slf4j
@Controller
@RequestMapping("/api")
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    /**
     * 공지 조회 메소드 id값으로 조회
     * @param id
     */
    //board 번호 입력시 바로 조회
    @RequestMapping(value = "board/{id}", method = RequestMethod.GET)
    public @ResponseBody
    BoardContent redirect(@PathVariable Long id) {
        return boardService.findById(id);
    }

    /**
     * 공지 생성 메소드
     * @param boardContent (title, writer, content 필수)
     */
    @RequestMapping(value = "/board", method = RequestMethod.POST)
    public ResponseEntity<BoardContent> writeBoard(@RequestBody BoardContent boardContent, HttpServletRequest request) {
        log.info("POST(create) method" + boardContent.toString());
        BoardContent result = boardService.saveBoard(boardContent);
        if (result.getId() != null)
            return ResponseEntity.ok(result);
        else {
            throw new CustomException(INTERNAL_ERROR);
        }
    }

    /**
     * 공지 수정 메소드
     * @param boardContent (id 필수)
     */
    @RequestMapping(value = "/board", method = RequestMethod.PUT)
    public ResponseEntity<BoardContent> updateBoard(@RequestBody BoardContent boardContent, HttpServletRequest request) {
        log.info("PUT(update) method" + boardContent.toString());
        if (boardContent.getId() != null) {
            BoardContent result = boardService.saveBoard(boardContent);
            return ResponseEntity.ok(result);
        } else {
            throw new CustomException(INTERNAL_ERROR);
        }
    }

    /**
     * 공지 삭제 메소드
     * @param boardContent (id 필수)
     */
    @RequestMapping(value = "/board", method = RequestMethod.DELETE)
    public ResponseEntity<BoardContent> deleteBoard(@RequestBody BoardContent boardContent, HttpServletRequest request) {
        log.info("DELETE(delete) method" + boardContent.toString());
        boardService.deleteBoard(boardContent);
        return ResponseEntity.ok(boardContent);
    }

}
