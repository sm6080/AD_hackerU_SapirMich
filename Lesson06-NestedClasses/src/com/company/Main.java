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
        // אילו יורשת פה מחלקה מ  Dog , ואין לה שם , לא ניתן ליצור אובייקט מהמחלקה הזו, היא חד פעמית
        Dog d=new Dog(){
            @Override
            void bark() {System.out.println("boof ");
            }
        };
        d.bark(); //  boof

        //זו לא אותה מחלקה , האובייקט פה הוא לא מאותה המחלקה הנ"ל
        Dog d3 =new Dog(){
            @Override
            void bark() {
                System.out.println("boof ");
            }
        };

        //שימוש בקבוע
        Student shira=new Student();
        shira.cityId=Student.JERUSALEM;
        shira.maritialStatus=Student.MARRIED;
        shira.gender=Gender.FEMALE;

        //Car bmw=new Car(); // א"א -- כי המבנאי הוא  privte

        int x5=13;
        //int 5x=13;
        PrinterType prinerType=PrinterType.LASER;
        System.out.println(prinerType);
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

enum Gender{
    MALE, FEMALE, OTHER
}

class Student{
    //דרך א
    public static final int JERUSALEM=23;
    public static final int RAANANA=73;


    public static final int MARRIED=73;
    public static final int SINGLE=47;
    public static final int DIVORCED=22;


    String firsName;
    String lastName;
    int cityId;
    int maritialStatus;
    Gender gender;
}

enum PrinterType {
    DOTMATRIX(4,"dotmatrix"), INKJET(3,"inkjet"), LASER(10,"laser"), THREE_D(1,"3D"), THERAL(20,"theral");

    private int pageCapacity;
    private String name;

    private PrinterType(int pageCapacity, String name) {
        this.pageCapacity = pageCapacity;
        this.name = name;
        System.out.println("in Printer Type");
    }

    public int getPageCapacity() {
        return pageCapacity;
    }

    @Override
    public String toString() {
        return name;
    }
}

class Car{
    private Car() {
    }
}