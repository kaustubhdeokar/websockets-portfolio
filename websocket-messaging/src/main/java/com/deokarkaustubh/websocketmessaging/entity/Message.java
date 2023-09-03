package com.deokarkaustubh.websocketmessaging.entity;

public class Message {

    private String username;

    private String message;

    @Override
    public String toString() {
        return "Username='" + username + '\'' +
               ", message='" + message + '\'' +
               '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
