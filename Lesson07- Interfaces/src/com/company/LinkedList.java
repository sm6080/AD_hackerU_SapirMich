package com.company;

import java.util.Iterator;

/**
 * Created by Sapir Michaeli on 14.02.2017.
 */
public class LinkedList implements List,Iterable,Iterator{

    private Node anchor; // יצביע לראשון ברשימה
    private Node last; // יצביע לאחרון ברשימה
    private int size;
    private Node current;

    public LinkedList() {
        anchor = new Node(0); //הוא  Node  פיקטיבי
        size = 0;
        last=anchor;
    }

    @Override
    public void add(int x) {
        // שמנו בהערה כי הוספנו את ה last
       /* Node last=anchor;
        while (last.next!=null){
            last=last.next;
        }*/
        last.next=new Node(x);
        last=last.next;
        size++;

        /*// another version

            Node n=anchor;
            for (int i =0; i<size; i++) {
                n=n.next;
            }
            n.next=new Node(x);
            size++;
        }*/
    }



    @Override
    public void add(int x, int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
        if (index == size) {  // טיפול אם זה באחרון
            add(x);
            return;
        }
        Node theOneBefore = anchor;
        for (int i = 0; i < index; i++) {
            theOneBefore = theOneBefore.next;
        }
        Node added = new Node(x);
        added.next = theOneBefore.next;
        theOneBefore.next = added;
        size++;

        /*// another version

           if(index>size||index<0)
              throw new IndexOutOfBoundsException();
            Node n=anchor,temp;
            for (int i =0; i<index-1 ; i++)
                n=n.next;
            temp=n.next;
            n.next=new Node(x);
            n.next.next=temp;
            size++;
            }
        }*/
    }

    @Override
    public void remove(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
        Node theOneBefore = anchor;
        for (int i = 0; i < index; i++) {
            theOneBefore = theOneBefore.next;
        }
        if (index == size)
            last = theOneBefore;
        theOneBefore.next = theOneBefore.next.next;
        size--;
    }

    @Override
    public void set(int index, int x) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
        Node n = anchor;
        for (int i = 0; i <= index; i++) {
            n = n.next;
        }
        n.next.value = x;
    }

    @Override
    public int get(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException();
        Node n = anchor;
        for (int i = 0; i <= index; i++) {
            n = n.next;
        }
        return n.value;
    }

    //search
    @Override
    public int indexOf(int x) {
        Node n = anchor.next;
        for (int i = 0; i < size; i++) {
            if (n.value == x)
                return i ;
            n = n.next;
        }
        return -1;
    }

    @Override
    public int[] toArray() {
        Node n=anchor.next;
        int [] tempArr=new int[size];
        for (int i =0; i < size; i++){
            tempArr[i]=n.value;
            n=n.next;
        }
        return tempArr;
    }

    @Override
    public String toString() {
        if(size==0)
            return "{}";
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("{")  ;
        Node n=anchor.next;
        for (int i = 0; i < size-1; i++) {
            stringBuilder.append(n.value+",");
            n=n.next;
        }
        stringBuilder.append(n.value);
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public int setSize() {
        return size;
    }

    @Override
    public Iterator iterator() {
        current=anchor;
        return this;  // מצביע לעצמי , אני גם Iterator
    }

    @Override
    public boolean hasNext() {
        return current.next!=null; //אם הוא לא אחרון מחזירים TRUE
    }

    @Override
    public Object next() {
        current=current.next;
        return current.value;
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
