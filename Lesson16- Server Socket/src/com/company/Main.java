package com.company;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static final int PORT = 3000;

    //socket server
    public static void main(String[] args) {

  /*  זה עבור String  השיחה בין השרת ללקוח
        try {
                        // בפורט מסוים רק אחד יכול להאזין ולכן אם נשתמש בפורט שמיהו מאזין בו נזרוק exception
          // יש לי  ServerSocket אחד ויחיד
            ServerSocket serverSocket=new ServerSocket(PORT);//alt+crtl+c   ->contant
            while (true) {
                System.out.println("waiting for incoming communication");
                Socket socket = serverSocket.accept();  // אותו סוקט שראינו אצל ה client
                System.out.println("client connected");
                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
*/


       // ServerSocket serverSocket = null;//alt+crtl+c   ->contant
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                System.out.println("waiting for incoming communication");
                Socket socket = serverSocket.accept();  // אותו סוקט שראינו אצל ה client
                System.out.println("client connected");
                ClientIntegerThread clientIntegerThread = new ClientIntegerThread(socket);
                clientIntegerThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /*public static int minus(int x,int y){
        return x-y;
    }
    public static int prod(int x,int y){
        return x*y;
    }‏
*/

}

// פונקציה מיוחדת שרק אנחנו יודעים לחשב, רק השרת שלנו יודע לחשב 2 int, על כן מחשב שרוצה לחבר 2 int צריך לפנות לשרת שלי, להעביר את 2 ה int לקבל תשובה
//תרגיל 2: אני רוצה לאפשר גם כפל , חילוק, חיסור, חוץ מלהעביר 2 מספרים, צריך גם להעביר לשרת איזו פעולה רוצים שיעשה עליהם, הוא מחשב ועונה את התוצאה
