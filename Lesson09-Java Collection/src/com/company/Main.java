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



        Queue q=new Queue();
        q.insert(7);
        q.insert(2);
        q.insert(4);
        q.insert(6);
        q.insert(5);
        q.insert(5);
        q.insert(5);
        q.insert(5);
        q.insert(5);
        q.insert(5);
        q.pop();
        q.insert(1);


        while (!q.isEmpty()){
            System.out.println(q.pop());
        }






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