package com.yeonjae.board.repository;

import com.yeonjae.board.domain.Board;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{

    //List<Board> findAll();

    Optional<Board> findById(Long id);

}
