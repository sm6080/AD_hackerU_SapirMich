package com.company;

//import myPackage.myClass;
//import myPackage.myOtherClass;
import myPackage.*; // במקום שתי השורות הנ"ל
import static java.lang.Math.PI;
import static java.lang.Math.*;

public class Main {

    public static void main(String[] args) {
        Dog d = null;
        hundleDog(d);

        Segment segment = new Segment(new Point(4, 5), null);
        segment.setP1(null);
        System.out.println(segment); //null-null
        Point p = new Point(6, 7);
        segment.setP1(p);
        segment.setP2(new Point(2, 3));
        System.out.println(segment);//(6,7)-(2,3)
        System.out.println(d); //null

        //ALIAS
        //p.setX(9);// ALIAS

        Dog dog = new Dog();
        System.out.println(dog); //I am a dog

        //אפשרי כל אלו
        Animal a = new Animal();
        Animal b = new Dog();
        Animal c = new Poodle();
        Dog dog1 = new Poodle();

        //    אם יודעים ש  dog1 הוא פודל (בראד פיט משחק את אכילס אבל בלב הוא יודע שהוא בראד  :-) )
        Poodle poodle = (Poodle) dog1;//downcast- explicit cast

        poodle.roll();// הכתובת שב poodle ו ב Ddog1 היא אותה כתובת ,שניהם מצביעים על אותו אובייקט
        Animal an=dog1; //upcast     implicit cast

        // טעות בשורה הבאה כי פודל לא יכול להיות מומר לחתול שגיאת זמן ריצה
        //Cat cat=(Cat)an;  // downcast for Animal
        //cat.howl();

        if(an instanceof Poodle) {// מחזיר אמת רק אם כרגע בזמן ריצה יש במקום הזה פודל ולכן ה downcast  לא יכול להיכשל
            Poodle p2 = (Poodle) an;
        }


        // ידפיס את הלא שווה כי ה == משווה בין הכתובות והם בכתובות שונות
        Point p1=new Point(4,5);
        Point p2=new Point(4,5);
        if(p1==p2){
            System.out.println("p1=p2");
        }
        System.out.println("p1!=p2");     //p1!=p2

        if(p1.equals(p2)){ //p1==p2
            System.out.println("p1=p2");
        }
        System.out.println("p1!=p2");    //p1!=p2


        myClass myClass;
        myOtherClass myOtherClass;

        double perimeter =2.0*PI*10.0; // לא צריך את הקודיומת MATH כי עשינו IMPORT למעלה
    }





    public static void hundleDog(Dog d) {
        if (d != null)
            d.bark();
    }



}
class Animal{
    public void gotoSleep(){
        System.out.println("going to sleep");
    }
}


class Dog extends Animal{
    void bark(){
        System.out.println("waf");
    }

    @Override
    public String toString() {
        return "I am a dog";
    }
}

class Cat extends Animal{
    void howl(){
        System.out.println("miyauooooooooooooooo");
    }
}

class Poodle extends Dog{
    @Override
    void bark() {
        System.out.println("bfff......bfffff");
    }
    public void roll(){
        System.out.println("rolling");
    }
}


