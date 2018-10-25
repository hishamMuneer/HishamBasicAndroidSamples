package com.hisham.javalibrary.producer_consumer;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Hisham on 23/Oct/2018 - 18:33
 */
public class ProducerConsumerMain {

    private int producerId = 0;
    private int limit = 5;
    private final Queue<Item> itemQueue = new ArrayDeque<>();
    private Consumer consumer;
    private Producer producer;

    public static void main(String args[]) {
        new ProducerConsumerMain().pc();
//        while (true){
//            System.out.println("Active threads: " + Thread.activeCount());
//        }
    }

        private static final class Lock {
            private volatile String lockedBy = "tt";

            public synchronized String getLockedBy() {
                return lockedBy;
            }

            public synchronized Lock setLockedBy(String lockedBy) {
                this.lockedBy = lockedBy;
                return this;
            }
        }
//    private final Lock lock = new ReentrantLock();
    private final Lock lock = new Lock();


    private void pc() {
        producer = new Producer() {
            @Override
            public void produce() throws InterruptedException {

                synchronized (lock) {
//                if (lock.tryLock()) {
//                    try {
                        printThreadInfo(Thread.currentThread());
                        while (itemQueue.size() >= limit || lock.getLockedBy().equalsIgnoreCase("producer")) {
                            System.out.println(Thread.currentThread().getName() + " waiting.");
                            lock.setLockedBy("producer").wait();
                        }
                        lock.setLockedBy("none");
                    System.out.println(Thread.currentThread().getName() + " started execution.");
                        Item item = new Item(producerId++);
                        itemQueue.add(item);
                        System.out.println("Item produced: " + producerId + " - Q Size: " + itemQueue.size());
                        lock.notifyAll();
//                    } finally {
//                        lock.unlock();
//                    }
                }
            }
        };
        consumer = new Consumer() {
            @Override
            public void consume() throws InterruptedException {

                synchronized (lock) {
//                if (lock.tryLock()) {
//                    try {
                        printThreadInfo(Thread.currentThread());
                        while (itemQueue.size() <= 0 || lock.getLockedBy().equalsIgnoreCase("consumer")) {
                            System.out.println(Thread.currentThread().getName() + " waiting.");
                            lock.setLockedBy("consumer").wait();
                        }
                    lock.setLockedBy("none");
                    System.out.println(Thread.currentThread().getName() + " started execution.");
                            Item poll = itemQueue.poll();
                            lock.notifyAll();
                            if (poll != null)
                                System.out.println("Item consumed: " + poll.getId() + " Q Size: " + itemQueue.size());
                            else
                                System.out.println("Item is " + poll);
//                    } finally {
//                        lock.unlock();
//                    }
                }
            }
        };

        new Thread("Consumer") {
            @Override
            public void run() {
                while (true) {
                    try {
//                        sleep();
                        consumer.consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread("Consumer2") {
            @Override
            public void run() {
                while (true) {
                    try {
//                        sleep(100);
                        consumer.consume();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
//        new Thread("Consumer3") {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
////                        sleep(100);
//                        consumer.consume();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();


        new Thread("Producer") {
            @Override
            public void run() {

                while (true) {
                    try {
//                        sleep(100);
                        producer.produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        new Thread("Producer2"){
            @Override
            public void run() {

                while (true) {
                    try {
//                        sleep(57);
                        producer.produce();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    private void printThreadInfo(Thread thread) {
        long threadId = thread.getId();
        String threadName = thread.getName();
        System.out.println("Thread info: " + threadName + "-" + threadId);
    }


}
