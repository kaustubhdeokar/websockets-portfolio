package com.deokarkaustubh.websocketmessaging.dto;

import java.util.Date;

public class UserLoginEvent {
    private String username;
    private Date time;

    public UserLoginEvent(String username) {
        this.username = username;
        time = new Date();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
