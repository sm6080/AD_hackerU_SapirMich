package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        //region GENERIC CLASS
        List list = new LinkedList<Object>();
        list.add("first");
        list.add("second");
        list.add(123);
        //list.add(new Pair (5, "T")); //runtime error
        List<String> strings = list;
        List<Integer> ints = list;
        /*no problem although list is Object
        however if we had written: List list<Object>=new LinkedList<Object>();
        there would be a compilation error*/
        System.out.println(ints.get(1));
        //does not crash since method applies toString without actually noticing type
        //however if casting was attempted it would crash
        System.out.println(strings.get(2));
        LinkedList<Animal> animals = new LinkedList<>();
        animals.add(new Shark());
        animals.add(new Shark());
        //LinkedList<Shark> sharks=animals; //compilation error. Solution:
        LinkedList<Shark> sharks = new LinkedList<>();
        for (int i = 0; i < animals.getSize(); i++) {
            sharks.add((Shark) animals.get(i));
            //problem is we have to make a new list. however new objects were no created, only one list
        }
        List <?> list2=animals; //to solve raw type problem
        //does not permit adding or indexOf only passive activities like printing
        System.out.println(list2.get(1));

        //Pair <?,?> pair1=new Pair <Integer, Integer>();
        //Integer integer=(Integer)pair1.getObject1();
        //endregion

        //region BUFFEREDREADER
        //to get input from user
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("enter your age");
        try {
            String ageAsString=bufferedReader.readLine();
            int age=Integer.valueOf(ageAsString);
            System.out.println("Your age is "+age);
        }
        catch (IOException ex){
            System.out.println("error reading");
        }
        catch (NumberFormatException ex){
            System.out.println("Must enter int");
        }

        //endregion
        //region STRING FUNCTIONS
        String s="hello";
        byte[] sBytes=s.getBytes();
        char c=(char)sBytes[0];
        System.out.println(c);
        System.out.println(sBytes[0]);
        System.out.println(couldIBeInt("123"));
        int check=valueOf("1234");
        System.out.println("CHECK "+check);
        //endregion
    }
    //region GENERIC METHODS

    public static <T> void fill(List<T> list, int qty, T obj) {
        for (int i = 0; i < qty; i++) {
            list.add(obj);
        }
    }
    //endregion
    //region STRING METHODS CONTINUED
    public static boolean couldIBeInt (String s){
        if(s==null) return false;
        byte[] checker=s.getBytes();
        for (int i = 0; i < checker.length; i++) {
            if(checker[i]<48 || checker[i]>57)
                return false;
        }
        return true;
    }
    public static int valueOf(String string){
        if(couldIBeInt(string)){
            int result=0;
            byte[] ints= string.getBytes();
            for (int i = 0; i < ints.length; i++)
                result=result*10+(ints[i]-48);
            return result;
        }
        return -1;
    }
    //endregion
}

class Animal{}
class Shark extends Animal{}
class Anteater extends Animal {}