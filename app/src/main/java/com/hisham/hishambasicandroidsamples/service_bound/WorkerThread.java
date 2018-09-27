package com.hisham.hishambasicandroidsamples.service_bound;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

public class WorkerThread extends HandlerThread {
    private static final String TAG = "HishamSample";
    private Handler handler;
    public WorkerThread() {
        super("Worker");
        start();
        handler = new Handler(getLooper());
        printThreadInfo("inside constructor worker");
    }

    public void addTask(Runnable r){
        printThreadInfo("inside worker");
        handler.post(r);
    }

    private void printThreadInfo(String caller) {
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        Log.d(TAG, "Caller: " + caller + " - Thread info: " + threadId + " - " + threadName );
    }

}
