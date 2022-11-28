package com.cefetqps.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("{id}")
    public ResponseEntity<String> getById(@PathVariable("id") int id) {
        //Optional<entityClassName> existingItemOptional = repository.findById(id);

        return new ResponseEntity<>("alou leleo", HttpStatus.OK);
        // if (existingItemOptional.isPresent()) {
        //     return new ResponseEntity<>(existingItemOptional.get(), HttpStatus.OK);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
    }
    
}
