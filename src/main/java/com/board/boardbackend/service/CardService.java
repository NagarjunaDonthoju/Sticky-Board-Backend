package com.board.boardbackend.service;

import com.board.boardbackend.model.CardResult;
import com.board.boardbackend.exception.ResourceNotFoundException;
import com.board.boardbackend.model.Board;
import com.board.boardbackend.model.Card;
import com.board.boardbackend.model.User;
import com.board.boardbackend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    BoardService boardService;

    @Autowired
    UserService userService;

    public Card createCard(Card card) throws Exception{

        if(card.getBoardID() <= 0 || card.getUid() == null){
            throw new Exception("Invalid body");
        }
        else if(card.getDescription().trim() == ""){
            throw new Exception("Card description cannot be empty!");
        }

        User user = userService.getUserById(card.getUid());
        Board board = boardService.getBoardById(card.getBoardID());

        final Date DATE = new Date();
        card.setCreatedAt(DATE);
        card.setUpdatedAt(DATE);
        return this.cardRepository.save(card);
    }

    public List<CardResult> getAllCardsInBoard(Long boardID, Integer limit, Long curTimestamp) throws ResourceNotFoundException {
        Board board = boardService.getBoardById(boardID);
        Pageable pageable = PageRequest.of(0, limit);
        Date curDate = new Date(curTimestamp);
        return cardRepository.findAllCardsInBoard(boardID, curDate, pageable);
    }
}
