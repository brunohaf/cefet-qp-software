package com.cefetqps.spring.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cefetqps.spring.models.Post;
import com.cefetqps.spring.services.PostServices;

@RestController
@RequestMapping("posts")
public class PostController {

    private PostServices postServices;

    public PostController(PostServices postServices) {
            
        this.postServices = postServices;
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable("id") int id) {
        Optional<Post> existingPostOptional = postServices.getById(id);
        
        if (existingPostOptional.isPresent()) {
            return new ResponseEntity<>(postServices.getById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<ArrayList<Post>> getByUserId(@RequestParam(value="userId") int userId) {
        return new ResponseEntity<>(postServices.getByUserId(userId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> savePostData(@RequestBody Post post) {
        return new ResponseEntity<>(
            "Post created!",
            postServices.savePostData(post) ?
            HttpStatus.ACCEPTED :
            HttpStatus.BAD_REQUEST);
    }
}
