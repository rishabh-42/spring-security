package com.springsecurity.springsecurity;

import com.springsecurity.springsecurity.entity.User;
import com.springsecurity.springsecurity.entity.VerificationToken;
import com.springsecurity.springsecurity.repository.UserRepository;
import com.springsecurity.springsecurity.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DataBootstrap {

    @Autowired
    UserRepository userRepository;

    @Autowired
    VerificationTokenRepository verificationTokenRepository;

    @EventListener(ContextRefreshedEvent.class)
    public void init(){
//        User user = new User();
//        user.setEnabled(false);
//        user.setUsername("admin");
//        user.setPassword("admin");
//
//        userRepository.save(user);
//
//        String token= UUID.randomUUID().toString();
//        System.out.println(token);
//        VerificationToken verificationToken = new VerificationToken();
//        verificationToken.setToken(token);
//        verificationToken.setUser(user);
//        verificationTokenRepository.save(verificationToken);


    }
}
