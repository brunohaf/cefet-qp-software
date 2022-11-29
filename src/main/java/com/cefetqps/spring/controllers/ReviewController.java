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

import com.cefetqps.spring.models.Review;
import com.cefetqps.spring.services.ReviewServices;

@RestController
@RequestMapping("review")
public class ReviewController {
    ReviewServices evaluationServices;

    public ReviewController(ReviewServices evaluationServices) {            
        this.evaluationServices = evaluationServices;
    }

    @GetMapping()
    public ResponseEntity<Collection<Review>> getAll() {
        return new ResponseEntity<>(evaluationServices.getAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Review> getById(@PathVariable("id") int id) {
        Optional<Review> existingUserOptional = evaluationServices.getById(id);
        
        if (existingUserOptional.isPresent()) {
            return new ResponseEntity<>(evaluationServices.getById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<Collection<Review>> getByUserId(@PathVariable("userId") int userId) {
        return new ResponseEntity<>(evaluationServices.getByUserId(userId), HttpStatus.OK);
    }
    
    @GetMapping("/getByPostId/{postId}")
    public ResponseEntity<Collection<Review>> getByPostId(@PathVariable("postId") int postId) {
        return new ResponseEntity<>(evaluationServices.getByPostId(postId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Review> insert(@RequestBody Review comment) {
        try{
            evaluationServices.add(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
