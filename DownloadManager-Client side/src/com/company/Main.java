package com.company;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

// just for practice - not needed
enum MenuChoice{
    UPLOAD, DOWNLOAD
}
public class Main {
    public static final int UPLOAD = 100;
    public static final int DOWNLOAD = 101;
    public static final int OK = 101;
    public static final int FAIL = 101;

    public static final int PORT = 3000;
    public static final String SERVER_IP = "127.0.0.1";
   static int existingVersion=0;

    public static void main(String[] args) {
        File downloadPath=getDownloadPath();
        if (downloadPath==null){// user typed "exit" so we break up from
            exit();
        }
        while (true) {
            MenuChoice choice = menu();
            switch (choice) {
                case UPLOAD:
                    uploadChoice();
                    break;
                case DOWNLOAD:
                    downloadChoice(downloadPath);
                    break;
            }
        }
    }

    static MenuChoice menu() {
        while (true) {
            System.out.println("please choose \n 1: upload \n 2:download \n type exit at any time to exit this program");
            String choiceString = getInputFromUser();
            switch (choiceString) {
                case "1":
                   return MenuChoice.UPLOAD;
                case "2":
                    return MenuChoice.DOWNLOAD;
                case "exit":
                    exit();
            }
        }
    }

    static void uploadChoice() {
        System.out.println("please enter path to file to upload");
        String input = getInputFromUser();
        File fileToUpload = new File(input);
        while (true) {
            if (fileToUpload.exists() && fileToUpload.isFile()) {
                if (uploadToServer(fileToUpload)) {
                    System.out.println("uploaded successfully");
                    break;
                } else
                    System.out.println("error uploading file");
            } else
                System.out.println("file does not exist or it is a directory");
        }
    }

    static void downloadChoice( File downloadPath){
        System.out.println("downloading...");
        DownloadResult downloadResult=downloadFromServer(downloadPath);
        if (downloadResult.getErrorCode()==downloadResult.SUCCESS){
            System.out.println("downloaded successfully"+downloadResult.getFileName());
        } else if (downloadResult.getErrorCode()==downloadResult.NETWORK_ERROR) {
            System.out.println("error downloading ");
        } else if (downloadResult.getErrorCode()==downloadResult.ALREADY_HAVE_LATEST_VERSION) {
            System.out.println("you already have latest version ");
        }else
            System.out.println("unknowm error");
    }

    static  boolean uploadToServer(File fileToUpload){
        Socket socket=null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        InputStream fileInputStream=null;
        try {
            socket=new Socket(SERVER_IP,PORT);
            inputStream=socket.getInputStream();
            outputStream=socket.getOutputStream();
            outputStream.write(UPLOAD);
            int response=inputStream.read();
            if (response!=OK)
                return false;
            byte []fileNameBytes=fileToUpload.getName().getBytes();
            outputStream.write(fileNameBytes.length);
            outputStream.write(fileNameBytes);
            fileInputStream=new FileInputStream(fileToUpload);
            int oneByte;
            while ((oneByte=fileInputStream.read())!=-1)
                outputStream.write(oneByte);
            fileInputStream.close();
            fileInputStream=null;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(inputStream,outputStream,socket);
        }
        return true;
    }

    static DownloadResult downloadFromServer(File downloadPath){
        Socket socket = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        OutputStream fileOutputStream = null;
        DownloadResult downloadResult = new DownloadResult();
        try {
            socket = new Socket(SERVER_IP, PORT);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
            outputStream.write(DOWNLOAD);
            int response = inputStream.read();
            if(response != OK) {
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            byte[] versionBytes = new byte[4];
            int actuallyRead;
            actuallyRead = inputStream.read(versionBytes);
            if(actuallyRead != 4){
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            int version = ByteBuffer.wrap(versionBytes).getInt();
            if(version == existingVersion){
                outputStream.write(FAIL);
                outputStream.close();
                outputStream = null;
                downloadResult.setErrorCode(DownloadResult.ALREADY_HAVE_LATEST_VERSION);
                return downloadResult;
            }else{
                outputStream.write(OK);
            }

            int fileNameLength = inputStream.read();
            if(fileNameLength == -1){
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            byte[] fileNameBytes = new byte[fileNameLength];
            actuallyRead = inputStream.read(fileNameBytes);
            if(actuallyRead != fileNameLength){
                downloadResult.setErrorCode(DownloadResult.NETWORK_ERROR);
                return downloadResult;
            }
            String fileName = new String(fileNameBytes);
            File downloadedFile = new File(downloadPath, fileName);
            downloadResult.setFileName(fileName);
            fileOutputStream = new FileOutputStream(downloadedFile);
            int oneByte;
            while((oneByte = inputStream.read()) != -1){
                fileOutputStream.write(oneByte);
            }
            fileOutputStream.close();
            fileOutputStream = null;
            downloadResult.setErrorCode(DownloadResult.SUCCESS);
            existingVersion=version;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(inputStream, outputStream, socket, fileOutputStream);
        }

        return downloadResult;
    }

    static void close(Closeable... closeables){
        for (Closeable closeable:closeables ){
            if (closeable!=null)
                try {
                    closeable.close( );
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    static File getDownloadPath(){
        while (true) {
            System.out.println("please enter a download directory");
            String pathString = getInputFromUser();
            if (pathString.equals("exit"))
                return null;
            File file = new File(pathString);
            if (file.exists() && file.isDirectory())
                return file;
            System.out.println("invalid path");
        }
    }

    private static String getInputFromUser() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    static void exit(){
        System.out.println("bye bye");
        System.exit(0);
    }
}

class DownloadResult {

    public static final int SUCCESS = 0;
    public static final int ALREADY_HAVE_LATEST_VERSION = 1;
    public static final int NETWORK_ERROR = 2;

    private int errorCode;
    private String fileName;

    public DownloadResult(){

    }
    public DownloadResult(String fileName) {
        this.fileName = fileName;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}



//My Code

/*private static Socket clientSocket;
    private static String path;

    public static void main(String[] args) {
        System.out.println("Enter directory/file path ");
        path=getInputFromUser();
        System.out.println("enter 1 :download \n 2:upload");
        String action=getInputFromUser();
        try {

            clientSocket=new Socket(SERVER_IP,PORT);
            switch (action){
                case "1":
                    DownloadThread downloadThread=new DownloadThread(clientSocket, path);
                    downloadThread.start();
                    break;
                case "2":
                    UploadThread uploadThread=new UploadThread(clientSocket,new File(path));
                    uploadThread.start();
                    break;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }*/
