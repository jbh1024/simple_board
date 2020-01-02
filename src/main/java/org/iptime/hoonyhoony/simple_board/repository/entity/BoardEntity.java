package org.iptime.hoonyhoony.simple_board.repository.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Table(name = "board")
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String writer;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    @Column(name = "updateDate", updatable = true)
    private LocalDateTime updateDate;

}
