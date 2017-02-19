package com.company;

/**
 * Created by Sapir Michaeli on 19.02.2017.
 */
public class User {

    private static final byte ASCII_0 = 48;
    private static final byte ASCII_9 = 57;
    private static final byte ASCII_MINUS = 45;
    private static final byte ASCII_a = 97;
    private static final byte ASCII_z = 122;




    private String userName;
    private String password;


    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private  boolean correctUser(String user) {
        byte[] sBytes = user.getBytes();
        boolean flagDigits = false;// while it`s true - numbers
        for (int i = 0; i < sBytes.length; i++) {
            if (flagDigits) { //
                if (sBytes[i] < ASCII_0 || sBytes[i] > ASCII_9)
                    return false;
                flagDigits = true;

            } else if (sBytes[i] < ASCII_a || sBytes[i] > ASCII_z)
                return false;
        }
        return true;
    }


}
