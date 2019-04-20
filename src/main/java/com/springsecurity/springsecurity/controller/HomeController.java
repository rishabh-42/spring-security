package com.springsecurity.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/home")
    @ResponseBody
    public String home(){
        return "Welcome to home";
    }


    @GetMapping("/")
    @ResponseBody
    public String index(){
        return  "Welcome to index";
    }

    @RequestMapping(value = "/login")
    public String login(){
        return "login";
    }
}
