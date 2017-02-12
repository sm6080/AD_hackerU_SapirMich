package com.company;

import graphicshape.Circle;

/**
 * Created by Sapir Michaeli on 05.02.2017.
 */
public class Ball extends Circle {
    void bounce(){
        System.out.println(x);//מסכים כי X הוא Protected ויש פה ירושה
        //System.out.println(y);// את Y לא מסכים כי הוא דיפולט
    }
}

