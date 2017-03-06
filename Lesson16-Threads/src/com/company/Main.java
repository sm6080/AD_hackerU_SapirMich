package com.company;

public class Main {

    public static void main(String[] args) {

        int[] arr={5,2,3,6,8,7,9,4};
        int num=6;
        search(arr,num);


    }


   public static void search(int[] arr, int num) {

       SearchThread.FoundListener listener = new SearchThread.FoundListener() {
           @Override
           public void found(int index) {
               System.out.println("found at " + index);
           }
       };
       int n = arr.length - 1;
       SearchThread searchThread1 = new SearchThread(arr, 0, n / 2, num, listener);
       SearchThread searchThread2 = new SearchThread(arr, n / 2 + 1, n, num, listener);
       searchThread1.start();
       searchThread2.start();
    }

}
