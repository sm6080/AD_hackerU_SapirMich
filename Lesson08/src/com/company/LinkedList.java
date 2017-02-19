package com.company;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;


/**
 * Created by Sapir Michaeli on 14.02.2017.
 */
public class LinkedList<T> implements List<T>,Iterable,Iterator {

    private final Node anchor; // יצביע לראשון ברשימה
    private Node current;
    private int size;


    public int getSize() {
        return size;
    }


    public LinkedList() {
        anchor = new Node<T>(null); //fictitious node
        size = 0;
    }


    @Override
    public void add(T x) {
        if (size == 0)
            anchor.next = new Node(x);
        else {
            Node iterator = anchor;
            int i = 0;
            while (i++ < size)
                iterator = iterator.next;
            iterator.next = new Node(x);
        }
        size++;
    }


    @Override
    public void add(T x, int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        Node iterator = anchor;
        int i = 0;
        while (i++ < index - 1)
            iterator = iterator.next;
        Node temp = iterator.next;
        iterator.next = new Node<T>(x); //TODO: write T every time we do new Node
        iterator.next.next = temp;
        size++;
    }

    @Override
    public void remove(int index) {
        if (index > size) throw new IndexOutOfBoundsException();
        Node iterator = anchor;
        int i = 0;
        while (i++ < index - 1)
            iterator = iterator.next;
        iterator.next = iterator.next.next;
        size--;
        for (int j = 0; j < 9; j++) {

        }
    }

    @Override
    public void set(int index, T x) { //changes value of existing Node
        if (index > size) throw new IndexOutOfBoundsException();
        Node iterator = anchor;
        int i = 0;
        while (i++ < index)
            iterator = iterator.next;
        iterator.value = x;
    }


    @Override
    public T get(int index) {
        if (index > size) throw new IndexOutOfBoundsException();
        Node<T> iterator = anchor;//TODO: REMEMBER <T>
        int i = 0;
        while (i++ < index)
            iterator = iterator.next;
        return iterator.value;
    }

    //search
    @Override
    public int indexOf(T x) {
        Node<T> iterator = anchor.next;
        int i = 0;
        while (i < size) {
            iterator = iterator.next;
            if (iterator.value == x)
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public T[] toArray() {
        T[] arr = (T[]) new Object[size];//TODO: typecasting for initializing type T object
        Node<T> iterator = anchor.next;
        for (int i = 0; i < size; i++, iterator = iterator.next)
            arr[i] = iterator.value;
        return arr;
    }

    @Override
    public String toString() {
        if (size == 0) return "{}";
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        Node iterator = anchor.next;
        for (int i = 0; i < size - 1; i++, iterator = iterator.next)
            stringBuilder.append(iterator.value + " , ");

        stringBuilder.append(iterator.next.value + "}");
        return stringBuilder.toString();
    }

    @Override
    public Iterator iterator() {
        current = anchor;
        return this;
    }

    @Override
    public void forEach(Consumer action) {
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public boolean hasNext() {
        return current.next != null;
    }

    @Override
    public Object next() {
        current = current.next;
        return current.value; //Boxing: Object-->Integer
    }

    private static class Node<T> { //TODO: not necessarily the same T as list--it's a separate class
        T value;
        Node<T> next;

        public Node(T value) {
            this.value = value;
            next = null;
        }
    }
}