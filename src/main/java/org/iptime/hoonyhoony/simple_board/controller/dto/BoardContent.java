package org.iptime.hoonyhoony.simple_board.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardContent {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
