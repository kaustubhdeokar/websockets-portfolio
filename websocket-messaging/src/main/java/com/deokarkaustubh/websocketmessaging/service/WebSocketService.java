package com.deokarkaustubh.websocketmessaging.service;

import com.deokarkaustubh.websocketmessaging.dto.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private final SimpMessagingTemplate messageTemplate;

    public WebSocketService(@Autowired SimpMessagingTemplate messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public void notifyFrontend(final String message){
        ResponseMessage responseMessage = new ResponseMessage(message);
        messageTemplate.convertAndSend("/topic/messages",responseMessage);
    }

    public void notifyUser(final String id, final String message){
        ResponseMessage responseMessage = new ResponseMessage(message);
        messageTemplate.convertAndSendToUser(id, "/topic/private-messages",responseMessage);
    }

}
