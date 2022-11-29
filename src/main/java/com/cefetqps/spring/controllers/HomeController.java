package com.cefetqps.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HomeController {

    @RequestMapping(value="/home", method = RequestMethod.GET)
    public String showHomePage(ModelMap model, @RequestParam int userId){
        model.put("userId", userId);
        //if(session.getAttribute("name") != null){
            return "home";
          //return "login";
        
    }
}
