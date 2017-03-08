package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Main {

    public static final int PORT = 3000;

    //socket client
    public static void main(String[] args) {
        try {
            Socket socket=new Socket("127.0.0.1", PORT);
            InputStream inputStream=socket.getInputStream();
            OutputStream outputStream=socket.getOutputStream();
            // get two numbers
            Scanner scanner = new Scanner(System.in);
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();

            // convert to byte
            byte[] bytes=new byte[4];
            ByteBuffer.wrap(bytes).putInt(num1);
            outputStream.write(bytes);
            ByteBuffer.wrap(bytes).putInt(num2);
            outputStream.write(bytes);

            byte [] buffer =new byte[4];
            int actuallyRead=inputStream.read(buffer); //x
            actuallyRead=inputStream.read(buffer); //y
            actuallyRead=inputStream.read(buffer); //j
            int j= ByteBuffer.wrap(buffer).getInt();
            System.out.println(j);
            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
