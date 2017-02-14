package com.company;

/**
 * Created by Sapir Michaeli on 14.02.2017.
 */
public class ArrayList implements List {

    int[] arr;
    int size;

    public ArrayList() {
        arr = new int[10];
        size = 0;
    }

    public ArrayList(int[] arr) {
        this.arr = arr;
        this.size = arr.length;
    }

    @Override
    public void add(int x) {
        copyArray_MakeRoom();
        arr[size++] = x;
    }

    @Override
    public void add(int x, int index) {
        copyArray_MakeRoom();
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException("what are u doing???");
        copyArray_MakeRoom();
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = x;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        for (int i = index; i < size; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
    }

    @Override
    public void set(int index, int x) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        arr[index] = x;
    }

    @Override
    public int get(int index) {
        if (index >= size || index < 0)
            throw new IndexOutOfBoundsException();
        return arr[index];
    }

    @Override
    public int indexOf(int x) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == x)
                return i;
        }
        return -1;
    }

    @Override
    public int[] toArray() {
        int[] tempArr = new int[size];
        for (int i = 0; i < size; i++) {
            tempArr[i] = arr[i];
        }
        return tempArr;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {

        if (size == 0)
            return "{}";
        String s = "{";
        for (int i = 0; i < size - 1; i++) {// בגלל הפסיקים
            s += arr[i] + ",";
        }
        s += arr[size - 1];
        s += "}";
        return s;

    }

    // מפנה מקום במערך אם יש צורך
    private void copyArray_MakeRoom() {
        if (size == arr.length) {
            int[] arrTemp = new int[size * 2];
            for (int i = 0; i < size; i++) {
                arrTemp[i] = arr[i];
            }
            this.arr = arrTemp;
        }
    }
}
