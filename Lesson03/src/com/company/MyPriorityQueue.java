package com.company;

import org.omg.CORBA.DynAnyPackage.Invalid;

import java.security.InvalidParameterException;

/**
 * Created by Sapir Michaeli on 02.02.2017.
 */
public class MyPriorityQueue {
    private int[] arr;
    private int size; // כמה בפועל אני משתמש מתוך ה 10

    public MyPriorityQueue() {
        arr = new int[10];
        size = 0;
    }

    public MyPriorityQueue(int[] arr) {
        this.arr = arr;
        size = arr.length;
        for (int i = size / 2; i >= 0; i--) {// מתחילים מהאמצע כי אחרי SIZE/2 כולם עלים ואין מה לעשות לגביהם כי הם ממילא ערימה חוקית
            heapify(i);
        }
    }

    public int getSize() {
        return size;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private void heapify(int i) { // (מתקנת לפי מטה) מתקנת את העץ ערימת מקסימום חוקי
        int largest = i;
        int l = leftChild(i);
        int r = rightChild(i);
        if (l < size && arr[l] > arr[largest])// האם הבן השמאלי גדול וממני והאם בכלל יש לי בן שמאלי
            largest = l;
        if (r < size && arr[r] > arr[largest])
            largest = r;
        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(largest);
        }
    }


    //log(n)
    private void bubbleUp(int i) {
        int p; // parent
        while (i != 0 && arr[(p = parent(i))] < arr[i]) {  //השמה  (p=parent(i))  כל עוד לא הגעתי לשורש והאבא קטן מילדו
            int temp = arr[i];
            arr[i] = arr[p];
            arr[p] = temp;
            i = p;

        }
    }

    //parent(leftChild(i))          (leftChild(i)-1)/2   =  (2*i)/2 = i


    //                  בדרך הרגילה - נכתוב בדרך הטובה למעלה

    /*// o(1)
    public void inset(int x) {
        if (size == arr.length) {
            int[] temp = new int[size * 2];  //עשינו כפול 2 כדי שהגדילה תהיה פרופורציונאלית
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        arr[size++] = x;
    }
    */
    //log(n)
    public void insetBe(int x) {
        if (size == arr.length) {
            int[] temp = new int[size * 2];  //עשינו כפול 2 כדי שהגדילה תהיה פרופורציונאלית
            for (int i = 0; i < size; i++) {
                temp[i] = arr[i];
            }
            arr = temp;
        }
        int i = size;
        size++;
        arr[i] = x;
        bubbleUp(i);
    }


    //o(n)
    public int getMax() {
        if (size == 0)
            throw new IndexOutOfBoundsException();

        int result = arr[0];
        for (int i = 1; i < size; i++) {
            if (arr[i] > result)
                result = arr[i];
        }
        return result;

    }

    /*
    //o(n)
    public int extractMax() {
        if (size == 0)
            throw new IndexOutOfBoundsException();
        int indexOfMax = 0;
        int result = arr[0];
        for (int i = 1; i < size; i++) {
            if (arr[i] > result) {
                result = arr[i];
                indexOfMax = i;
            }
        }
        for (int i = indexOfMax; i <size-1 ; i++) {// מקטין ממקום שבו נמצא האינדקס
            arr[i]=arr[i+1];
        }
        size--;
        return result;
        */
    public int extractMaxBe() {
        if (size == 0)
            throw new IndexOutOfBoundsException();
        if (size == 1) {
            size--;
            return arr[0];
        }
        int max = arr[0];
        arr[0] = arr[size - 1];
        size--;
        heapify(0);
        return max;
    }


    public void increase(int i, int addition) {// מקבלת איבר קיים ומגדילה את ערכו
        if (addition < 0 || i >= size || i < 0)
            throw new InvalidParameterException();
        arr[i] += addition;
        bubbleUp(i);
    }

    //log(n)
    public void deleate(int i) {
        increase(i, Integer.MAX_VALUE - arr[i]); //האיבר קפץ לשורש ואז פשוט נוציא אותו בשורה הבא
        extractMaxBe();
    }


}


