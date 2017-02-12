package com.company;

public class Main {

    public static void main(String[] args) {

        //************
        byte myByte; //-128 to 127
        myByte = 100;
        short myShort = 120; //2 bytes
        int myInt;// 4 bytes
        long myLong = 4234234L; // 8 bytes
        myByte = 127;
        myByte++;
        //System.out.println(myByte);
        float myFloat = 123.4f; //4 bytes
        double myDouble = 123.4; //8 bytes
        char ch = 'c';

        float myFloat2 = (float) 123.4;


        int x = 5;
        x = (5 + 3) * 2;
        x = 5 - 2;
        x = 5 * 2;
        x = 5 % 2;
        int y = 3;
        x = y + 1;
        x = x + 5;
        x += 5;
        x += 1;
        x++;
        ++x;
        x = 5;
        y = x++; //y = 5, x = 6

        boolean myBoolean = true;
        myBoolean = 5 <= 7;
        myBoolean = 5 != 7;
        myBoolean = 5 == 7;
        myBoolean = !(5 == 7);
        myBoolean = 5 < 7 && 7 % 2 == 0;
        myBoolean = 5 < 7 || 7 % 2 == 0;
        myBoolean = (5 < 7 && 8 < 10) || 3 != 5;

        //2*(3+4) = 2*3 + 2*4
        boolean a = false, b = true;
        boolean c = !(a && b);
        c = !a || !b;

        //************
        //System.out.println(); //shotrts: sout +tab

        //casting
        System.out.println("1" + 1 + 1); //prints 111
        System.out.println("1" + 1 + 1); //prints 12
        System.out.println(1 + 1 + "1"); // prints 21

        //***********
        if (5 < 7)
            System.out.println("5 is less than 7");
        else
            System.out.println("5 is greater than or equal to 7");

        int age = 15;
        if (age < 18)
            System.out.println("you may not drink alcohol");
        else if (age < 21)
            System.out.println("you may drink with your parents");
        else
            System.out.println("you may drink alcohol");
        switch (age) {
            case 18:
                System.out.println("age is exactly 18");
            case 21:
                System.out.println("age is exactly 21");
                break;
            case 35:
                break;
            default:
                break;
        }

        int i = 0;
        while (i < 10) {
            System.out.println("i = " + i);
            i++;
        }

        for (int j = 0; j < 10; j++) {
            System.out.println(j);
        }

        x = 0;
        do {
            System.out.println(x);
            x++;
        } while (x < 10);


        /* short if
        int z = 0;
        if(x < 10)
            z = 100;
        else
            z = 200;
        */
        //int z = x < 10 ? 100 : 200;


        // bitwise operator
        //לוקח את המספרים בבינארית ועושה את הפעולות
        x = 3 & 4;
        System.out.println(x); //0

        x = 3 | 4;
        System.out.println(x); // 7

        x = 3 ^ 2;
        System.out.println(x); //1

        x = 3 << 1; //כל ביט זז שמאלה (אומרים לביטים זוז שמאלה צעד 1 )- הכפלה ב 2 !   6
        x = 3 >> 1; //כל ביט זז ימינה (אומרים לביטים זוז ימינה צעד 1 )- חלוקה ב 2 !

        System.out.println(sumDigits(413));

    }

    //the distance between x and y
    static int distance(int x, int y) {
        int result = x - y;
        if (result < 0)
            result += -1;
        return result;
    }

    //Ex:
    //ex1:
    /*static int distance1(int x, int y) {
        int i, tmp;
        if (x > y) {
            tmp = x;
            x = y;
            y = tmp;
        }
        for (i = 0; x < y; i++, x++);
        return i;
    }*/

    //ex1- *B
    // הצהרנו 2 משתנים שמכילים את X ו Y  ומשתנה RESULT שמתחיל ב 0 ואז בודקים את המספר הקטן פלוס התוצאה האם קטן מהגדול עד שמוצאים את התוצאה
    static int distance1B(int x, int y) {
        int small = x;
        int large = y;
        if (x > y) {
            small = y;
            large = x;
        }
        int result = 0;
        while (small + result < large)
            result++;
        return result;
    }

    //ex2:
    // לא הכי יעיל
    /*static int product (int x, int y) {
        if (x == 0 || y == 0)
            return 0;
        for (int i=0; i < y; i++) {
                    x += x;
                }
        return x;
    }
    */
    //ex2- *B
    //לא צריך לשאול אם הם 0 משום שב SMALL כבר ייכנס המספר הקטן וזה ה 0 ויהיו 0 איטרציות
    static int productB(int x, int y) { // כפל
        int small = x;
        int large = y;
        if (x > y) {
            small = y;
            large = x;
        }
        int result = 0;
        for (int i = 0; i < small; i++) {
            result += large;
        }
        return result;
    }

