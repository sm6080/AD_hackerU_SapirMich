package com.company;

import java.util.ArrayList;

/**
 * Created by Sapir Michaeli on 21.03.2017.
 */
public class Message {
    ArrayList <String> messagesList;

    public Message(ArrayList<String> messagesList) {
        this.messagesList = messagesList;
    }

    public void addMessage(String message){
        messagesList.add(message);
    }
}
