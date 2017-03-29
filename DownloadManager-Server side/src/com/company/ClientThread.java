package com.company;

import java.io.*;
import java.net.Socket;

/**
 * Created by Sapir on 29.03.2017.
 */
public class ClientThread extends Thread {

    Socket clientSocket;
    UploadedFile uploadedFile;
    private InputStream inputStream;
    private OutputStream outputStream;
    private  OutputStream fileOutputStream;
    private FileInputStream fileInputStream;

    public static final int UPLOAD = 100;
    public static final int DOWNLOAD = 101;
    public static final int OK = 101;
    public static final int FAIL = 101;


    public ClientThread(UploadedFile uploadedFile, Socket clientSocket) {
        this.uploadedFile = uploadedFile;
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action=inputStream.read();
            switch (action) {
                case UPLOAD:
                    upload();
                    break;
                case DOWNLOAD:
                    download();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            handleCloseOfStreams();
            if(fileOutputStream!=null)
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(fileInputStream!=null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if(clientSocket!=null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

    }

    // מעלים אל השרת
    private void upload() throws IOException {
        boolean lock=false;
        synchronized (uploadedFile){
            if (!uploadedFile.isLocked()){  // כשיש הצלחה הוא משנה מצב
                uploadedFile.lock();  // נועלים לכבודך !
                lock=false;
            }
        }
        outputStream.write(lock?FAIL:OK);
        if (lock)
            return;
        int fileNameLength=inputStream.read();
        if (fileNameLength==-1) {
            uploadedFile.unlock();
            return;
        }
        byte[] fileNameBytes=new byte[fileNameLength];
        int actuallyRead=inputStream.read(fileNameBytes);
        if (actuallyRead!=fileNameLength) {
            uploadedFile.unlock();
            return;
        }
        fileOutputStream=new FileOutputStream(uploadedFile);
        int oneByte;
        while ((oneByte = inputStream.read()) != -1) {
            fileOutputStream.write(oneByte);
            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fileOutputStream.close();
        fileOutputStream=null;
        uploadedFile.setFileNameBytes(fileNameBytes);
        uploadedFile.increaseVersion();
    }

    private void download() throws IOException {
        if (uploadedFile.isLocked()) {
            outputStream.write(FAIL);
            return;
        } else
            outputStream.write(OK);
        fileInputStream = new FileInputStream(uploadedFile);
        //TODO:: reading/downloading lock
        int oneByte;
        while ((oneByte = fileInputStream.read()) != -1) {
            outputStream.write(oneByte);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        fileInputStream.close();
        fileInputStream = null;
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







/*public static final int DOWNLOAD = 100;
    public static final int UPLOAD = 101;
    public static final String PATH = "c:\\sapir";

    private Socket clientSocket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private File file;

    public ClientThread(Socket clientSocket, File file) {
        this.clientSocket = clientSocket;
        this.file = file;
    }

    @Override
    public void run() {
        try {
            inputStream = clientSocket.getInputStream();
            outputStream = clientSocket.getOutputStream();
            int action = inputStream.read();
            switch (action) {
                case DOWNLOAD:
                    download();
                    break;
                case UPLOAD:
                    upload();
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            if (clientSocket != null)
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            handleCloseOfStreams();
        }
    }

    private void download() throws IOException {
        outputStream.write(file.getName().length());
        outputStream.write(file.getName().getBytes());

        InputStream inputStreamFile = null;
        try {
            inputStreamFile = new FileInputStream(file);
            int oneByte;
            while ((oneByte = inputStream.read()) != -1) {
                byte b = (byte) oneByte;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStreamFile != null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    private void upload() throws IOException {
        inputStream = clientSocket.getInputStream();
        outputStream = clientSocket.getOutputStream();
        outputStream.write(UPLOAD);
        int actuallyRead = inputStream.read();  //size
        byte[] inputBytes = new byte[actuallyRead];// arr with this size
        actuallyRead = inputStream.read(inputBytes); // read the name
        String fileName = new String(inputBytes);
        File file = new File(PATH + "\\" + fileName + ".jpg");
        OutputStream outputStreamFile = new FileOutputStream(file);
        while ((actuallyRead = inputStream.read()) != -1) {
            outputStreamFile.write((byte) actuallyRead);
        }
    }



*/