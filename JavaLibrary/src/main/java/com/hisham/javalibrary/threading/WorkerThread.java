package com.hisham.javalibrary.threading;

/**
 * Created by Hisham on 04/Oct/2018 - 09:54
 */
/* package */class WorkerThread implements Runnable {
    private String message;
    public WorkerThread(String s){
        this.message=s;
    }
    public void run() {
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);
        processmessage();//call processmessage method that sleeps the thread for 2 seconds
        System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name
    }
    private void processmessage() {
        try {  Thread.sleep(200);  } catch (InterruptedException e) { e.printStackTrace(); }
    }
}
