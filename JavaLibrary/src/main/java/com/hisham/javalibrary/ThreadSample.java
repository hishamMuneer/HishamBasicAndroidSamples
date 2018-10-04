package com.hisham.javalibrary;

/**
 * Created by Hisham on 04/Oct/2018 - 09:34
 */
public class ThreadSample extends Thread {

    ThreadSample(String name){
        setName(name);
    }

    @Override
    public void run() {
        super.run();

        if(getName().equals("Sample1")) {
            System.out.println("Thread sleeping: " + getName());
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread running: " + getName());
        } else if (getName().equals("Sample2")) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Thread sleeping: " + getName());
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread running: " + getName());
            }
        }
    }
}
