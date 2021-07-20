package com.board.boardbackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "uid")
    private String uid;

    @Column(name = "board_id")
    private long boardID;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name="uid", referencedColumnName = "uid", nullable=false, insertable = false, updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="board_id", referencedColumnName = "id", nullable=false, insertable = false, updatable = false)
    private Board board;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getBoardID() {
        return boardID;
    }

    public void setBoardID(long boardID) {
        this.boardID = boardID;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
