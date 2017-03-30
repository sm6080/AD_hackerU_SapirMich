package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {

        public static final int PORT = 3000;
    public static final String PATH_TO_UPLOADED_FILE = "C:\\Users\\hackeru.HACKERU3\\Desktop\\upload_file.dat";

    public static void main(String[] args) {

        UploadedFile uploadedFile=new UploadedFile(PATH_TO_UPLOADED_FILE);
        ServerSocket serverSocket=null;
        try {
            serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(uploadedFile, clientSocket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}


/* ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            File file=new File("C:\\Users\\hackeru.HACKERU3\\Downloads\\sea.jpg");
            while (true) {
                System.out.println("waiting for incoming communication...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("client connected");
                ClientThread clientThread = new ClientThread(clientSocket,file);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/