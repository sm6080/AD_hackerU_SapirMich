package com.company;

import com.sun.org.apache.regexp.internal.RE;

import java.util.Random;

/**
 * Created by Sapir Michaeli on 12.02.2017.
 */
public class Rectangle {

    int width,height;

    public Rectangle makeMeSqure(){
        class Squre extends Rectangle{
            public Squre(int side){
                this.width=side;
            }
            public int getSide(){
                return this.width;
            }

            // מופעל בפועל מה שיש בפועל! ואת זה יראו מחוץ לפונקציה makeMeSqure
            @Override
            public String toString() {
                return "I am a squre with side"+width;
            }
        }
        Squre s=new Squre(width);
        s.getSide();
        return  new Squre(width);
    }


    @Override
    public String toString() {
        return "I am rectangle width:"+width+"height"+height;
    }



}
