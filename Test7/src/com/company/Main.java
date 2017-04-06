package com.company;

import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Set<String> words =new TreeSet<>();

        /*Ex1 ex1=new Ex1(words);
        ex1.menu();*/
        Ex2 ex2=new Ex2(words);
        ex2.menu();

    }

    public void hack (byte[] fromFile, Set<String>commonWords){

    }
}
