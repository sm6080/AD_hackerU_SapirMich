package com.company;

public class Main {

    public static void main(String[] args) {

         /*MyClass myClass = new MyClass();
        myClass.run();
        Runnable myRunnable = new MyClass();
        myRunnable.run();
        Runnable myRunnable2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("running...");
            }
        };
        Runnable myRunnable3 = ()-> System.out.println("run2");
        myRunnable3.run();*/


        /*Thread thread1 = new Thread(()->slowRunningFunction(1));
        thread1.start();
        Thread thread2 = new Thread(()->slowRunningFunction(2));
        thread2.start();*/


        /*Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("thread1 " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread1.start();
        //thread1.interrupt();
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    if(i==5)
                        try {
                            thread1.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    System.out.println("thread2 " + i);
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                    }
                }
            }
        });
        thread2.start();*/



        /*System.out.println("first call to slowRunningFunction:");
        slowRunningFunction(1);
        System.out.println("second call to slowRunningFunction:");
        slowRunningFunction(2);*/

        //System.out.println("done calling slowRunningFunction");


        /*MyThread myThread = new MyThread();
        myThread.start();*/


        int[] arr2 = {5, 2, 3, 6, 11, 4, 2, 4, 6};
        int[] arr = {1,2,3,4,5,6,7,8};
        int num = 6;
       // search(arr, num);
        maxInArray(arr2);


        Thread.currentThread();
    }


    public static int slowRunningFunction(int x) {

        System.out.println("starting " + x);
        int sum = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100000000; j++) {
                if (j % 2 == 0)
                    sum++;
                else
                    sum--;
            }
        }
        System.out.println("done " + x);
        return sum;
    }


    public static void search(int[] arr, int num) {

        class SearchFoundListener implements SearchThread.FoundListener {
            boolean f = false;
            int index;
            SearchThread thread1, thread2;

            public void setThread1(SearchThread thread1) {
                this.thread1 = thread1;
            }

            public void setThread2(SearchThread thread2) {
                this.thread2 = thread2;
            }

            @Override
            public void found(int index, SearchThread searchThread) {
                if (!f) {
                    f = true;
                    if (index == -1) {
                        this.index = -1;
                    } else {
                        System.out.println("found at " + index);
                        this.index = index;
                        if (searchThread == thread1)
                            thread2.stopSearching();
                        else
                            thread1.stopSearching();
                    }
                }else{
                    if(index == -1){
                        if(this.index == -1){
                            System.out.println("not found");
                        }else{

                        }
                    }else {
                        if (this.index == -1) {
                            System.out.println("found at " + index);
                        } else {

                        }
                    }
                }
            }
        }

        SearchFoundListener listener = new SearchFoundListener();
        int n = arr.length - 1;
        SearchThread searchThread1 = new SearchThread(arr, 0, n / 2, num, listener);
        SearchThread searchThread2 = new SearchThread(arr, n / 2 + 1, n, num, listener);
        listener.setThread1(searchThread1);
        listener.setThread2(searchThread2);
        searchThread1.start();
        searchThread2.start();
    }





    // תוכנית שבהינתן מערך של int מדפיסה למסך את האיבר הגדול במערך
    public static void maxInArray(int [] arr) {

        MaxArrayThread.FoundListener listener = new MaxArrayThread.FoundListener() {
            boolean firstResponse = false;
            int maxFromFirstResponse;
            long start=System.nanoTime();

            @Override
            public void found(int max) {
                if (firstResponse) {
                    firstResponse = false;
                    maxFromFirstResponse = max;
                } else {
                    // שלושת השורות האלו הן בשביל להציג את הזמן

                    if (max > maxFromFirstResponse)  //this.max - מה שהיה הקודם
                        System.out.println("max is: " + max);
                    else
                        System.out.println("max is: " + maxFromFirstResponse);
                }
            }
        };

        int middle = (arr.length - 1)/2;
        MaxArrayThread maxArrayThread1 = new MaxArrayThread(arr, 0, middle, listener);
        MaxArrayThread maxArrayThread2 = new MaxArrayThread(arr, middle+ 1, arr.length, listener);
        maxArrayThread1.start();
        maxArrayThread2.start();
    }

}

/*
•	יש 2 דרכים לגרום למחלקה להשתמש בThread:
1.	לממש את Runnable ולהכיל שדה מסוג Thread
public class MyThread implements rUNNABLE
{
    Thread thread;

     @Override
    public void run() {
     System.out.println("do long running stuff");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done doing long running stuff");
      }


2.	לרשת מThread  ולדרוס את run()
public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("do long running stuff");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done doing long running stuff");

    }



פונקציות של המחלקה THREAD
public void start ()
ניתן לקרוא לפונקציה הזאת רק פעם אחת על כל Thread. היא בונה Thread חדש ומצהירה שהוא מוכן להרצה. ברגע שניתן להפעיל את run(), מפעילה אותו.

public void run ()
הרצת הThread.

public final void setPriority (int priority)
קובע את הpriority  (רמת הדחיפות) של הThread.

public void interrupt ()
ממשיך את הרצת הThread אם הוא היה מורדם/מושהה.

public final void join (long millisec)
הThread שבריצתו קוראים לjoin מוותר על זמן הריצה שלו במשך milisec מילי-שניות (אם לא שולחים פרמטר פשוט מוותר עד שהתהליך השני ימות) לטובת Thread אחר שאתו הוא קרה לjoin
למשל: t.join() התהליך שקרה לזה מחכה עד שt  ימות בשביל להמשיך לרוץ.

public final boolean isAlive ()
מחזיר האם הThread עוד חי, ז"א אם הוא עוד לא סיים את הרצתו.




פונקציות סטטיות של המחלקה THREAD (המופעלות על מי קורה להם)

public static void yield ()
הThread שמפעיל את זה מוותר על זמן הריצה שלו לטובת Thread אחר כלשהוא באותו priority.

public static void sleep (long millisec)
מקפיא את הThread הנוכחי במשך milisec מילי-שניות.

public static Thread currentThread ()
מחזיר reference הThread שקרה למטודה.


*/

