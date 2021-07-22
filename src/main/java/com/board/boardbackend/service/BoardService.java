package com.board.boardbackend.service;

import com.board.boardbackend.exception.ResourceNotFoundException;
import com.board.boardbackend.model.Board;
import com.board.boardbackend.model.BoardResult;
import com.board.boardbackend.model.User;
import com.board.boardbackend.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserService userService;

    public Board createBoard(Board board) throws ResourceNotFoundException {

        String uid = board.getUid();
        User user = userService.getUserById(uid);
        board.setCreatedAt(new Date());
        return this.boardRepository.save(board);

    }


    public List<Board> getAllBoardsByUser(String uid, Integer limit, Long curTimestamp) throws ResourceNotFoundException{
        User user = userService.getUserById(uid);
        Pageable pageable = PageRequest.of(0, limit);
        Date curDate = new Date(curTimestamp);
        final List<Board> boardsByUID = this.boardRepository.findBoardsByUID(uid, curDate, pageable);
        return boardsByUID;
    }

    public List<BoardResult> getAllBoards(String uid, Integer limit, Long curTimestamp) {
        Pageable pageable = PageRequest.of(0, limit);
        Date curDate = new Date(curTimestamp);
        return this.boardRepository.customizedFindAll(uid, curDate, pageable);
    }

    public Board getBoardById(Long id) throws ResourceNotFoundException {

        return boardRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Board not found for this id: " + id));

    }

    public BoardResult getBoardDetails(String uid, Long boardID) throws ResourceNotFoundException {

        User user = userService.getUserById(uid);
        Board board = getBoardById(boardID);

        BoardResult boardResult = new BoardResult();

        boardResult.setId(boardID);
        boardResult.setName(board.getName());
        boardResult.setEmail(user.getEmail());
        boardResult.setCreatedAt(board.getCreatedAt());
        boardResult.setFirstName(user.getFirstName());
        boardResult.setLastName(user.getLastName());
        boardResult.setPhotoURL(user.getPhotoURL());

        return boardResult;

    }
}
