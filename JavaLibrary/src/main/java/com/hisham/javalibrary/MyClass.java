package com.hisham.javalibrary;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyClass {

    private static final ThreadSample sample = new ThreadSample("Sample1");

    public static void main(String args[]) throws InterruptedException {
        System.out.println("Hello Android from Java");
//        MyClass myClass = new MyClass();
//
//        ThreadSample sample2 = new ThreadSample("Sample2");
//        sample.start();
//        synchronized (sample) {
//            sample.wait();
//        }
//        sample2.start();
//        System.out.println("Main thread execution finished");

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

        DeadlockExample.test();

        // ============== InetAddress

        try {
            System.out.print(InetAddress.getByName("www.google.com").getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

}
