package com.company;
/**
 * Created by Sapir Michaeli on 15.02.2017.
 */

public class MinHeap extends Heap {
    public MinHeap() {
        super(true);
    }

    public MinHeap(int[] arr) {
        super(arr, true);
    }

    public int getMin(){
        return getTop();

    }
    public int extractMin(){
        return extractTop();
    }
}
