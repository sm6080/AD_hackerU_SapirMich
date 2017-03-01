package com.company;

/**
 * Created by Sapir Michaeli on 22.02.2017.
 */
public class Queue {
    int[] arr;
    int front, rear;
    int size;

    public Queue() {
        arr = new int[10];
        front = 0;
        rear = arr.length - 1;
        size = 0;
    }

    public int size() {
        if (rear > front)
            return rear - front + 1;
        return arr.length - front + rear + 1; // בשביל המעגליות
    }

    // מה שבהערה זה בגלל שהוספנו את ה size אז שינינו את הפתרון


    public void insert(int x) {
        if (isFull())
            throw new IndexOutOfBoundsException();
       /* arr[rear] = x;
        rear = (rear % arr.length) + 1;*/
        rear = (rear + 1) % arr.length;
        arr[rear] = x;
        size++;
    }

    public int pop() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        int x = arr[front];
        front = (front + 1) % arr.length;
        size--;
        return x;
    }

    //פופ וםרונט שניהם מחזירים את הראשון אך פופ גם מוציא אותו
    public int front() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return arr[front];
    }


    public int rear() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return arr[rear];
    }


    public boolean isEmpty() {
        //return front == rear;
        return size == 0;
    }

    public boolean isFull() {
        //return (rear % arr.length) + 1 == front;
        return size == arr.length;
    }

}
