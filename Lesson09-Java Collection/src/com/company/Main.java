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


        // במקום  hasCode and equals
        Map<String,User> users= new HashMap<>();
        User user1=new User("Sapir","12345");
        User user2=new User("Maayan","123456");
        User user3=new User("Avi","123456");

        //put -
        users.put(user1.getUserName(),user1);
        users.put(user2.getUserName(),user2);
        users.put(user3.getUserName(),user3);

        System.out.println(users.size());

       /* //get - we cant search by value, we must send key
        User user3 =users.get(user1.getUserName());// בגלל הגנריות לא נדרשנו לעשות המרה - downcasting
        */
       // עוד מתודות : clear, , containKey

        Iterator<User> allTheUsers=users.values().iterator();
        while (allTheUsers.hasNext()){
            User u=allTheUsers.next();
        }


        System.out.println();

        String s="mmmmmSapirmm";
        String s2="Sapir";
        MyString myString=new MyString(s.toCharArray());
        MyString myString2=new MyString(s2.toCharArray());
        System.out.println(myString.indexOf(myString2));





    }

    //תרגיל מסילת רכבת
    // naive
    public static int findStartingStation(Station[] stations){
        boolean validPath;
        for (int i = 0; i < stations.length; i++) {
            int sum = 0;
            int j=i;
            validPath = true;
            do {
                sum += stations[j].getCharge();
                sum -= stations[j].getDistance();
                if(sum<0){
                    validPath = false;
                    break;
                }
                j++;
                if(j==stations.length)
                    j=0;
            }while(j != i);
            if(validPath)
                return i;
        }
        return -1;
    }

    //o(n)
    public static int findStartingStationBe(Station[] stations){
        int sum=0, position=0;
        for (int i = 0; i <stations.length ; i++) {
            sum += stations[i].getCharge();
            sum -= stations[i].getDistance();
            if(sum<0) {
                position = i + 1;
                sum = 0;
            }
        }
        return position;
    }


}


class User{
    private String userName;
    private String password;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}