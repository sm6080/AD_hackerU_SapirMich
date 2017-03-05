package com.company;

import java.io.*;
import java.nio.ByteBuffer;

public class Main {

    public static void main(String[] args) {

       /* // בדיקה שההמרה תמיד עובדת
        for (int i = 0; i <255 ; i++) {
            byte b=(byte)i;
            int x=b&0xFF;
            if (i!=x)
                System.out.println("didnt work for"+i);
        }*/

        String [] stringsArray= {"hello, are you?", "Today is Sunday", "Today is a nice day"};

       /* String s1 = "היי שלום לכולם ";
        String s2 = "היום יום ראשון";*/

        File file = new File("C:/Users/hackeru.HACKERU3/Documents/sapir_m/files/sap.txt");
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            for (int i = 0; i <stringsArray.length ; i++) {  //for array
               //  נלך לפי השיטה של אורך שומרים את האורך של כל מחרוזת
                byte [] stringBytes =stringsArray[i].getBytes();
                byte b=(byte)stringBytes.length;
                outputStream.write(b);
                outputStream.write(stringBytes);



                /*outputStream.write(stringsArray[i].getBytes());
                if (i<stringsArray.length-1)
                    outputStream.write("&".getBytes());*/
            }
            /*outputStream.write(s1.getBytes());
            outputStream.write("&".getBytes());
            outputStream.write(s2.getBytes());*/
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }


        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            /* שיטת החוצץ
            StringBuilder stringBuilder = new StringBuilder();
            byte[] buffer = new byte[8];
            int actuallyRead;
            // השיטה הזו לא טובה כי מה אעשה אם המחרוזת עצמה תכיל את התו החוצץ
            // אפשר לשים 2 תווים חוצצים אך לא הורדנו לגמרי את ההסתברות וגם ניפחנו מאוד את הקובץ
            // ה שמסומן בהערת כוכב למעלה זה לפי השיטה הראשונה
            while ((actuallyRead = inputStream.read(buffer)) != -1) {
                stringBuilder.append(new String(buffer, 0, actuallyRead)); // כל פעם נותנים לו string חדש והוא גדל
            }
            inputStream.close();
            inputStream=null;
            String [] strings=stringBuilder.toString().split("&"); // המרה ל STRING רגיל
            for (String s:strings)
                System.out.println(s);


            //לפי הגישה הזו אם הקובץ גדול אז קוראים כל פעם ועד שלא הגענו לחוצץ מעבירים וכך אם נצטרך לחפש נוכל לעשות ולא נצטרך לקרוא את כל הקובץ בשלמותו ולהחזיק אותו בבת אחת בזיכרון
            char[] buffer=new char[500];
            int pos=0;
            int nextByte;
            while ((nextByte=inputStream.read())!=-1){//קראנו לגירסה שמחזירה בייט בודד
                char c=(char)nextByte;
                if (c!='&'){
                    buffer[pos]=c;
                    pos++;
                }else {
                    String s=new String(buffer,0,pos);
                    System.out.println(s);
                    pos=0;

                }
            }
            if(pos>0){
                String s =new String (buffer, 0 ,pos);
               System.out.println(s);
           }

*/

            // לפי השיטה של האורך
            // אחת האפשרויות
            int actuallyRead;
            int nextByte;
            while ((nextByte=inputStream.read())!=-1){
                byte [] stringBytes=new byte[nextByte];
                actuallyRead=inputStream.read(stringBytes);// קוראים לתוכו
                if (actuallyRead!=nextByte){
                    //TODO: something really bad happend
                }else {
                    String s= new String(stringBytes,0,actuallyRead);
                    System.out.println(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null)
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }






        Dog d=new Dog("snoopy", 12,20);
        byte[] bytes=d.getBytes();
        Dog d2=new Dog(bytes);
        System.out.println(d.getName());
        System.out.println(d.getAge());
        System.out.println(d.getColor());

    }
}


class Dog{
    private String name;
    private int age;
    private int color;

    public Dog(String name, int age, int color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public Dog(byte[] bytes){
        int length =bytes[0] &0xFF;
        this.name=new String(bytes,1,length);
        this.age=ByteBuffer.wrap(bytes).getInt(1+length);
        this.color=ByteBuffer.wrap(bytes).getInt(1+length+4);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public byte[] getBytes(){
        byte[] nameBytes =name.getBytes();
        byte[] bytes=new byte[1+nameBytes.length+4+4]; // האורך של השדות והמחרוזת
        bytes[0]=(byte)nameBytes.length;
        for (int i = 0; i <nameBytes.length ; i++) {//העתקנו את המחרוזת פנימה
            bytes[1+i]=nameBytes[i];
        }
        ByteBuffer.wrap(bytes).putInt(1+nameBytes.length,age);
        ByteBuffer.wrap(bytes).putInt(1+nameBytes.length+4,color);
        return bytes;
    }
}
