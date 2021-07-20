package com.board.boardbackend.model;

import java.util.Date;

public class CardResult {

    private String firstName;
    private String lastName;
    private String photoURL;
    private Date createdAt;
    private Date updatedAt;
    private String description;
    private String email;

    public CardResult(String firstName, String lastName, String email, String photoURL, String description, Date createdAt, Date updatedAt) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.photoURL = photoURL;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.email = email;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
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
