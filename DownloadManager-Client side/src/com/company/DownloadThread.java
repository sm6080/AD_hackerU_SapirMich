/*
package com.company;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

*/
/**
 * Created by Sapir on 29.03.2017.
 *//*

public class DownloadThread extends Thread {
    public static final int PORT = 3000;
    public static final String SERVER_IP = "127.0.0.1";
    public static final int DOWNLOAD = 100;
    public static final int UPLOAD = 101;

    Socket clientSocket;
    String path;
    InputStream inputStream = null;
    OutputStream outputStream = null;

    public DownloadThread(Socket clientSocket,String path) {
        this.clientSocket = clientSocket;
        this.path=path;
    }

    @Override
    public void run() {
        try{
            clientSocket = new Socket(SERVER_IP, PORT);
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            outputStream.write(DOWNLOAD);
            int actuallyRead;
            actuallyRead=inputStream.read();  //size
            byte[] inputBytes = new byte[actuallyRead];// arr with this size
            actuallyRead=inputStream.read(inputBytes); // read the name
            String fileName=new String(inputBytes);
            File file=new File(path+"\\"+fileName+".jpg");
            OutputStream outputStreamFile=new FileOutputStream(file);
            while ((actuallyRead=inputStream.read())!=-1){
                outputStreamFile.write((byte)actuallyRead);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            handleCloseOfStreams();
        }
    }

    private void handleCloseOfStreams() {
        if (inputStream != null)
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        if (outputStream != null)
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }


}



*/
