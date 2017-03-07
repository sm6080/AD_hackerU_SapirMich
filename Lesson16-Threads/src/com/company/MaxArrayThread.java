package com.company;

import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Sapir Michaeli on 07.03.2017.
 */
public class MaxArrayThread extends Thread {
    // תוכנית שבהינתן מערך של int מדפיסה למסך את האיבר הגדול במערך

    private int[] arr;
    private int from,to;
    private FoundListener listener;

    public MaxArrayThread(int[] arr, int from, int to, FoundListener listener) {
        this.arr = arr;
        this.from = from;
        this.to = to;
        this.listener = listener;
    }

    @Override
    public void run() {
        int max=Integer.MIN_VALUE;
        for (int i = from; i <=to ; i++) {
            if (arr[i]>max)
                    max=arr[i];
        }
        if (listener!=null)
            listener.found(max);
    }

    public interface FoundListener{
        void found( int max);
    }
}
