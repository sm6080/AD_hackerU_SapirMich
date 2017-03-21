package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        HashMap<String ,User > users=new HashMap<>();
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(3000);
            while(true){
                Socket socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket, users);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
