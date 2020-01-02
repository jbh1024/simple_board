package org.iptime.hoonyhoony.simple_board.repository;

import org.iptime.hoonyhoony.simple_board.repository.entity.BoardEntity;
import org.iptime.hoonyhoony.simple_board.repository.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
}
