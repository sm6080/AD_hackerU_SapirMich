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

    public void hack (byte[] fromFile, Set<String>commonWords,MatchFound matchFound){
        for (int i = 0; i <256 ; i++) {
            for (int j = 0; j <fromFile.length ; j++) {
                fromFile[j]++;
            }
            int count=0;
            String text=new String(fromFile);
            for (String commonWord:  commonWords) {
                if (containWord(commonWord,text)) {
                    count++;
                    if (count==3)
                        matchFound.found(3,text);
                }
            }
        }
    }

    public static boolean containWord(String word, String text){
        int index=text.indexOf(word);
        if (index==-1)
            return false;
        if (index!=0 ) {
            char charBefore = text.charAt(index - 1);
            if (charBefore!='.' &&charBefore!=' ' && charBefore!=',')
                return false;
        }
        return true;
    }

    public interface MatchFound{
        void found(int key, String text);// המפוצח
    }

}
