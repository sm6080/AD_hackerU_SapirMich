package com.company;

/**
 * Created by Sapir Michaeli on 15.02.2017.
 */

// נגדיר 2 ערימות: מקסימום ומינימום, במינ את חצי האיברים הגדולים ובמינ את החצי הקטנים.
// לכן ב maxHeap שך הקטנים הוא החציון. יכול להיות שבקטנים יהיה אחד יותר וזה בסדר כי יש כמות אי זוגית של איברים
public class MedianCollection {
    private MinHeap minHeap;
    private MaxHeap maxHeap;

    public MedianCollection() {
        minHeap = new MinHeap();
        maxHeap = new MaxHeap();
    }

    public int getMedian() {
        if (minHeap.getSize() == 0)
            throw new IndexOutOfBoundsException("COLLECTION IS EMPTY");
        return maxHeap.getMax();
    }

    public void insert(int num) {
        maxHeap.insert(num);
        if (maxHeap.getMax()>minHeap.getMin()){
            
        }
        if (maxHeap.getSize() - minHeap.getSize() == 2) {  // מיד שזה 2 זה כבר יהיה יותר מידי ונצטרך לטפל ולהעביר לערימה השניה
            minHeap.insert(maxHeap.extractMax());
        }
    }
}
