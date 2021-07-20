package com.board.boardbackend.repository;

import com.board.boardbackend.model.Board;
import com.board.boardbackend.model.BoardResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from Board b where b.uid = ?1")
    List<Board> findBoardsByUID(String uid);

    @Query(value = "select new com.board.boardbackend.model.BoardResult(b.id, u.firstName, u.lastName, u.photoURL, b.name, b.createdAt, u.email) from Board b join User u on b.uid = u.uid where b.uid != :uid")
    List<BoardResult> customizedFindAll(String uid);
}
