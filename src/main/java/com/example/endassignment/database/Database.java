package com.example.endassignment.database;

import com.example.endassignment.model.Item;
import com.example.endassignment.model.User;

import java.util.ArrayList;
import java.util.List;

public class Database {

    public List<User>users;
    public List<Item>items;

    public Database(){
        createUserCollection();
        //items = createItemCollection();
    }
    private void createUserCollection(){
        users = new ArrayList<>();
        users.add(new User("Johanna", "Becker", "jojibc","Secret123"));
        users.add(new User("user","number 2","user2","123Secret"));
    }
    private void createItemCollection(){
        items = new ArrayList<>();

    }
}
