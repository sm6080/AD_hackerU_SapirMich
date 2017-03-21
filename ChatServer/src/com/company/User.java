package com.company;

/**
 * Created by Sapir Michaeli on 21.03.2017.
 */
public class User {
    private int password;
    private int unReadMessageCounter;

    public User(int password) {
        this.password = password;
        unReadMessageCounter = 0;
    }
}
