package com.company;

/**
 * Created by Sapir Michaeli on 14.02.2017.
 */
public class LinkedList implements List{

    private Node anchor; // יצביע לראשון ברשימה
    private int size;

    public LinkedList() {
        anchor = new Node(0); //הוא  Node  פיקטיבי
        size = 0;
    }

    @Override
    public void add(int x) {
        Node n=anchor;
        for (int i = 1; i < size; i++) {
            n=n.next;
        }
        n.next=new Node(x);
        size++;
    }

    @Override
    public void add(int x, int index) {
        if (index>size+1)
            throw new IndexOutOfBoundsException();
        Node n=anchor;
        Node temp=anchor;
        for (int i = 0;  i<index-1; i++) {
            n=n.next;
            size++;
        }
        temp=n.next;
        n.next=new Node(x);
        n.next.next=temp;
    }

    @Override
    public void remove(int index) {
        if (index>size)
            throw new IndexOutOfBoundsException();
        Node n=anchor;
        for (int i = 0;  i<index-1; i++) {
            n=n.next;
            size--;
        }
        n.next=n.next.next;
    }

    @Override
    public void set(int index, int x) {
        if (index>size)
            throw new IndexOutOfBoundsException();
        Node n=anchor;
        for (int i = 0;  i<index-1; i++) {
            n=n.next;
        }
        n.next.value=x;

    }

    @Override
    public int get(int index) {
        if (index>size)
            throw new IndexOutOfBoundsException();
        Node n=anchor;
        for (int i = 0;  i<index-1; i++) {
            n=n.next;
        }
        return n.next.value;
    }

    //search
    @Override
    public int indexOf(int x) {
        Node n=anchor;
        for (int i = 1; i < size; i++) {
          if(n.value==x)
            return i+1;
          n=n.next;
        }
        return -1;

    }

    @Override
    public int[] toArray() {
        Node n=anchor;
        int [] arr=new int[size-1];
        for (int i = 1; i < size; i++){
            arr[i-1]=n.value;
            n=n.next;
        }
        return arr;
    }

    public void print(){
        Node node=anchor;
        for (int i = 0; i <size ; i++) {
            System.out.print(node.value+" ");
            node=node.next;
        }
    }

    private static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            next=null;
        }
    }
}
