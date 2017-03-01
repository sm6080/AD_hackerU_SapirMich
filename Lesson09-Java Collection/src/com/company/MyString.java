package com.company;

/**
 * Created by Sapir Michaeli on 27.02.2017.
 */
public class MyString {

    private final char[] chars;   // הפוינטר לא יכול לשנות את ערכו אבל התווים כן

    public MyString(char[] chars){
        this.chars=chars;
    }

    //"how are you today?"     "are"->4
    public int indexOf(MyString myString){
        int j=0;
        for (int i = 0; i <chars.length ; i++) {
            if (j<myString.chars.length ) {
                if (myString.chars[j] == chars[i])
                    j++;
                else
                    j = 0;
            }
            else
                return i-myString.chars.length;
        }
        return -1;
    }


    public MyString[] split(char c) {
        int j = 0, k = 0;
        int [] countArr=new int[chars.length];
        //
        //char[] t = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i]==c){
                j++;
            }
            else {
                countArr[j]++;
            }
                  }
        MyString[] temp = new MyString[j];
        char[] t;
        for (int i = 0; i <j ; i++) {
            t=new char[countArr[i]];
            for (int l = 0; l <countArr[i] ; l++) {
                t[l]=chars[k];
            }
            k++;
            temp[k] = new MyString(t);
        }

        return temp;
    }
}


