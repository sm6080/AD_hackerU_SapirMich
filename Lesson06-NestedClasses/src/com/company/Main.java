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

    }
}
