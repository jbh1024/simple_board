package org.iptime.hoonyhoony.simple_board.service;

import org.iptime.hoonyhoony.simple_board.controller.dto.BoardContent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {
    Page<BoardContent> getAllBoard(Pageable pageable);

    BoardContent findById(Long id);

    BoardContent saveBoard(BoardContent boardContent);

    void deleteBoard(BoardContent boardContent);
}
