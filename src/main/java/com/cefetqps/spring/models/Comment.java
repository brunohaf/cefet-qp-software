package com.cefetqps.spring.models;

import java.util.Date;

public class Comment {
    private int id;
    private int userId;
    private int postId;
    private String content;
    private Date createdAt;
    private Date modifiedAt;

    public Comment(int id, int userId, int postId, String content) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.content = content;

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

    public String getContent() {
        return content;
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
    
    public void setContent(String content) {
        Date dateNow = new Date(System.currentTimeMillis());
        this.setModifiedAt(dateNow);

        this.content = content;
    }

    private void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String toString() {
        return "id: " + Integer.toString(id) 
            + " | " + "idUser: " + Integer.toString(userId) 
            + " | " + "postId: " + Integer.toString(postId) 
            + " | "  + "content: " + content 
            + " | "  + "createdAt: " + createdAt.toString()
            + " | "  + "modifiedAt: " + modifiedAt.toString();
    }
}