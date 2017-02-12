package com.company;

public class Main {

    public static void main(String[] args) {
        // Static nested class    - מחלקה מקוננת סטטית
        // Inner class            - מחלקה מקוננת פנימית
        // Local Inner class      - מחלקה מקוננת מקומית
        // Anonymous inner class   - מחלקה פנימית אנונימית


        //     יצרנו אובייקט מסוג Color ואין שום אובייקט מסוג Shape  - זה:   Static nested class
        Shape.Color myColor = new Shape.Color(34, 45, 100);

        Circle.Point point=new Circle().new Point(4,5);
        // השורה הנ"ל מקבילה ל 2 השורות הבאות
        Circle c=new Circle();
        Circle.Point point1=c.new Point(3,4);


        // זה ששמתי סוגריים מסולסלים יצרתי אובייקט מסוג מחלקה שיורשת מ  Dog  ואין לה שם
        Dog d=new Dog(){
            @Override
            void bark() {
                System.out.println("boof ");
            }
        };
        d.bark(); //  boof
    }

    static Dog trainDog(Dog d ){
        class TrainDog extends Dog{
            @Override
            void bark() {
                System.out.println("abcdefg....yz");
            }
        }
        //יצרתי כלב אחר עם אותו שם
        TrainDog trainDog=new TrainDog();
        trainDog.name=d.name;
        return trainDog;
    }
}

class Dog{
    String name;
    void bark(){
        System.out.println("waf"+name);
    }
}