package com.cefetqps.spring.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cefetqps.spring.models.User;
import com.cefetqps.spring.services.UserAuthenticationServices;
import com.cefetqps.spring.services.UserServices;

@RestController
@RequestMapping("users")
public class UserController {

    private UserServices userServices;
    private UserAuthenticationServices userAuthenticationServices;

    public UserController(
        UserServices userServices,
        UserAuthenticationServices userAuthenticationServices) {
            
        this.userServices = userServices;
        this.userAuthenticationServices = userAuthenticationServices;
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getById(@PathVariable("id") int id) {
        Optional<User> existingUserOptional = userServices.getById(id);
        
        if (existingUserOptional.isPresent()) {
            return new ResponseEntity<>(userServices.getById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping()
    public ResponseEntity<Collection<User>> getAll() {
        return new ResponseEntity<>(userServices.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> authenticate(@RequestBody User user) {
        boolean hasloginSucceeded = userAuthenticationServices.login(user);
        return new ResponseEntity<>(
            "User authenticated!",
            hasloginSucceeded ?
            HttpStatus.ACCEPTED :
            HttpStatus.UNAUTHORIZED);
    }
}
