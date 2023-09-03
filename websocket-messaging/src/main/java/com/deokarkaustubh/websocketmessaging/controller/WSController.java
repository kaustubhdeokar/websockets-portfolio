package com.deokarkaustubh.websocketmessaging.controller;

import com.deokarkaustubh.websocketmessaging.dto.Message;
import com.deokarkaustubh.websocketmessaging.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WSController {

    @Autowired private WebSocketService webSocketService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody final Message message){
        webSocketService.notifyFrontend(message.getMessageContent());
    }

    @PostMapping("/send-private-message/{id}")
    public void sendMessage( @PathVariable String id, @RequestBody final Message message){
        webSocketService.notifyUser(id, message.getMessageContent());
    }
}
