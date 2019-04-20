package com.springsecurity.springsecurity.controller;

import com.springsecurity.springsecurity.entity.User;
import com.springsecurity.springsecurity.repository.UserRepository;
import com.springsecurity.springsecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @Autowired
    UserRepository userRepository;
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

    @GetMapping("/enableUser")
    @ResponseBody

    public String enableUser(String token){
        User user =verificationTokenRepository.findByToken(token).getUser();
        user.setEnabled(true);
        userRepository.save(user);
        return "user enabled";
    }


}