    //לשכלל את הפונקציה שכמות האיטרציות תקטן בהרבה
    // log(n)
    static int productBe(int x, int y) {
        int result = 0;
        while (y > 0) {
            if (y % 2 == 0) {
                y = y >> 1;
                x = x << 1;
            } else {
                y--; // גם את זה אפשר ע"י ביטים
                result += x;
            }
        }
        return result;
    }

    //ex3:
    static int quotient(int x, int y) { //מנה - השלם
        if (y == 0)  // למנוע לולאה אינסופית
            return -1;
        int result = 0;
        int sum = y;
        while (sum <= x) {
            sum += y;
            result++;
        }
        return result;
    }

    //ex4:
    static int remainder(int x, int y) { //modolu
        if (y == 0)  // למנוע לולאה אינסופית
            return -1;
        return distance1B(x, productB(quotient(x, y), y));
    }
    // נראה שאפשר לשדרג את פונקצית השלם שתחזיר גם את השארית משום שבפונקצת השארית יש חוסר יעילות

    //ex5:
    static int power(int x, int y) {
        if (x == 0) {
            if (y == 0)
                return -1;
            return 0;
        } else if (y == 0 || x == 1)
            return 1;
        int result = x;
        for (int i = 1; i < y; i++) { // התחלנו מ 1 כי את ה RESULT אתחלנו ב X
            // result=productB(result,x);
            result *= x;
        }
        return result;
    }

    //לשכלל את הפונקציה שכמות האיטרציות תקטן בהרבה
    // log(n)
    static int powerBe(int x, int y) {
        if (x == 0) {
            if (y == 0)
                return -1;
            return 0;
        } else if (y == 0 || x == 1)
            return 1;
        if (y % 2 == 0) {    // <->(y&1)!=1
            int tmp = powerBe(x, y / 2);
            return tmp * tmp;
        } else {
            int tmp = powerBe(x, (y - 1) / 2);
            return tmp * tmp * x;
        }
    }

    //ex6:
    static int powerWithoutForW(int x, int y) {
        if (x == 0) {
            if (y == 0)
                return -1;
            return 0;
        } else if (y == 0 || x == 1)
            return 1;
        return powerWithoutForW(x, y - 1) * x;
    }

    //ex6:
    /*static int squrtUp (int x ) {
        int i=1,result;
        while(i<x)
        {
            result=power(i,2);
            i++;
        }
        return  --i;
    }
    */

    static int squrtUpB(int x) {
        int result = 0;
        while (result * result < x)
            result++;
        return result;
    }
    //לשדרג את הפומ כדי שתחזיר FLOAT וגם תקטין את כמות האיטרציות

    //--------- END EX-----------

    // פיבונאצי'י
    // (2^n) זה ממש גרוע כי זה זמן ריצה אקספוננציאלי
    /*static int fib(int n){
        if(n==1 || n==2)
            return  1;
        return fib(n-1)+(n-2);
    }
    */

    // (n)
    static int fibBe(int n) {
        if (n == 1 || n == 2)
            return 1;
        int a = 0;
        int b = 1;
        int c;
        for (int i = 2; i <= n; i++) {
            c = a + b;
            a = c;
            b = c;
        }
        return b;
    }


    public static int sumDigits(int num) {
        int sum = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            num /= 10;
            sum += lastDigit;

        }
        return sum;
    }

    public static int largestDigits(int num) {
        int max = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            num /= 10;

            if (lastDigit > max)
                max += lastDigit;

        }
        return max;
    }

    public static int reversetDigits(int num) {
        int reverse = 0;
        while (num != 0) {
            int lastDigit = num % 10;
            num /= 10;

            reverse = reverse * 10 + lastDigit;
        }
        return reverse;
    }

    public static boolean isDivBy3(int num) {
        int sumOfDigits = finalSumDigitsRec(num);
        return sumOfDigits == 3 || sumOfDigits == 6 || sumOfDigits == 9;
    }

    //עזר שמחשבת את הסכום הסופי למס חד ספרתי
    public static int finalSumDigitsRec(int num) {
        int result = 0;
        while (result > 10)
            result = sumDigits(result);
        return result;
    }


}
