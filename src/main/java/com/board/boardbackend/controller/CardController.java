package com.board.boardbackend.controller;

import com.board.boardbackend.exception.ResourceNotFoundException;
import com.board.boardbackend.model.Card;
import com.board.boardbackend.model.CardResult;
import com.board.boardbackend.service.CardService;
import org.apache.http.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("cards")
    public Card createCard(@RequestBody Card card,
                           @RequestAttribute("uid") String curUID)
            throws Exception {

        if(curUID != null && curUID.equals(card.getUid())){
            return cardService.createCard(card);
        }

        throw new Exception("Invalid Request");

    }

    @GetMapping("cards/{boardID}")
    public List<CardResult> getAllCardsInBoard(@PathVariable(value = "boardID") Long boardID)
            throws ResourceNotFoundException {
        return cardService.getAllCardsInBoard(boardID);
    }

}
