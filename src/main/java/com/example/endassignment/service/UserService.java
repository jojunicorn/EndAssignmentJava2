package com.example.endassignment.service;

import com.example.endassignment.database.Database;
import com.example.endassignment.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserService {
    private Database database;
    private List<User>users;
    public UserService(Database database){
        this.database = database;
        users = new ArrayList<>();
    }
    public void getUsersFromDatabase(){
        for(User user : database.users){
            users.add(user);
        }
    }
    public User getUser(String username){
        for(User user : users){
            if(Objects.equals(user.username, username)){
                return user;
            }
        }
        return new User("test", "user", "username", "password");
    }
}
