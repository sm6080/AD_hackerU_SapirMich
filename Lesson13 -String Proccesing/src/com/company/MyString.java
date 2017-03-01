package com.company;

/**
 * Created by Sapir Michaeli on 01.03.2017.
 */
public class MyString implements Comparable<MyString> {

    private final char[] chars;

    public MyString(char[] chars) {
        this.chars = chars;
    }

    //"how are you today?"     "are"->4
    public int indexOf(MyString myString) {
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            if (j < myString.chars.length) {
                if (myString.chars[j] == chars[i])
                    j++;
                else
                    j = 0;
            } else
                return i - myString.chars.length;
        }
        return -1;
    }


    public MyString[] split(char c) {
        int j = 0, k = 0;
        int[] countArr = new int[chars.length];
        //
        //char[] t = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                j++;
            } else {
                countArr[j]++;
            }
        }
        MyString[] temp = new MyString[j];
        char[] t;
        for (int i = 0; i < j; i++) {
            t = new char[countArr[i]];
            for (int l = 0; l < countArr[i]; l++) {
                t[l] = chars[k];
            }
            k++;
            temp[k] = new MyString(t);
        }

        return temp;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof MyString) {
            MyString other = (MyString) obj;
            if (this.chars.length != other.chars.length)
                return false;
            return indexOf(other) == 0;  // חרוזת אחת בתוך השניה במקום האפס וגם הם באותו אורך
        }
        return false;
    }

    @Override
    public int compareTo(MyString o) {
        int n = chars.length;
        if (o.chars.length < n)
            n = o.chars.length;// ב n יש את האורך הקצר
        for (int i = 0; i < n; i++) {
            int asciiCodeThis = (int) chars[i];
            int asciiCodeOther = (int) o.chars[i];
            if (asciiCodeThis < asciiCodeOther)
                return 1;
            else if (asciiCodeOther < asciiCodeThis)
                return -1;
        }
        if (this.chars.length == o.chars.length)
            return 0;
        if (this.chars.length < o.chars.length)
            return 1;
        return -1;
    }

    public MyString toUpper() {
        char[] charsUpper = new char[chars.length];
        for (int i = 0; i < charsUpper.length; i++) {
            charsUpper[i] = chars[i];
            int asciiCode = (int) chars[i];
            if (asciiCode >= 97 && asciiCode <= 122) {
                asciiCode -= 32;
                charsUpper[i] = (char) asciiCode;
            }
        }
        return new MyString(charsUpper);
    }

    @Override
    public int hashCode() {
        int n = chars.length - 1;
        byte a = (byte) chars[0];
        byte b = (byte) chars[n / 4];
        byte c = (byte) chars[3 * n / 4];
        byte d = (byte) chars[n];             //char d=chars[n];         // המרה לבייט

        int hash = 0;
        hash |= (a & 0xFF);
        hash <<= 8;
        hash |= (b & 0xFF);
        hash <<= 8;
        hash |= (c & 0xFF);
        hash <<= 8;
        hash |= (d & 0xFF);
        return hash;

    }
}


