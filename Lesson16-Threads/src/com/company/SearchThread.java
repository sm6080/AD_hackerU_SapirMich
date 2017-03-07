package com.company;

/**
 * Created by Sapir Michaeli on 06.03.2017.
 */
public class SearchThread extends Thread{
    //נניח שיש 2 ליבות במחשב, כתבו פונקציה שמקבלת מערך של int ומספר וצריכה להחזיר אם המספר קיים במערך

    private int []arr;
    private int num;
    private int to,from;
    private FoundListener listener;
    private boolean go=true;

    public SearchThread(int[] arr,  int from, int to,int num, FoundListener listener) {
        this.arr = arr;
        this.from = from;
        this.to = to;
        this.num = num;
        this.listener=listener;
    }

    @Override
    public void run() {
        for (int i = from; i <= to; i++) {
            if(!go) {
                System.out.println("stopped");
                return;
            }
            if(arr[i] == num){
                if(listener != null)
                    listener.found(i, this);
                break; // if I found stop the searching!
            }
        }
        if(listener != null)
            listener.found(-1, this);
    }

    public void stopSearching(){
        go=false;
    }

    public interface FoundListener{
        void found(int index, SearchThread searchThread);
    }
}
