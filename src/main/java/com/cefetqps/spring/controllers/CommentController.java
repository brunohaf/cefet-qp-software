package com.cefetqps.spring.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cefetqps.spring.models.Comment;
import com.cefetqps.spring.services.CommentServices;

@RestController
@RequestMapping("comment")
public class CommentController {

    CommentServices commentServices;

    public CommentController(CommentServices commentServices) {            
        this.commentServices = commentServices;
    }

    @GetMapping()
    public ResponseEntity<Collection<Comment>> getAll() {
        return new ResponseEntity<>(commentServices.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getById(@PathVariable("id") int id) {
        Optional<Comment> existingUserOptional = commentServices.getById(id);
        
        if (existingUserOptional.isPresent()) {
            return new ResponseEntity<>(commentServices.getById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<Collection<Comment>> getByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(commentServices.getByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/getByPostId/{postId}")
    public ResponseEntity<Collection<Comment>> getByPostId(@PathVariable("postId") int postId) {
        return new ResponseEntity<>(commentServices.getByPostId(postId), HttpStatus.OK);
    }
    
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Comment> insert(@RequestBody Comment comment) {
        try{
            commentServices.add(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
