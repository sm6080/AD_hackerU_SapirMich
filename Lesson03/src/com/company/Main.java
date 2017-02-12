package com.company;

public class Main {

    public static void main(String[] args) {
        // Basic Java
        drawRectangle(3, 2, 5, 4);
        drawX(3);

        //drwRectangle(3, 2, 8, 10);

        // מערך שאיבריו מערכים

        int[][] arr = new int[7][3];
        System.out.println(arr[0].length);

        int[][] arr2 = {{2, 3}, {6, 7}, {1, 3, 4}, null};
        int[] arr3 = {4, 6, 7};
        arr2[3] = arr3;
        arr2[0] = new int[]{8, 9, 5, 4};// הכתובת שהוחזרה במקום ה NEW הזה נשמרת במקום הזה (דורס את הכתובת שלו)
        int[][] arr4 = new int[5][];

        /*TwoDimensinalArray matrix= new TwoDimensinalArray(4,3);
        matrix.set(1,2,100);
        System.out.println(matrix.get(1,2));
*/
        Canvas canvas = new Canvas(60, 50);
        canvas.drawRectangle(3, 2, 10, 5);
        canvas.drawRectangle(5, 3, 10, 5);
        canvas.render();


        MyPriorityQueue mqueue = new MyPriorityQueue();
        mqueue.insetBe(45);
        mqueue.insetBe(12);
        mqueue.insetBe(80);
        mqueue.insetBe(15);
        mqueue.insetBe(8);
        mqueue.insetBe(2);

        while (mqueue.getSize() > 0) {
            System.out.println(mqueue.extractMaxBe());
        }

        int[] arr5 = {45, 12, 80, 15, 8, 2};
        heapSort(arr5);
        for (int i = 0; i < arr5.length; i++) {
            System.out.print(arr5[i] + " ");
        }


    }

    // הפנקציה תצייר על המסך מלבן חלול באמצעות כוכביות, הפונ מקבלת ארבעה פרמטרים:  X ו Y הם שיורים של הנקודה של הפינה השמלאית העליונה שלך המלבן
    static void drawRectangle(int x, int y, int width, int height) {
        for (int i = 0; i < y; i++) { // הסמן יורד Y יחידות
            System.out.println();
        }
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < x; j++) { // עבור כל שורה הדפיס רווח X פעמים
                System.out.print(" ");
            }
            for (int j = 0; j < width; j++) {
                System.out.print(i == 0 || i == height - 1 || j == 0 || j == width - 1 ? "*" : " ");
            }
            System.out.println();
        }
    }     // T(n)(x,y,width,height)= y+height*(x+width)


    /*static void drawRectangle(int x, int y, int width, int height) {
        for (int i = 0; i < width; i++) { // הסמן יורד Y יחידות
            System.out.print("*");
        }
        System.out.println();
        for (int i = 0; i < height - 2; i++) {
            System.out.print("*");
            for (int j = 0; j < width - 2; j++) {
                System.out.print(" ");
            }
            System.out.print("*");
            System.out.println();
        }
        for (int i = 0; i < width; i++) { // הסמן יורד Y יחידות
            System.out.print("*");

        }
        System.out.println();
    }
    */
    public static void drawX(int size) {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                if (row == col || row + col == size - 1)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println();
        }
    }



    //-----------heap sort------------
    //n*log(n)
    static void heapSort(int[] arr)
    {
        MyPriorityQueue myPriorityQueue = new MyPriorityQueue(arr);
        for (int i = 0; i < arr.length; i++) {
            int max = myPriorityQueue.extractMaxBe();
            arr[arr.length-1-i] = max;
        }
    }

}


class DivisionResult {
    int times; //חלוקה -מנה
    int remainder; //שארית


    static DivisionResult divide(int x, int y) {
        if (y == 0)
            return null;
        int result = 0;
        int sum = y;
        int largestProductOfY = 0;
        while (sum <= x) {
            sum += y;
            largestProductOfY += y;
            result++;
        }
        DivisionResult divisionResult = new DivisionResult(); // יצרנו משתנה מסוג האובייקט ואת האובייקט
        divisionResult.times = result; //כמה פעמים אפשר להכניס את Y בלי לעבור את X
        divisionResult.remainder = x - largestProductOfY; // distance(x,largestProductOfY)
        return divisionResult; //תוצאת חלוקה
    }
}



//  באפור זה במערך דו ממדי ושינינו לחד ממדי כדי להפחית והוספנו את השדה שורות
class TwoDimensinalArray {
    //private int[][] arr;
    private int[] arr;
    private int rows;

    public TwoDimensinalArray(int rows, int columns) {
        // arr = new int[rows][colums]; זה בדרך היקרה של המון מצביעים
        arr = new int[rows * columns];
        this.rows = rows;
    }

