package com.cefetqps.spring.services;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.models.Review;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class ReviewServices {

    private DatabaseClient<Review> reviewDataBaseClient;

    @Autowired
    public ReviewServices(DatabaseClient<Review> reviewDataBaseClient) {
        this.reviewDataBaseClient = reviewDataBaseClient;
    }

    public boolean add(Review comment){
        return reviewDataBaseClient.save(comment);
    }

    public Optional<Review> getById(int id){
        try{
            return reviewDataBaseClient.getById(id);
        }
        catch(IndexOutOfBoundsException indexOutOfBoundsException){
            return Optional.of(null);
        }
    }

    public Collection<Review> getAll(){
        return reviewDataBaseClient.getAll();
    }

    public ArrayList<Review> getByUserId(int userId){
        return new ArrayList<Review> (
            reviewDataBaseClient.getAll().stream()
            .filter(n -> n.getUserId() == userId)
            .collect(Collectors.toList())
        );
    }

    public ArrayList<Review> getByPostId(int postId){
        return new ArrayList<Review> (
            reviewDataBaseClient.getAll().stream()
            .filter(n -> n.getPostId() == postId)
            .collect(Collectors.toList())
        );
    }
}
