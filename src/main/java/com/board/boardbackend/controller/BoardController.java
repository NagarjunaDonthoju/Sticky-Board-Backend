package com.board.boardbackend.controller;


import com.board.boardbackend.exception.ResourceNotFoundException;
import com.board.boardbackend.model.Board;
import com.board.boardbackend.model.BoardResult;
import com.board.boardbackend.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class BoardController {

    @Autowired
    BoardService boardService;

    @PostMapping("boards")
    public Board createBoard(@RequestBody Board board) throws ResourceNotFoundException {

        return boardService.createBoard(board);
    }

    @GetMapping("boards")
    public List<BoardResult> getAllBoards(@RequestAttribute("uid") String uid){

        return boardService.getAllBoards(uid);
    }

    @GetMapping("boards/{uid}")
    public List<Board> getAllBoardsByUser(@PathVariable(value = "uid") String uid,
                                          @RequestAttribute("uid") String curUID)
            throws Exception {

        if(curUID != null && curUID.equals(uid)){
            return boardService.getAllBoardsByUser(uid);
        }

        throw new Exception("Unauthorized access");
    }

    @GetMapping("boards/{uid}/{boardID}")
    public BoardResult getBoardDetails(@PathVariable(value = "uid") String uid,
                                            @PathVariable(value = "boardID") Long boardID,
                                            @RequestAttribute("uid") String curUID)
            throws Exception {

        if(curUID != null && curUID.equals(uid)){
            return boardService.getBoardDetails(uid, boardID);
        }

        throw new Exception("Unauthorized access");
    }

}
