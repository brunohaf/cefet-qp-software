package com.cefetqps.spring.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.models.Comment;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class CommentServices {

    private DatabaseClient<Comment> commentsDataBaseClient;

    @Autowired
    public CommentServices(DatabaseClient<Comment> commentsDataBaseClient) {
        this.commentsDataBaseClient = commentsDataBaseClient;
    }

    public boolean add(Comment comment){
        return commentsDataBaseClient.save(comment);
    }

    public Optional<Comment> getById(int id){
        try{
            return commentsDataBaseClient.getById(id);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return Optional.of(null);
        }
    }

    public Collection<Comment> getAll(){
        return commentsDataBaseClient.getAll();
    }
    
    public ArrayList<Comment> getByUserId(int userId){
        return new ArrayList<Comment> (
            commentsDataBaseClient.getAll().stream()
            .filter(n -> n.getUserId() == userId)
            .collect(Collectors.toList())
        );
    }

    public ArrayList<Comment> getByPostId(int postId){
        return new ArrayList<Comment> (
            commentsDataBaseClient.getAll().stream()
            .filter(n -> n.getPostId() == postId)
            .collect(Collectors.toList())
        );
    }
}
