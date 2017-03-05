package com.company;

public class Main {

    public static void main(String[] args) {
        int x;
        x=5;
        stam();
        if (5<6){
            x=6;
        }
        while (x<20){
            System.out.println("x "+x);
            x++;
        }
        int sum=0;
        for (int i = 0; i <100 ; i++) {
            System.out.println("first loop"+i);
            for (int j = 0; j <100000000 ; j++) {
                if (j%2==0)
                    sum++;
                else
                    sum--;
            }
        }
        for (int i = 0; i <100 ; i++) {
            System.out.println("second loop"+i);
            for (int j = 0; j <100000000 ; j++) {
                if (j%2==0)
                    sum++;
                else
                    sum--;
            }
        }
        System.out.println(sum);


    }
    public static void stam(){
        System.out.println("ff");
    }
}
