package com.company;

public class Main {

    public static void main(String[] args) {
       /* Point p1 = null, p2 = null;
        //הם לא אותו דבר גם אם לא מימשנו את equals
        if (p1 == p2) {

        }
        if(p1.equals(p2)){ // אם P1 הוא null התוכנית תקרוס עם NULL POINTER EXEPTION

        }*/
        Point p1 = new Point(7, 3);
        System.out.println(p1.hashCode());


        D d=new D();
        d.y=5;
        D.x=5; // הגישה ל X היא דרך המחלקה עצמה
        D.func7();
        d.func7(); // עובר קומפילציה אך זה לא טוב כי הפונקציה היא סטטית ואמור להיות D גדול


        System.out.println(Dog.getCount());
        Dog d1=new Dog();
        Dog d2=new Dog();
        d1=new Dog();
        Dog d3=d1; // יש 2 מצביעים אך לא אובייקטים
        System.out.println(Dog.getCount());
    }
}

abstract class A{
    void func1(){
        System.out.println("in func1");
    }
    abstract void func2();
}

abstract class B extends A{
    void func3(){
        System.out.println("in func3");
    }
    abstract void func4();
}

class C extends B{
    // final int x=5;
    final int x;
    public C() {
        this.x=5;
    }

    final void func(final int param){
        //param=5;           // א"א - קבוע
        final int temp=5;
        //temp++;            // א"א - קבוע
    }
    @Override
    void func2() {
        System.out.println("func2");
    }

    @Override
    void func4() {
        System.out.println("func4");
    }
}

class D extends C{
    final Dog dog;
    // D הוא מאפיין של אובייקט מסוג y
    static int x;
    int y;

    public D(Dog d){
        this.dog=d;
    }
    public D(){
        this.dog=null;
    }
    void func6(){
        //this.d=new Dog();   // אסור כי משנים את d נותנים לו כתובת אחרת
        this.dog.name="Snoopy";
        System.out.println(y);
    }
    static void func7(){
        System.out.println("func7");
       // System.out.println(y);      //א"א כי היא מופעלת על המחלקה ולא על אובייקט מסוים
    }
}

class Dog {
    String name;
    private static int count = 0;

    public Dog() {
        count++;
    }

    //ליצור פונקציה סטטית שתמנה כמה אובייקטים מסוג המחלקה נוצרו
    public static int getCount() {
        return count;
    }

}

class MyClass{
    private int x;

    public MyClass(int x) {
        this.x = x;
    }
}
