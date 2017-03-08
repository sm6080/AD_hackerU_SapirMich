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
    Socket socket;
    InputStream inputStream;
    OutputStream outputStream;

    public ClientIntegerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            int x, y, j;
            int actuallyRead = 0;
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            byte[] buffer = new byte[4];
            actuallyRead = inputStream.read(buffer);
            x = ByteBuffer.wrap(buffer).getInt();
            actuallyRead = inputStream.read(buffer);
            y = ByteBuffer.wrap(buffer).getInt();
            j = plus(x, y);
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

    public int plus(int x,int y){
        System.out.println(x+""+y);
        return x+y;
    }
}
