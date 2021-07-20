package com.board.boardbackend.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "boards")
public class Board {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "uid")
    private String uid;

    @ManyToOne
    @JoinColumn(name="uid", referencedColumnName = "uid", nullable=false, insertable = false, updatable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private Date createdAt;

    public Board(){
        super();
    }

    public Board(long id, String uid, String name, Date createdAt){
        super();
        this.id = id;
        this.name = name;
        this.uid = uid;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
