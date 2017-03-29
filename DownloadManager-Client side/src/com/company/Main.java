package com.company;

import java.io.*;
import java.net.Socket;

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
        String downloadedFileName;
        if ((downloadedFileName=downloadFromServer(downloadPath))!=null){
            System.out.println("downloaded successfully"+downloadedFileName);
        }else
            System.out.println("error downloading file");
    }

    static  boolean uploadToServer(File fileToUpload){
        Socket socket=null;
        InputStream inputStream=null;
        OutputStream outputStream=null;
        try {
            socket=new Socket(SERVER_IP,PORT);
            inputStream=socket.getInputStream();
            outputStream=socket.getOutputStream();
            outputStream.write(UPLOAD);
            int response=inputStream.read();
            if (response!=OK)
                return false;
            byte []fileNameToBytes=fileToUpload.getName().getBytes();
            outputStream.write(fileNameToBytes.length);
            InputStream fileInputStream=new FileInputStream(fileToUpload);
            int oneByte;
            while ((oneByte=fileInputStream.read())!=-1)
                outputStream.write(oneByte);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            close(inputStream,outputStream,socket);
        }
        return true;
    }

    static String downloadFromServer(File downloadPath ){
        return "name";
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
