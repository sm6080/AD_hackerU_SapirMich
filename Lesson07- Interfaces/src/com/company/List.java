package com.company;

/**
 * Created by Sapir Michaeli on 14.02.2017.
 */
public interface List {

    void add(int x);
    void add(int x, int index);
    void remove( int index);
    void set( int index, int x);
    int get( int index);

    /**
     * finds an element in the list
     * @param x the element we looking for
     * @return the position in the list, zero based. returns -1 if not found
     */
    int indexOf( int x);
    int[] toArray();



}
