package com.example.endassignment.service;

import com.example.endassignment.database.Database;
import com.example.endassignment.model.User;
import javafx.beans.Observable;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Objects;

public class LoginService {
    private UserService userService;
    private Database database;

    public LoginService(Database database){
        userService = new UserService(database);
        this.database = database;
    }

    public boolean checkLogin(String username, String password){
        //List<User> userList = userService.getUsersFromDatabase();
        //ObservableList<User> userlist = userService.getUsersFromDatabase();
        for(User user : database.users){
            if(Objects.equals(user.username, username)){
                if(Objects.equals(user.password, password)){
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
