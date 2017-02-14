package com.company;

/**
 * Created by Sapir Michaeli on 14.02.2017.
 */
public class ArrayList implements List {

    int[]arr;
    int size;

    public ArrayList() {
        arr = new int[10];
        size = 0;
    }

    @Override
    public void add(int x) {
        copyArray();
        arr[size] = x;
    }

    @Override
    public void add(int x, int index) {
        copyArray();
        if(index>size)
            throw new IndexOutOfBoundsException();
        for (int i = size; i >index ; i--) {
            arr[i]=arr[i-1];
        }
        arr[index]=x;
    }

    @Override
    public void remove(int index) {
        if(index>size)
            throw new IndexOutOfBoundsException();
        for (int i = index; i <size ; i++) {
            arr[i-1]=arr[i];
        }
        size--;
    }

    @Override
    public void set(int index, int x) {
        if(index>size)
            throw new IndexOutOfBoundsException();
        arr[index]=x;
    }

    @Override
    public int get(int index) {
        if(index>size)
            throw new IndexOutOfBoundsException();
        return arr[index];
    }

    @Override
    public int indexOf(int x) {
        for (int i = 0; i <size ; i++) {
            if(arr[i]==x)
                return i+1;
        }
        return -1;
    }

    @Override
    public int[] toArray() {
        int []tempArr=new int[size];
        for (int i = 0; i <size ; i++){
            tempArr[i]=arr[i];
        }
        return tempArr;
    }

    private void copyArray(){
        if (size == arr.length) {
            int tempS = size;
            int[] arrTemp = new int[size * 2];
            for (int i = 0; i < size; i++) {
                arrTemp[i] = arr[i];
            }
            arr = arrTemp;
        }
        size++;
    }
}
