package org.iptime.hoonyhoony.simple_board.service;

import org.iptime.hoonyhoony.simple_board.controller.dto.BoardContent;
import org.iptime.hoonyhoony.simple_board.exception.CustomException;
import org.iptime.hoonyhoony.simple_board.repository.BoardRepository;
import org.iptime.hoonyhoony.simple_board.repository.entity.BoardEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.iptime.hoonyhoony.simple_board.exception.ErrorCode.REQUEST_ERROR;
import static org.iptime.hoonyhoony.simple_board.exception.ErrorCode.SAVE_ERROR;

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<BoardContent> getAllBoard(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1); // page index 0부터 시작
        pageable = PageRequest.of(page, 10, new Sort(Sort.Direction.DESC, "id")); //최근 데이터부터 역순조회
        //entity -> dto
        Page<BoardEntity> boardEntities = boardRepository.findAll(pageable);
        List<BoardContent> boardContents = boardEntities.stream().map(entity -> new BoardContent(entity.getId(), entity.getTitle(), "", entity.getWriter(), entity.getCreateDate(), entity.getUpdateDate())).collect(Collectors.toList());
        return new PageImpl<BoardContent>(boardContents, pageable, boardEntities.getTotalElements());


    }

    @Override
    @Transactional
    public BoardContent findById(Long id) {
        Optional<BoardEntity> entity = boardRepository.findById(id);
        if (entity.isPresent()) {
            //entity -> dto
            BoardContent boardContent = new BoardContent(entity.get().getId(), entity.get().getTitle(), entity.get().getContent(), entity.get().getWriter(), entity.get().getCreateDate(), entity.get().getUpdateDate());
            return boardContent;
        } else {
            throw new CustomException(REQUEST_ERROR);
        }
    }

    @Override
    @Transactional
    public BoardContent saveBoard(BoardContent boardContent) {
        //dto -> entity
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardContent.getId());
        boardEntity.setTitle(boardContent.getTitle());
        boardEntity.setContent(boardContent.getContent());
        boardEntity.setWriter(boardContent.getWriter());
        BoardEntity entity = boardRepository.save(boardEntity);
        if (!ObjectUtils.isEmpty(entity)) {
            //entity -> dto
            return new BoardContent(entity.getId(), entity.getTitle(), entity.getContent(), entity.getWriter(), entity.getCreateDate(), entity.getUpdateDate());
        } else {
            throw new CustomException(SAVE_ERROR);
        }

    }

    @Override
    @Transactional
    public void deleteBoard(BoardContent boardContent) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardContent.getId()); //삭제시 id값만 필요
        boardRepository.delete(boardEntity);
    }

}
