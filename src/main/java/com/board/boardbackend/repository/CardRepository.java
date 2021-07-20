package com.board.boardbackend.repository;

import com.board.boardbackend.model.CardResult;
import com.board.boardbackend.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    @Query(value = "select new com.board.boardbackend.model.CardResult(u.firstName, u.lastName, u.email, u.photoURL, c.description, c.createdAt, c.updatedAt) from Card c join User u on c.uid = u.uid where c.boardID = :boardID")
    List<CardResult> findAllCardsInBoard(@Param("boardID") Long boardID);
}
