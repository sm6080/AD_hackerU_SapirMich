package com.company;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class Main {

    public static final int PORT = 3000;

    //client socket
    public static void main(String[] args) {
        menu();
    }
    public static void menu() {
        System.out.println("Hi, what do you want to do?");
        String op = readInput();
        if (!((op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/")))) {
            System.out.println("It is not an optional operation");
            menu();
            return;
        }
        Integer num1 = 0, num2 = 0;
        num1=getNumbers();
        num2=getNumbers();
        if (op.equals("/") && num2==0){
            System.out.println("ERROR! Division by zero is forbidden");
            num2=getNumbers();
        }
        operate(num1, num2,op.charAt(0));
        System.out.println("Do you want to go on ? please enter 1 ");
        String goOn = readInput();
        if (goOn.equals( "1"))
            menu();
        else
            System.out.println("bye - bye");

    }



    // פונקצית עזר לקליטת 2 המספרים

    private static Integer getNumbers(){
        System.out.println("enter a number");
        String num1;
        num1=readInput();
        Integer n1;
        try {
            n1 = Integer.valueOf(num1);
        }catch (Exception ex){
            System.out.println("the input is not valid");
            n1=getNumbers();
        }
        return n1;
    }







    public static void operate(int num1, int num2, char op) {
        try {
            byte[] arrBytes =new byte[9]; // כדי לכתוב לו את כל המשתנים ביחד

            Socket socket = new Socket("127.0.0.1", PORT);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            //outputStream.write(op);
            arrBytes[0]=(byte)op;

            // convert to byte
            //byte[] bytes = new byte[4];
            ByteBuffer.wrap(arrBytes).putInt(num1);
            //outputStream.write(arrBytes);
            ByteBuffer.wrap(arrBytes).putInt(num2);
            outputStream.write(arrBytes);

            byte[] buffer = new byte[4];
            int actuallyRead = inputStream.read(buffer); //x
            actuallyRead = inputStream.read(buffer); //y
            actuallyRead = inputStream.read(buffer); //j
            int j = ByteBuffer.wrap(buffer).getInt();
            System.out.println("The result: " + j);
            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // לקלט
    public static String readInput(){
        String input = null;
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(System.in));
        try {
            input = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

}
