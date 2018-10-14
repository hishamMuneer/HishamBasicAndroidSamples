package com.hisham.javalibrary;

/**
 * Created by Hisham on 04/Oct/2018 - 09:34
 */
public class ThreadSample extends Thread {

    private MyClass myClass;
    private ThreadSample sample2;

    ThreadSample(String name){
        setName(name);
    }

    void setMyClass(MyClass myClass){
        this.myClass = myClass;
    }

    @Override
    public void run() {
        super.run();

        if(getName().equals("Sample1")) {
            System.out.println("Thread sleeping: " + getName());
            for (int i = 0; i < 10000; i++) {
                i = i--;
//                try {
//                    sleep(30);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
            synchronized (sample2) {
                sample2.interrupt();
            }
            System.out.println("Thread running: " + getName());
        } else if (getName().equals("Sample2")) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread sleeping: " + getName() + "  - " + i);
                try {
//                    if( i == 7){
//                        synchronized (this) {
//                            notifyAll();
//                        }
//                    }
                    sleep(500);
//                    synchronized (sample2) {
//                        sample2.interrupt();
//                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                System.out.println("Thread running: " + getName());
            }
        }
    }

    public void setSample(ThreadSample sample2) {
        this.sample2 = sample2;
    }
}
