package com.deokarkaustubh.websocketmessaging.controller;

import com.deokarkaustubh.websocketmessaging.dto.Message;
import com.deokarkaustubh.websocketmessaging.dto.ResponseMessage;
import com.deokarkaustubh.websocketmessaging.dto.UserMessage;
import com.deokarkaustubh.websocketmessaging.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
@CrossOrigin("*")
public class MessageController {

    @Autowired
    private WebSocketService webSocketService;

    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public ResponseMessage getMessage(final Message message) {
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public ResponseMessage getPrivateMessage(final Message message) {
        return new ResponseMessage(HtmlUtils.htmlEscape(message.getMessageContent()));
    }

    @MessageMapping("/private-message-user")
    @SendToUser("/topic/private-messages")
    public void getPrivateMessageToUser(@RequestBody final UserMessage message) {
        System.out.println("ID of user:" + message.getUserid());
        webSocketService.notifyUser(message.getUserid(), message.getMessageContent());
    }
}
