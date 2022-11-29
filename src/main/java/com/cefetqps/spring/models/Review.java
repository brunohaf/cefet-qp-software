
package com.cefetqps.spring.models;

import java.util.Date;

public class Review { 
    private int id;
    private int userId;
    private int postId;
    private int score;
    private Date createdAt;
    private Date modifiedAt;

    public Review(int id, int userId, int postId, int score) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.score = score;

        Date dateNow = new Date(System.currentTimeMillis());
        this.createdAt = dateNow;
        this.modifiedAt = dateNow;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    public int getScore() {
        return score;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        Date dateNow = new Date(System.currentTimeMillis());
        this.setModifiedAt(dateNow);

        this.score = score;
    }

    private void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "id: " + Integer.toString(id) 
            + " | " + "idUser: " + Integer.toString(userId) 
            + " | " + "postId: " + Integer.toString(postId) 
            + " | "  + "score: " + Integer.toString(score) 
            + " | "  + "createdAt: " + createdAt.toString()
            + " | "  + "modifiedAt: " + modifiedAt.toString();
    }
}
