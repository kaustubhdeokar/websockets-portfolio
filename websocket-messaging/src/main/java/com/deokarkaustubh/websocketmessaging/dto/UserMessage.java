package com.deokarkaustubh.websocketmessaging.dto;

public class UserMessage {
    private String messageContent;
    private String userid;

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public UserMessage() {
    }

    public UserMessage(String messageContent, String userid) {
        this.messageContent = messageContent;
        this.userid = userid;
    }
}
