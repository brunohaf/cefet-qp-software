package com.cefetqps.spring.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cefetqps.spring.models.Post;
import com.cefetqps.spring.services.PostServices;

@Controller
@RequestMapping("posts")
public class PostController {

    private PostServices postServices;

    public PostController(PostServices postServices) {
            
        this.postServices = postServices;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPostsPage(ModelMap model, @RequestParam int userId){
        ArrayList<Post> lista = postServices.getByUserId(userId);
        model.put("postList", lista);
        return "posts";
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable("id") int id) {
        Optional<Post> existingPostOptional = postServices.getById(id);
        
        if (existingPostOptional.isPresent()) {
            return new ResponseEntity<>(postServices.getById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
