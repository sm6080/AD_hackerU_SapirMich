package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Sapir on 06.04.2017.
 */
public class Ex1 {

    public static final String MAIN = "please choose:\n 1. add word \n 2. print the words\n type 3 at any point to exit this program";

   private Set<String> words =new TreeSet<>();

    public Ex1(Set<String> words) {
        this.words = words;
    }

    public void menu() {
        System.out.println(MAIN);
        String action=getInputFromUser();
        switch (action) {
            case "1":
                addWord();
                menu();
                break;
            case "2":
                printWords();
                break;
            case "3":
                break;
            default:
                System.out.println("It is not a correct choice");
        }
    }
    private void addWord(){
        String word=getInputFromUser();
        if (words.contains(word))
            System.out.println("The word is already exist");
        words.add(word);
   }

   private void printWords(){
       for (String word:words)  {
           System.out.println(word);
       }
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
