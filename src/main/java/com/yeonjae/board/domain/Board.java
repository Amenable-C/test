package com.yeonjae.board.domain;

import com.yeonjae.board.DTO.WritingDTO;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Board {

    public Board(WritingDTO form) {
        id = form.getId();
        title = form.getTitle();
        content = form.getContent();

    }

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String userName;

    private String title;

    private String content;

    private LocalDateTime createDate;


}
