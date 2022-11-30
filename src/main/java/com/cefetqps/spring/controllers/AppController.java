package com.cefetqps.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("")
public class AppController {

  public AppController() {
  }

  @RequestMapping("/")
  public void redirectToLogin(HttpServletResponse httpServletResponse) {
    httpServletResponse.setHeader("Location", "/users/login");
    httpServletResponse.setStatus(302);
  }
}
