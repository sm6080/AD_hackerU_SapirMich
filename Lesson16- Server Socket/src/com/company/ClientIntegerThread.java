package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;

/**
 * Created by Sapir Michaeli on 08.03.2017.
 */
public class ClientIntegerThread extends Thread{
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public ClientIntegerThread(Socket socket) {
        this.socket = socket;
        inputStream=null;
        outputStream=null;
    }

    @Override
    public void run() {
        try {
            int x, y, j=0;
            int actuallyRead = 0;
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            char op=(char)inputStream.read(); // get the char
            byte[] buffer = new byte[4];
            actuallyRead = inputStream.read(buffer);
            x = ByteBuffer.wrap(buffer).getInt();
            actuallyRead = inputStream.read(buffer);
            y = ByteBuffer.wrap(buffer).getInt();

            switch (op){
                case '+':j=plus(x,y);
                    break;
                case '-':j=subtraction(x,y);
                    break;
                case '*':j=mult(x,y);
                    break;
                case '/':j=divition(x,y);
                    break;
                default:
                    if (socket!=null)
                        socket.close();
            }
            ByteBuffer.wrap(buffer).putInt(j);
            outputStream.write(buffer);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (outputStream != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            if (socket != null)
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public int plus(int x,int y) throws IOException{
        return x+y;
    }
    public int subtraction (int x,int y) throws IOException{
        return x-y;
    }
    public int mult (int x,int y) throws IOException{
        return x*y;
    }
    public int divition (int x,int y) throws IOException{
        if (y==0)
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return x/y;
    }
}
