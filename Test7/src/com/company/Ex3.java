package com.company;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * Created by Sapir on 06.04.2017.
 */
public class Ex3 {

    private static final String[] commonWords={"in","a","and","of","to","be","the"};

    File file;

    public void menu() {
        String path = getInputFromUser();
        file = new File(path);
        if (file.exists() || file.isFile())
            System.out.println("wrong");

        InputStream inputStream1 = null;

        try {
            inputStream1 = new FileInputStream(file);
            int actuallyRead;
            byte bytes2[] = new byte[1];
            while ((actuallyRead = inputStream1.read(bytes2)) != -1) {
                System.out.println(new String(bytes2, 0, actuallyRead - 1));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dec(){


    }


    public static String getInputFromUser(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }



}
