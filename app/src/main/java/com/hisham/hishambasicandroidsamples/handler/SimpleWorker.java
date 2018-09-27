package com.hisham.hishambasicandroidsamples.handler;

import android.util.Log;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by Hisham on 19/Sep/2018 - 20:32
 */
public class SimpleWorker extends Thread {

    private static final String TAG = SimpleWorker.class.getSimpleName();

    private AtomicBoolean alive = new AtomicBoolean(true);
    private ConcurrentLinkedQueue<Runnable> taskQueue = new ConcurrentLinkedQueue<>();

    public SimpleWorker() {
        super(TAG);
        start();
    }

    @Override
    public void run() {
        super.run();
        while (alive.get()){

            Runnable task = taskQueue.poll();
            if(task != null)
                task.run();

        }

        Log.d(TAG, "run: Thread terminated");
    }


    public SimpleWorker addTasks(Runnable r){
        taskQueue.add(r);
        return this;
    }

    public void quit(){
        alive.compareAndSet(true, false);
    }
}

