package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        //Important abstract Classes and Interfaces regaeding Collections
        //Iterable
        //Collection
        //List
        //Set, SortedSet, NavigableSet
        //Queue , Deque
        //Map<>
        //SortedMap, NavigableMap

        //Important concrete classes regarding collection
        //ArrayList
        //LinkedList
        //HashSet
        //TreeSet
        //HashMap
        //TreeMap

        Set<Point> points=new HashSet<>();// וא בלי כפילויות ולכן בודק כל פעם את ה hashCode ואז רואה אם שווים
        Point p1=new Point(4,5);
        Point p2=new Point(3,4);
        points.add(p1);
        points.add(p2);






    }
}


class User{
    String userName;
    String password;

    @Override
    public int hashCode() {
        return userName.hashCode();
    }
}