package com.deokarkaustubh.websocketmessaging.controller;


import com.deokarkaustubh.websocketmessaging.dto.ChatMessage;
import com.deokarkaustubh.websocketmessaging.dto.ParticipantRepository;
import com.deokarkaustubh.websocketmessaging.dto.UserLoginEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.net.UnknownServiceException;
import java.security.Principal;
import java.util.Collection;

@Controller
public class ChatController {

    @Autowired
    ParticipantRepository participantRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @SubscribeMapping("/chat.participants")
    public Collection<UserLoginEvent> retrieveParticipants() {
        return participantRepository.getActiveSessions().values();
    }


    @MessageMapping("/chat.message")
    public ChatMessage filterMessage(@Payload ChatMessage message, Principal principal) {
        message.setUsername(principal.getName());
        return message;
    }

    @MessageMapping("/chat.private.{username}")
    public void filterPrivateMessage(@Payload ChatMessage message, @DestinationVariable("username") String username, Principal principal) {
        message.setUsername(principal.getName());
        simpMessagingTemplate.convertAndSend("/user/" + username + "/exchange/amq.direct/chat.message", message);
    }



}