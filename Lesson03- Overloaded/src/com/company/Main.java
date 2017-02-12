package com.company;

public class Main {

    public static void main(String[] args) {
        int x;  //primitive
        Integer y = new Integer(5); //class
        int z = 7;
        Integer m = z; //boxing
        int t = y; //unboxing
        System.out.println(z + 3);//unboxing

        t = z;
        z++;
        System.out.println(z);

        Integer a = 5;
        Integer b = a;
        a++;
        System.out.println(b);// 5


        byte c = 9;
        aMethod(c);  // aMethod(short val)
        aMethod(9);//aMethod(int val)
        Integer i = 9;
        aMethod(i); //aMethod(Object val)
        aMethod("9");

        //       ERRORS :
        //aMethod(9); // שגיאת קומפילציה כי הקומפיילר מפחד לאבד מידע , אם יש לנו רק short או byte
        //aMethod(9, 10);//  LONG ןגם ה INT שגיאת קומפילציה כי שניהם מתאימים גם  והקומפיילר לא יכול לבחור


    }

    public static void aMethod(int val){ System.out.println("int");}
    public static void aMethod(byte val){ System.out.println("byte");}
    public static void aMethod(short val){System.out.println("short");}
    public static void aMethod(Object val){System.out.println("Object");}
    public static void aMethod(String val){System.out.println("String");}
    public static void aMethod(long val1,int val2){System.out.println("long, int");}
    public static void aMethod(int val1,long val2){System.out.println(" int,long");}
}
