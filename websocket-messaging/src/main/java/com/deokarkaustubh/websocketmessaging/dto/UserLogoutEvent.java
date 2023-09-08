package com.deokarkaustubh.websocketmessaging.dto;

public class UserLogoutEvent {

    private String username;

    public UserLogoutEvent(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
