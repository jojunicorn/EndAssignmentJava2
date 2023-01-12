package com.example.endassignment.model;

import javax.crypto.Cipher;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    //use properties
    public String firstName;
    public String lastName;
    public String username;
    public String password;

    public User(String firstName, String lastName, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

}
