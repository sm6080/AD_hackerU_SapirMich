package com.company;

/**
 * Created by Sapir Michaeli on 12.02.2017.
 *
 */
public class Circle {
    private Point center;
    private  int radius;
    private int z;



    // המחלקה היא לא סטטית,
    // כל אובייקט  Circle שייוצר , תיווצר המחלקה  Point   חדשה לאותו אובייקט
    //אין לי אובייקט של   Circle אני לא יכולה ליצור Point
    class Point{
        private int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
        // פונקציה של Point
        public int getRadius(){
            return radius;
        }

        public int getZ(){
            return Circle.this.z;   //Circle
        }

        @Override
        public String toString() {
            return "("+x+","+y+")";
        }
    }

    public Circle( int x, int y , int radius) {
        this.radius = radius;
        this.center=this.new Point(x,y);
    }

    //default constractor
    public Circle(){
        this(0,0,0);
    }

    @Override
    public String toString() {
        return "radius"+radius+"center:"+center;
    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }
}
