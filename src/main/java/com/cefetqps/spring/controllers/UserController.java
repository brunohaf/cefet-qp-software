package com.cefetqps.spring.controllers;

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

import com.cefetqps.spring.models.User;
import com.cefetqps.spring.services.UserAuthenticationServices;
import com.cefetqps.spring.services.UserServices;

import jakarta.servlet.http.HttpServletResponse;

@Controller
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

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model) {
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterPage(ModelMap model) {
        return "register";
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) {
        Optional<User> existingUserOptional = userServices.getById(id);

        if (existingUserOptional.isPresent()) {
            return new ResponseEntity<>(userServices.getById(id).get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showWelcomePage(ModelMap model, @RequestParam String email, @RequestParam String password) {

        boolean isValidUser = userAuthenticationServices.login(new User(email, password));
        if (!isValidUser) {
            model.put("errorMessage", "Invalid Credentials");
            return "login";
        }

        User userData = userServices.getByEmail(email);

        model.put("userId", userData.getId());
        model.put("user", userData);
        model.put("name", userData.getEmail().split("@")[0]);
        return "welcome";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(HttpServletResponse httpServletResponse, ModelMap model, @RequestParam String email,
            @RequestParam String password) {
        try {
            boolean isUserRegistered = userAuthenticationServices.register(new User(email, password));
            if (!isUserRegistered) {
                model.put("errorMessage", "User already exists");
                return "register";
            }
            httpServletResponse.sendRedirect("/users/login");
            return "login";
        } catch (Exception e) {
            model.put("errorMessage", e.getMessage());
            return "register";
        }
    }

    @PostMapping()
    public ResponseEntity<String> saveUserData(@RequestBody User user) {
        return new ResponseEntity<>(
                "User created!",
                userServices.saveUserData(user) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST);
    }
}
