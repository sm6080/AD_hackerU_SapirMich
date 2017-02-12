package com.company;

import graphicshape.*;

public class Main {

    public static void main(String[] args) {

        Circle circle1; //מצביע היכול להכיל כתובת בערימה
        circle1 = new Circle(); // יצרנו אובייקט מסוג Circle
        System.out.println(circle1.area());

        circle1.fillColor(4, 5, 6);

        HappyBirthday.wish();// Happy Birthday to you!
        HappyBirthday.wish("Sapir"); //Happy Birthday Sapir!


        Number[] nums = {5, 7, 4.3f, new Double(3), new RationalNumber(5, 2)};
        System.out.println(sum(nums));
        RationalNumber r = new RationalNumber(1, 3);
        r.add(new RationalNumber(1, 2));
        System.out.println(r.numerator + " " + r.denominator);

        RationalNumber num1 = new RationalNumber(1, 2);
        RationalNumber num2 = new RationalNumber(1, 4);
        num1.add(num2);

        System.out.println(RationalNumber.gcd(15, 5));

        Square square = new Square(4);// rectangle

        System.out.println(square.area()); //16
        System.out.println(square.perimeter()); //16

        Shape s = new Square(6); // מותר שפוינטר לא יהיה בדיוק מאותו סוג של האובייקט -האובייקט שנוצג בזיכרון הוא מסוג Square והמצביע יהיה SHAPE
        //רשימת המתודות אותן נפעיל על אובייקט נקבעת ע"פ סוג המצביע, ולכן ל s יש את המתודות של Shape
        s.area();
        System.out.println(s.area());  //  הוא הפעיל את ה area של Square (כי מפעיל את מה שיש )
        // יש 2 מקומות ששכיח שנראה את זה, שהאוביקטים לא מאותו סוג של המצביע: 1. אוספים - מערך    2. כפרמטר של פונקציה


        Segment segment = new Segment(new Point(4, 5), new Point(7, 4));
        segment.A();


    }



    //מקבלת מערך של Numbers ומחזירה את הסכום שלהם
    public static double sum(Number [] nums){
        double sum=0.0;
       /* for (int i = 0; i <nums.length ; i++) {
            Number num =nums[i];
            sum+=num.doubleValue();
        }*/
       //foreach
       for(Number num:nums){
           sum+=num.doubleValue();
       }
        return sum;
    }
}
