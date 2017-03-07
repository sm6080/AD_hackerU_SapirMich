package com.company;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // write your code here
    }


    //tar1
    public static void mergeFiles(File f1, File f2, File output) {
        InputStream inputStreamA = null;
        InputStream inputStreamB = null;
        OutputStream outputStream = null;
        try {
            inputStreamA = new FileInputStream(f1);
            inputStreamB = new FileInputStream(f2);
            outputStream = new FileOutputStream(output);
            int a, b;
            int actuallyReadA, actuallyReadB;
            byte[] aBytes = new byte[4];
            byte[] bBytes = new byte[4];
            // נקרא int אחד מכל קובץ
            actuallyReadA = inputStreamA.read(aBytes);
            actuallyReadB = inputStreamB.read(aBytes);
            a = ByteBuffer.wrap(aBytes).getInt();
            b = ByteBuffer.wrap(bBytes).getInt();
            while (actuallyReadA == 4 && actuallyReadB == 4) {
                if (a < b) {
                    outputStream.write(aBytes);
                    actuallyReadA = inputStreamA.read(aBytes);
                    a = ByteBuffer.wrap(aBytes).getInt();
                } else {
                    outputStream.write(bBytes);
                    actuallyReadB = inputStreamB.read(bBytes);
                    b = ByteBuffer.wrap(aBytes).getInt();
                }
            }
            //one of them is over
            if (actuallyReadA == 4) {
                do {
                    outputStream.write(aBytes);
                    actuallyReadA = inputStreamA.read(aBytes);
                } while (actuallyReadA == 4);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStreamA != null)
                    inputStreamA.close();
                if (inputStreamB != null)
                    inputStreamB.close();
                if (outputStream != null)
                    outputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    //tar2
    public static int[] smallest(File file, int k) {
        int[] smallest = new int[k];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            byte[] intBytes = new byte[4];
            int actuallyRead;
            int count = 0;
            // ממלא ב K האיברים הראשונים
            while ((actuallyRead = inputStream.read(intBytes)) != -1) {
                int num = ByteBuffer.wrap(intBytes).getInt();
                smallest[count] = num;
                count++;
                if (count == k)
                    break;
            }
            if (count < k) {
                //TODO:copy to array of size count
                return smallest;
            }
            MaxHeap maxHeap = new MaxHeap(smallest);
            while ((actuallyRead = inputStream.read(intBytes)) != -1) {
                int num = ByteBuffer.wrap(intBytes).getInt();
                if (num < maxHeap.getMax()) {
                    maxHeap.extractMax();
                    maxHeap.insert(num);
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return smallest;// הוא עובד עם מצביעים ולכן אנחנו יכולים פשוט להחזיר את המערך
        }
    }




        //tar3
    public static Map<String, Integer> countWords(File file) {
        Map<String, Integer> words = new HashMap<>();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int length = 0;
            char[] chars = new char[50];
            int nextByte;
            while ((nextByte = inputStream.read()) != -1) {
                char c = (char) nextByte;
                if (c == ' ') {
                    String word = new String(chars, 0, length);
                    Integer currentCount = words.get(word);
                    if (currentCount == null) {
                        words.put(word, 1);
                    } else {
                        words.put(word, currentCount + 1);
                    }
                    length = 0;
                } else {
                    chars[length] = c;
                    length++;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }



    //tar4




}
