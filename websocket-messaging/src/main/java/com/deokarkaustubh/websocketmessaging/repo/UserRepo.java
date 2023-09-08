package com.deokarkaustubh.websocketmessaging.repo;

import com.deokarkaustubh.websocketmessaging.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {

    public static List<User> userList = new ArrayList<>();

    public List<User> getUserList(){
        return userList;
    }

    public void addUser(User user){
        userList.add(user);
    }


}
