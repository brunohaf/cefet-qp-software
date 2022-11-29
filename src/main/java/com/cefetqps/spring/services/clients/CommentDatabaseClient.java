package com.cefetqps.spring.services.clients;

import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.cefetqps.spring.Constants;
import com.cefetqps.spring.models.Comment;
import com.cefetqps.spring.services.interfaces.DatabaseClient;

@Service
@Scope("singleton")
public class CommentDatabaseClient implements DatabaseClient<Comment> {

    private ArrayList<Comment> commentDataBaseList;
    private AtomicInteger commentIdIncrementer = new AtomicInteger(Constants.ID_INCREMENTER_START_OFFSET);

    public CommentDatabaseClient() {
        this.commentDataBaseList = getStaticMockupComments();
    }

    public boolean getConnetion(){
        return true;
    }

    public ArrayList<Comment> getAll(){
        return commentDataBaseList;
    }

    public Optional<Comment> getById(int id){
        return Optional.ofNullable(commentDataBaseList.get(id));
    }

    public boolean save(Comment comment){
        try{
            comment.setId(commentIdIncrementer.incrementAndGet());
            return commentDataBaseList.add(comment);
        }
        catch(Exception ex){
            return false;
        }
    }

    private ArrayList<Comment> getStaticMockupComments(){
        ArrayList<Comment> staticMockupComments = new ArrayList<Comment>();

        staticMockupComments.add(getStaticComment("Gostei muito 😳.", 0, 0));
        staticMockupComments.add(getStaticComment("Bem legal 😁.", 1, 0));
        staticMockupComments.add(getStaticComment("Mid 😐.", 2, 1));

        return staticMockupComments;
    }

    private Comment getStaticComment(String content, int userId, int postId){
        return new Comment(commentIdIncrementer.incrementAndGet(), userId, postId, content);
    }
}
