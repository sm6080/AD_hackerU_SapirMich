package com.company;

import java.io.*;
import java.net.Socket;

/**
 * Created by hackeru on 29.03.2017.
 */
public class UploadThread extends Thread {
    public static final int UPLOAD = 101;

  private OutputStream outputStream;
  private Socket clientSocket;
  private File file;


    public UploadThread( Socket clientSocket,File file) {
        this.clientSocket = clientSocket;
        this.file=file;
    }

    @Override
    public void run() {
        InputStream inputStreamFile = null;
            try {
                outputStream = clientSocket.getOutputStream();
                inputStreamFile = new FileInputStream(file);
                outputStream.write(UPLOAD);
                outputStream.write(file.getName().length());
                outputStream.write(file.getName().getBytes());
                int oneByte;
                while ((oneByte = inputStreamFile.read()) != -1) {
                    byte b = (byte) oneByte;
                    outputStream.write(b);
                }
            } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamFile != null)
                try {
                    inputStreamFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
