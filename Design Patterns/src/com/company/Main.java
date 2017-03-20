package com.company;

import java.io.File;

public class Main {

    public static void main(String[] args) {
       // Dog dog=new Dog();
        Dog dog=Dog.createDog();


        ShapeFactory shapeFactory=new ShapeFactory();

        Shape s=shapeFactory.getShape("circle");
        s.draw();


        Shape circle=new Circle();
        Shape redCircle=new RedShapeDecorator(circle);

        Shape rectangle=new Rectangle();
        Shape redRectangle=new RedShapeDecorator(rectangle);

        circle.draw();
        circle.draw();
    }
}

// ללא המילה new , ניצור פונקציה שהיא סטטית
//קטגורית Creational Patterns - יצירת אובייקטים שלא דרך new

//singelton
class Dog {

    private Dog(){// א"א ליצור מבחוץ כי הבנאי הוא private
        System.out.println("in dog constractor");
    }

    /*      for singelton
    private static Dog d=null;
    */
    public static Dog createDog() {
        return new Dog();
    }
}




//קטגורית Behavioral Patterns - תקשורת בין אובייקטים

// design pattern - Observer/Listener
class MotionSensor{
    private MotionListener listener;

    public void setListener(MotionListener listener) {
        this.listener = listener;
    }

    public void detectMotion(){
        if (listener!=null)
            listener.motionDetected(123);
    }

    interface  MotionListener{
        void  motionDetected(int sensorId);
    }
}






  //  קטגורית Structural Patterns - מחלקות מורכבות בתוך מחלקות




  //  קטגורית J2EE - קשור לממשק משתמש יותר מאשר לקוד
// תהיה קשורה להמשך יותר




//תבנית ה- Factory
//יוצרים אובייקטים מבלי לחשוף החוצה את לוגיקת יצירת האובייקט ומתייחסים לאובייקט שנוצר באמצעות interface מוכר.
interface Shape{
    void draw();
}

class Circle implements Shape{
    @Override
    public void draw() {

    }
}
class Square implements Shape{
    @Override
    public void draw() {

    }
}
class Rectangle implements Shape{
    @Override
    public void draw() {
        System.out.println();
    }
}

class ShapeFactory extends AbstractFactory{
    @Override
    Color getColor(String color) {
        return null;
    }

    public Shape getShape(String shapeType){
        if (shapeType==null)
            return null;
        switch (shapeType) {
            case "circle":
                return new Circle();
            case "rectangle:":
                return new Rectangle();
            case "square:":
                return new Square();
            default:
                return null;

        }
    }
}






//(Structural Patterns)     Decorator -  תבנית ה
// מאפשר למשתמש להוסיף פונקציונליות לאובייקט קיים מבלי לשנות את המבנה שלו. תבנית זו היא תחת הקטגוריה השניה והתבנית משתמש ב״מעטפת״ למחלקה קיימת
// נישאר עם Shape
abstract class ShapeDecorator implements Shape{
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    @Override
    public void draw() {
        decoratedShape.draw();
    }
}


class RedShapeDecorator extends ShapeDecorator{
    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("red");
    }
}




//תבנית ה- Abstract Factory
//זהו למעשה super factory כלומר factory שיוצר factory אחרים. נופל תחת הקטגוריה הראשונה. בתבנית זו interface אחראי ליצור factory ליצירת אובייקט מסוג מסויים מבלי לציין במפורש את המחלקה

interface Color{ // ממשק צבע וכמה מחלקות שממשות אותו
    void fill();
}

class Red implements Color{

    @Override
    public void fill() {
        System.out.println("fill in red");
    }
}
class Green implements Color{

    @Override
    public void fill() {
        System.out.println("fill in green");
    }
}
class Blue implements Color{

    @Override
    public void fill() {
        System.out.println("fill in blue");
    }
}

abstract  class AbstractFactory{
    abstract Color getColor(String color);
    abstract Shape getShape(String shape);
}

class ColorFactory extends AbstractFactory {

    @Override
    Color getColor(String color) {
        if (color == null)
            return null;
        switch (color) {
            case "red":
                return new Red();
            case "green:":
                return new Green();
            case "blue:":
                return new Blue();
            default:
                return null;

        }
    }
    @Override
    Shape getShape(String shape) {
        return null;
    }
}

class FactoryProducer{
   public static AbstractFactory grtFactory(String type){
       switch (type) {
           case "shape":
               return new ShapeFactory();
           case "color":
               return new ColorFactory();
           default:
               return null;
       }
   }
}



//תבנית ה- Strategy
//התנהגות של מתודה ניתנת לשינוי בזמן ריצה. התבנית נופלת תחת הקטגוריה השלישית.
// תבנית זו מייצרת אובייקטים שמייצגים אסטרטגיות שונות ואובייקט context שההתנהגות שלו משתנה לפי האסטרטגיה. אובייקט האסטרטגיה משנה את האלגוריתם של אובייקט ה- context


//example 1
interface EncryptionStrategy{
    int encrypt(File input,File output);
}

class CaesarEncryption implements EncryptionStrategy{
    @Override
    public int encrypt(File input, File output) {
        return 123;
    }
}class RSAEncryption implements EncryptionStrategy{
    @Override
    public int encrypt(File input, File output) {
        return 456;
    }
}class TripleDESEncryption implements EncryptionStrategy{
    @Override
    public int encrypt(File input, File output) {
        return 345;
    }
}

 class Context {
     private EncryptionStrategy encryptionStrategy;

     public Context(EncryptionStrategy encryptionStrategy) {
         this.encryptionStrategy = encryptionStrategy;
     }

     public int encrypt(File input, File output) {
         return encryptionStrategy.encrypt(input, output);
     }
 }



//example 2
 interface CalculateStrategy{
    int calculate(int x, int y);
 }

 class calculateAddition implements CalculateStrategy{
     @Override
     public int calculate(int x, int y) {
         return x+y;
     }
 }
 class calculateSubtraction implements CalculateStrategy{
     @Override
     public int calculate(int x, int y) {
         return x-y;
     }
 }
 class calculateMultiplation implements CalculateStrategy{
     @Override
     public int calculate(int x, int y) {
         return x*y;
     }
 }
 class calculateDivition implements CalculateStrategy{
     @Override
     public int calculate(int x, int y) {
         if (y!=0)
             return x/y;
         throw  new ArithmeticException("y is zero");
     }
 }

 class CalculateContext{
    private CalculateStrategy strategy;

     public CalculateContext(CalculateStrategy strategy) {
         this.strategy = strategy;
     }

     public int doCalculation(int x, int y){
         return strategy.calculate(x,y);
     }
 }

