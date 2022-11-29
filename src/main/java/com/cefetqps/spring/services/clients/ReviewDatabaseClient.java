package com.cefetqps.spring.services.clients;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.Constants;
import com.cefetqps.spring.models.Review;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class ReviewDatabaseClient implements DatabaseClient<Review> {

    private ArrayList<Review> reviewDataBaseList;
    private AtomicInteger reviewIdIncrementer = new AtomicInteger(Constants.ID_INCREMENTER_START_OFFSET);

    public ReviewDatabaseClient() {
        this.reviewDataBaseList = getStaticMockupReviews();
    }

    public boolean getConnetion(){
        return true;
    }

    public ArrayList<Review> getAll(){
        return reviewDataBaseList;
    }

    public Optional<Review> getById(int id){
        return Optional.ofNullable(reviewDataBaseList.get(id));
    }

    public boolean save(Review review){
        try{
            review.setId(reviewIdIncrementer.incrementAndGet());
            return reviewDataBaseList.add(review);
        }
        catch(Exception ex){
            return false;
        }
    }

    private ArrayList<Review> getStaticMockupReviews(){
        ArrayList<Review> staticMockupReviews = new ArrayList<Review>();

        staticMockupReviews.add(getStaticReview(10, 0, 0));
        staticMockupReviews.add(getStaticReview(9, 1, 0));
        staticMockupReviews.add(getStaticReview(5, 2, 1));

        return staticMockupReviews;
    }

    private Review getStaticReview(int score, int userId, int postId){
        return new Review(reviewIdIncrementer.incrementAndGet(), userId, postId, score);
    }
}
