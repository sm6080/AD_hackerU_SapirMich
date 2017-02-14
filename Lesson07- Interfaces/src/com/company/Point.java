package com.company;

import java.security.InvalidParameterException;

/**
 * Created by Sapir Michaeli on 13.02.2017.
 */
public class Point implements Comparable{
    private int x,y;

    public Point() {
    }

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

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof Point))
            throw new InvalidParameterException("must send a valid point");
        if (o == this)
            return 0;
        Point other = (Point) o; // נמיר אותו כדי שנוכל לגשת ל X, Y שלו
        double d1 = this.distanceFromOrigin();
        double d2 = other.distanceFromOrigin();
        if (d1 > d2)
            return 1;
        else if (d1 < d2)
            return -1;
        //בדיקות איפה הנקודה בראשית הצירים
        if (this.x >= 0) {
            if (other.x >= 0) {
                if (this.y * other.y >= 0) { // עד לפה בדקנו אם 2 ה X חיוביים (ז"א ברביע ה 1 וה 4 ) ולכן נבדוק מי ה Y הגדול
                    if (this.y >= other.y)
                        return 1;
                    else
                        return -1;
                } else {
                    if (this.y < 0)//זה שברביעי הרביעי הוא הגדול שה Y שלו שלילי
                        return 1;
                    else
                        return -1;
                }
            } else {// ה X שלילי (ברביע 2 או 3)
                if (this.y >= 0) // רביע 1
                    return -1;
                else
                    return 1;
            }
        }else {// same same
             }
        return 0;
    }

    // מחזירה את המרחק מראשית הצירים
    public double distanceFromOrigin(){
        return  Math.sqrt(x*x+y*y); // חיסור עם(0,0)

    }

    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this) // אותו אוביקט בזיכרון באותה כתובת
            return true;
        if (obj instanceof Point) {
            Point other = (Point) obj;//downcast
            return this.x == other.x && this.y == other.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (7*x)^(11*y)^(53*y);
    }


}
