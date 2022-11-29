package com.cefetqps.spring.controllers;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cefetqps.spring.models.User;
import com.cefetqps.spring.services.UserAuthenticationServices;
import com.cefetqps.spring.services.UserServices;

@Controller
@SessionAttributes("name")
public class UserController {

    private UserServices userServices;
    private UserAuthenticationServices userAuthenticationServices;

    public UserController(
        UserServices userServices,
        UserAuthenticationServices userAuthenticationServices) {
            
        this.userServices = userServices;
        this.userAuthenticationServices = userAuthenticationServices;
    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
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

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String email, @RequestParam String password){

        boolean isValidUser = userAuthenticationServices.login(new User(email, password));

        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }

        model.put("email", email);
        model.put("password", password);

        return "welcome";
    }

    @PostMapping()
    public ResponseEntity<String> saveUserData(@RequestBody User user) {
        return new ResponseEntity<>(
            "User created!",
            userServices.saveUserData(user) ?
            HttpStatus.ACCEPTED :
            HttpStatus.BAD_REQUEST);
    }
}
