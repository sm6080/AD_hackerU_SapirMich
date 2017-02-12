package com.company;

import sun.font.FontRunIterator;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //arrays
        int[] t = {4, 7, 1, 3, 6, 8};
        t[0] = 15;
        System.out.println(t[0]);

        // דרך נוספת ליצור מערך
        int[] n = new int[6];
        n[0] = 4;
        n[1] = 5;
        n[5] = 14;

        int x = 6;
        int[] k = new int[x];


        int[] arr1 = t;
        t[0] = 4;
        System.out.println(arr1[0]);//4


        //Random
        Random random = new Random(10);
        for (int i = 0; i < 20; i++) {
            System.out.println(random.nextInt() + ",");
        }

        //כמה מילי שניות חלפו החל מהראשון ל1 1970
        long now = System.currentTimeMillis();
    }


    static void printArray(int[] arr) {
        System.out.print("{");// מדפיסה ולא יורדת שורה -PRINT
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
            if (i < arr.length - 1)
                System.out.print(",");
        }
        System.out.println("}");
    }


    // פונ מקבלת מערך וממיינת אותו (n^2)
    static void bubleSort(int[] arr) {
        int upTo = arr.length - 1; // האיבר האחרון
        boolean isSorted = false;
        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < upTo; i++) {
                if (arr[i] > arr[i + 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                    isSorted = false;
                }
            }
            upTo--; // נעזוב את האיבר האחרון כי הוא במקומו
        }
    }

    // מיון הכנסה כמו חפיסת קלפים   (n^2)
    static void insertionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int key = arr[i];
            int j = i = 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }


    //בהינתן 2 מערכים גדולים היא ממזגת למערך גדול  -(פונ' עזר למיון מיזוג)
    static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        for (int i = 0; i < n2; i++) {
            R[i] = arr[m + 1 + i];
        }
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    //  מיון מיזוג (הפרד ומשול וצרף מקטין את הבעיה עד לבעיה שיודע להסתדר ומחזיר תשובה לזה שקרא לי.. עד הסוף )   (n*log(n)
    static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    // מיון בסיס - קודם נמיין לפי ספרת ההאחדות אח"כ לפי העשרות מאות אלפים וכו..


    //פונקצית עזר למיון מהיר
    //  ממקמת איבר כך שכל האיברים הקטנים ממנו משמאלו וכל מה שבמינו גדול ממנו והוא במקומו הנכון, רואים כמה איברים קטנים יש לי מאיבר הציר
    //example: i=-1/0/1/2/3/4/5/6/7
    //2 7 3 8 34 2 5 3   9                         at the end of loop 1: 2 7 3 8 2 5 3 9 34
    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // counter
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++; // counts of the smaller than pivot
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        i++;
        int temp = arr[i];
        arr[i] = arr[high];
        arr[high] = temp;
        return i;
    }

    // איבר הציר יהיה רנדומלי- ז"א אין קלט גרוע
    static int randomizePartition(int[] arr, int low, int high) {
        if (high == low)
            return low;
        Random random = new Random(System.currentTimeMillis());
        int position = random.nextInt(high - low) + low;
        int temp = arr[high];
        arr[high] = arr[position];
        arr[position] = temp;
        return partition(arr, low, high);
    }

    // מקבלת מערך ומספר K ומחזירה את K המספרים האלו הקטנים ממנו <-> האיבר במקום ה K
    static int quickSelect(int[] arr, int l, int r, int k) {
        if (k > 0 && k <= r - l + 1) {
            int position = randomizePartition(arr, l, r);
            if (position - l == k - 1) // הוא בדיוק במקום וזנ מה שאני צריך
                return arr[position];
            if (position - l > k - 1) {
                return quickSelect(arr, l, position - 1, k);
            } else
                return quickSelect(arr, position + 1, r, k - position + l - 1);
        }
        return Integer.MAX_VALUE; // כל המערך הם האיברים הקטנים
    }

    // n*(log(n))
    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high - 1);
            quickSort(arr, low, pi - 1); //pi במקומו ולא צריך להשתתף בתהליך
            quickSort(arr, pi + 1, high);
        }
    }


    // חיפוש במערך ממוין
    static int binarySearch(int[] arr, int left, int right, int x) {
        if (right >= left) {
            int middle = left + (right - left) / 2; // כמה איברים במערך יש חלקי 2 -(right-left+1)/2
            if (arr[middle] == x)
                return middle;
            if (arr[middle] < x)
                return binarySearch(arr, middle + 1, right, x); // נחפש בחצי הימני כאשר ה middle  עצמו לתוך בתחום החיפוש
            return binarySearch(arr, left, middle - 1, x);
        }
        return -1;
    }

    //o(nlg(n))
    // נמיין את המערך ואז נבדוק משני הצצדים משום שהוא ממוין
    static public boolean containPairWithSumX (int[] arr, int num) {
        quickSort(arr, 0, arr.length - 1);
        int l = 0, r = arr.length - 1;
        while (l < r) {
            int temp = arr[l] + arr[r];
            if (temp == num)
                return true;
            else if (temp < num)
                l++;
            else
                r--;
        }
        return false;
    }


    //{4,2,7,12,8,1}
    //min=2  max=19    19-2+1=18
    //{1,0,1,0,0,1,1,0,0,0,1,0,0,0,0,0,0,1}              =>true!
    // מוצאים את האיבר המינ' והמקס' במערך נגדיר מערך בוליאנים שגודלו מקס-מינ+1 והוא מורכב ממספרים שכבר עברתי עליהם ואז בודקים לכל מספר אם המספר הוא המשלים שלו
    static boolean containPairWithSumXBe(int[]arr, int num) {
        if (arr.length < 100)  // אם המערך קטן נלך לשיטה הראשונה ונמיין אותו - עדיף
            return containPairWithSumX(arr, num);
        //מוצאים את האיבר המינ' והמקס' במערך
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
        }
        if (max - min > 10000) // בדיקה ואם לא מתאים לפה נשלח לגירסה השניה שחוסכת לי במקום
            return containPairWithSumX(arr, num);
        boolean[] binmap = new boolean[max - min + 1];
        for (int i = 0; i < arr.length; i++) {
            int temp = num - arr[i];// המשלים של המספר לסכום
            if (temp >= min && binmap[temp - min])
                return true;
            binmap[arr[i] - min] = true;
        }
        return false;


    }


}

