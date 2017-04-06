package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Sapir on 06.04.2017.
 */
public class Ex2 {
    public static final String MAIN = "please choose:\n 1. add word \n 2. print the words\n type 3 at any point to exit this program";

    Set<String> words = new TreeSet<>();
   // Set<String> commonWords = new TreeSet<>();


    public Ex2(Set<String> words) {
        this.words = words;
    }

    void printWords() {
        for (String word : words) {
            System.out.println(word);
        }
    }

    public void menu() {
        System.out.println(MAIN);
        String action=getInputFromUser();
        switch (action) {
            case "1":
                addWord(getInputFromUser());
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


    private void addWord(String word) {
       // String word = getInputFromUser();
        int count = 0;
        int begin = 0;
        int end ;
        int length=word.length();
        while ((end=word.indexOf(',',begin))!=-1) {
            count++;
            addWord(word.substring(begin,end),count);
            begin=end+1;
        }
        addWord(word.substring(begin,length), count);
    }

    private void addWord(String word, int count) {
        boolean find=words.add(word);
        if(!find)
            System.out.println("the word already found");
        else
            System.out.println("success word ");
    }


    public static String getInputFromUser() {
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