    public void set(int row, int column, int value) {
        //if (row >= arr.length || column >= arr[row].length) // arr[row].length כמה תאים בשורה הנוכחית וזה כללי למרות שבמקרה זה מטריצה דו ממדית
        if(row >= arr.length || column >= columns())
            throw new IndexOutOfBoundsException(); // יוצא ומסיים את התהליך
        // arr[row][column] = value;
        arr[pos(row, column)] = value;
    }

    public int get(int row, int column) {
        // if (row*column >= arr.length ) // arr[row].length כמה תאים בשורה הנוכחית וזה כללי למרות שבמקרה זה מטריצה דו ממדית
        if(row >= arr.length || column >= columns())
            throw new IndexOutOfBoundsException(); // יוצא ומסיים את התהליך
        // return arr[row*column];
        return arr[pos(row, column)];
    }

    private int pos(int row, int column){// (במקום לחזור על זה בשתי הפונקציות )מחזירה את המיקום
        return row * columns() + column;
    }

    public void print() { // 2 סוגים עם FOR אחד ועם שניים
        int col = columns();
        for (int i = 0; i < rows; i++) {
            System.out.print(arr[i] + " ");
            if (i % col == 0)
                System.out.println();
        }

        //2 for
        /*
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < col; j++) {
               System.out.print(arr[pos(i, j)]);
             }
            System.out.println();
        */
    }

    public int rows(){
        return this.rows;
    }

    public int columns() {
        return arr.length / rows();
    }

}




class ElementCount {
    int element;
    int counter;

    public ElementCount(int element, int counter) {
        this.element = element;
        this.counter = counter;
    }



    static void printMoreThankNd4(int[] arr){
        int k = 4 - 1;
        ElementCount[] elementCounts = new ElementCount[k];

        //Step 1 - initializing our temporary counting array
        for (int i = 0; i < elementCounts.length; i++)  // n iterations
            elementCounts[i] = new ElementCount(123, 0);
        //Step 2 - process all the elements in the array
        for (int i = 0; i < arr.length; i++) { // n * (3 + 3 + 3) = 9n
            int j;
            for (j = 0; j < k; j++) {
                if(arr[i] == elementCounts[j].element){
                    //the element arr[i] exists in eleCount
                    //so we increase its counter
                    elementCounts[j].counter++;
                    break;
                }
            }
            if(j == k){ //the element arr[i] was not found in eleCount
                int l;
                for (l = 0; l < k; l++) {
                    if(elementCounts[l].counter == 0){
                        elementCounts[l].element = arr[i];
                        elementCounts[l].counter = 1;
                        break;
                    }
                }
                if(l==k){ //there's no room for the new element
                    //so we decrease by one each counter
                    for (l = 0; l < k; l++) {
                        elementCounts[l].counter--;
                    }
                }
            }
        }

        //Step 3 - check the actual count
        for (int i = 0; i < k; i++) {  // 3 * n
            int actualCount = 0;
            for (int j = 0; j < arr.length; j++) {
                if(arr[j] == elementCounts[i].element)
                    actualCount++;
            }
            if(actualCount > arr.length/(k+1))
                System.out.println(elementCounts[i].element);
        }
    }


            /* הפונקציה בצורה פחות טובה - נאיבית
            static int findSmallLarge(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            boolean f = false;
            for (int j = 0; j < i; j++) {
                if(arr[j]>x){
                    f = true;
                    break;
                }
            }
            if(f)
                continue;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<x){
                    f = true;
                    break;
                }
            }
            if(!f)
                return i;
        }
        return -1;
    }*/



/*static int findSmallLarge(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            int x = arr[i];
            boolean f = false;
            for (int j = 0; j < i; j++) {
                if(arr[j]>x){
                    f = true;
                    break;
                }
            }
            if(f)
                continue;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[j]<x){
                    f = true;
                    break;
                }
            }
            if(!f)
                return i;
        }
        return -1;
    }*/
/*
            1. Create two arrays, leftMax and rightMin
            2. Traverse the input array from left to right,
               and fill leftMax such that leftMax[i] will contains the
               max element up to index i in arr.
            3. Do the same with rightMin but from right left.
            4. Traverse the array, if an element in index i,
               if greater than leftMax[i] and also smaller than
               rightMin[i] then we've found such element.
         */

    // 1    5  4  7  9  6   -1
    //               -1   -1    9999

    // -999 1  5  5  7  9   9

    static int findSmallLarge(int arr[]) {
        int[] leftMax = new int[arr.length];
        leftMax[0] = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = max(leftMax[i - 1], arr[i]);// המקסימום שפגשתי עד עכשיו והמספר שאני מסתכל עליו כרגע
        }
        int rightMin = Integer.MAX_VALUE;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (leftMax[i] < arr[i] && rightMin > arr[i])
                return i;
            rightMin = min(rightMin, arr[i]);
        }
        return -1;
    }


    static int max(int x, int y) {
        if (x > y)
            return x;
        return y;
    }

    static int min(int x, int y) {
        if (x < y)
            return x;
        return y;
    }
}