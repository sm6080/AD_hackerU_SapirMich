package com.company;

/**
 * Created by hackeru on 06.03.2017.
 */
public class SearchThread extends Thread{
    //נניח שיש 2 ליבות במחשב, כתבו פונקציה שמקבלת מערך של int ומספר וצריכה להחזיר אם המספר קיים במערך

    private int []arr;
    private int num;
    private int to,from;
    private FoundListener listener;

    public SearchThread(int[] arr,  int from, int to,int num, FoundListener listener) {
        this.arr = arr;
        this.from = from;
        this.to = to;
        this.num = num;
        this.listener=listener;
    }

    @Override
    public void run() {
        for (int i = from; i <=to ; i++) {
            if (arr[i]==num){
                if (listener!=null)
                    listener.found(i);
                break; // if I found stop the searching!
            }
        }
    }
    public interface FoundListener{
        void found(int index);
    }
}
