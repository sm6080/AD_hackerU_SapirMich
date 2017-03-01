package com.company;

import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {

        String s="how are you today?";
        String s2="are";
        MyString myString=new MyString(s.toCharArray());
        MyString myString2=new MyString(s2.toCharArray());
        System.out.println(myString.indexOf(myString2));


        int y=127;
        byte n=(byte)y;
        System.out.println(n);//127
        int x=128;
        byte b=(byte)x;
        System.out.println(b);//-128
        int z=b;
        System.out.println(z);//-128
        System.out.println(z & 0xFF);//128

        byte[] bytes=new byte[4];

        //פירוק לבייטים
        for (int i = 0; i <4 ; i++) {
            bytes[4-i-1]= (byte)(x>>(8*i));
        }

        // מערך של בייטים ורוצים ממנו לבנות int
        int a=0;


        //ByteBuffer יודע להמיר כל דבר לבייטים ובחזרה

        int c=56789;
        byte[] abytes=new byte[4];
        ByteBuffer.wrap(abytes).putInt(c);  // פירוק לבייטים

        int d=ByteBuffer.wrap(abytes).getInt();





    }
}
