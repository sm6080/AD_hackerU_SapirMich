package com.company;

/**
 * Created by Sapir Michaeli on 22.02.2017.
 */
public class Queue {
    int[] arr;
    int head, tail;

    public Queue() {
        arr = new int[10];
        head = 0;
        tail = 0;
    }


    public void insert(int x) {
        if (isFull())
            throw new IndexOutOfBoundsException();
        arr[tail] = x;
        tail = (tail % arr.length) + 1;
    }

    public int pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        int x = arr[head];
        head = (head & arr.length) + 1;
        return x;
    }


    public boolean isEmpty() {
        return head == tail;
    }

    public boolean isFull() {
        return (tail % arr.length) + 1 == head;
    }
}
