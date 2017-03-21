package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;

/**
 * Created by Sapir Michaeli on 21.03.2017.
 */

// התהליכון הזה חייב לדעת מי הם היוזרים
public class ClientThread extends Thread{
    private static final int PORT =3000 ;
    private static final byte SIGN_UP = '1';
    private static final byte LOG_IN = '2';
    private static final byte WRITE_MES = '3';
    private static final byte READ_MES = '4';
    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private HashMap<String ,User > users;
    String userName;
    int password;



    public ClientThread(Socket clientSocket, HashMap<String ,User > users) {
        this.clientSocket = clientSocket;
        inputStream = null;
        outputStream = null;
        this.users=users;
    }

    @Override
    public void run() {
        try {
            int actuallyRead = 0;
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            getUser();
            byte action=(byte)inputStream.read();
            switch (action){
                case SIGN_UP:
                    sign_up();
                    break;
                    case LOG_IN:
                    log_in();
                    break;
                case WRITE_MES:
                    writeMessage();
                    break;
                case READ_MES:
                    readMessage();
                    break;
                default:
                    clientSocket.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
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



    //כותב אחד חדש
    private void sign_up(){
        if (!users.containsKey(userName))
            users.put(userName, new User(password));
    }

    // בודק אם קיים ושולח אותו
    private void log_in() {
    }

    private void writeMessage() {
        int actuallyRead = 0;

    }

    private void readMessage() {

    }

    private void checkIfInt(int actuallyRead) throws Exception {
        if (actuallyRead!=4) {// if is not an int
            clientSocket.close();
            throw new Exception("string, not an integer");
        }
    }

    private void getUser() throws Exception {
        int actuallyRead = 0;
        byte bufferInt[] = new byte[4];
        byte bufferString[];
        actuallyRead=inputStream.read(bufferInt);
        checkIfInt(actuallyRead);
        int userNameLength = ByteBuffer.wrap(bufferInt).getInt();
        bufferString = new byte[userNameLength];
        userName = bufferString.toString();
        actuallyRead=inputStream.read(bufferInt);
        checkIfInt(actuallyRead);
        password = ByteBuffer.wrap(bufferInt).getInt();
    }
}
