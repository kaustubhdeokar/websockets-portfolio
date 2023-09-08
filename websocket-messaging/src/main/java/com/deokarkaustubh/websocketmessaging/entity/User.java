package com.deokarkaustubh.websocketmessaging.entity;

import java.util.UUID;

public class User {

    private final String name;

    private final String uuid;

    public User(String name) {
        this.name = name;
        uuid = java.util.UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public String getUUID() {
        return uuid;
    }

}
