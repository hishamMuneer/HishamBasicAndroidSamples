package com.hisham.javalibrary;

import com.hisham.javalibrary.dst.LongestPalinSubstring;
import com.hisham.javalibrary.threading.ThreadSample;

public class MyClass {

    private static final ThreadSample sample = new ThreadSample("Sample1");

    void test() throws InterruptedException {
        ThreadSample sample2 = new ThreadSample("Sample2");
        sample.start(); // main thread
        sample.setSample(sample2);
        sample2.start();
        sample2.setSample(sample2);
//        sample.setMyClass(this);
//        synchronized (synchronizedsample2) {
            sample2.wait(); // main thread
//        }thread
        System.out.println("Main thread execution finished");


    }


    public static void main(String args[]) throws InterruptedException {
//        System.out.println("Hello Android from Java");
//        MyClass myClass = new MyClass();
//        myClass.test();
        // ========== Executors

//        ExecutorService executor = Executors.newFixedThreadPool(5);
//        for (int i = 0; i < 10; i++) {
//            Runnable worker = new WorkerThread("" + i);
//            executor.execute(worker);//calling execute method of ExecutorService
//        }
//        executor.shutdown();
//        while (!executor.isTerminated()) {   }
//
//        System.out.println("Finished all threads");

        // ============ Deadlock

//        DeadlockExample.test();

        // ============== InetAddress

//        try {
//            System.out.print(InetAddress.getByName("www.google.com").getHostAddress());
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }


            String str = "forgeeksskeegfor";
//            String str = "abaxabaxabb";
            System.out.println("Length is: " + LongestPalinSubstring.longestPalSubstr(str));
    }

}
