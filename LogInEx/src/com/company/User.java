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
    private static final byte ASCII_TILDA = 126;



    private String userName;
    private String password;

    public User(String userName) {
        this.userName = userName;
    }

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

    public static boolean correctUser(String user) {
        byte[] sBytes = user.getBytes();
        boolean flagDigit = false;
        for (int i = 0; i < sBytes.length; i++) {
            if (sBytes[i] >= ASCII_0 || sBytes[i] <= ASCII_9) {
                if (i == 0)
                    return false;
                flagDigit = true;
            } else {
                if (sBytes[i] >= ASCII_a || sBytes[i] <= ASCII_z)
                    if (flagDigit)
                        return false;
                    else
                        return false;
            }
        }
        return true;
    }

    public boolean isUserAvailable(LinkedList<User> users, String userName) {
        if(getUserFromList(users,userName)!=null)
            return true;
        return false;
    }


    public static boolean correctPassword(String password) {
        int pasLength = password.length();
        if (pasLength < 4 || pasLength > 12)
            return false;
        byte[] sBytes = password.getBytes();
        for (int i = 0; i < sBytes.length; i++) {
            if (sBytes[i] < ASCII_0 || sBytes[i] > ASCII_TILDA)
                return false;
        }
        return true;
    }

    public static boolean validationPassword(String password,String password2 ){
        if (password.equals(password2))
            return true;
        return false;
    }

    public boolean isLogOned(LinkedList<User> users,User u){
        User user=getUserFromList(users,u.userName);
        if (user!=null && user.password.equals(u.password))
            return true;
        return false;
    }

    private User getUserFromList(LinkedList<User> users, String userName){
        for (User u : users) {
            if (u.userName.equals(userName))
                return u;
        }
        return null;
    }





}
