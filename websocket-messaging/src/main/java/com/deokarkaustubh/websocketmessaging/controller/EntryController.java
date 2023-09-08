package com.deokarkaustubh.websocketmessaging.controller;

import com.deokarkaustubh.websocketmessaging.entity.User;
import com.deokarkaustubh.websocketmessaging.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin("*")
public class EntryController {

    UserRepo userRepo;

    @MessageMapping("/entry")
    public void getUserEntryNotification() {
        String suffix = UUID.randomUUID().toString().substring(1, 5);
        User newUser = new User("user" + suffix);
        userRepo.addUser(newUser);
        System.out.println("In the user entry notification.");
    }

}
