package com.company;

/**
 * Created by Sapir Michaeli  on 15.02.2017.
 */
public class MergeSortLinkedList {

    public static Node merge(Node n1, Node n2) {
        Node anchor = new Node();
        Node tail = anchor;
        while (true) {
            // if either runs out, use the other one
            if (n1 == null) {
                tail.next = n2;
                break;
            }
            if (n2 == null) {
                tail.next = n1;
                break;
            }
            if (n1.next.value <= n2.next.value) {
                tail.next = n1;
                n1 = n1.next;  //n1 קידום
            } else {
                tail.next = n2;
                n2 = n2.next;  //n2 קידום
            }
            tail = tail.next;
        }
        return anchor.next;

    }


    // נוסיף מצביע ומונה וכך נסרוק את הגודל כדי להגיע לחצי מהרשימה
    public static Node mergeSort(Node first) {
        Node head = first;
        Node middle, last;
        if (head == null || head.next == null)
            return head;
        middle = head;
        last=head;
        int counter = 0;
        while (last.next != null) { // ס   n2 סורק את הרשימה
            last = last.next;
            counter++;
            if (counter % 2 == 0)
                middle = middle.next;
        }
        Node temp = middle.next;
        middle.next = null;
        middle = temp;

        Node headSorted = mergeSort(head);
        Node middleSorted = mergeSort(middle);

        return merge(headSorted, middleSorted);
    }

    public static class Node{
        int value;
        Node next;

        public Node() {

        }
        public Node(int value) {
            this.value = value;
            next=null;
        }
        public Node(int value, Node next) {
            this.value = value;
            this.next=next;
        }
    }
}
