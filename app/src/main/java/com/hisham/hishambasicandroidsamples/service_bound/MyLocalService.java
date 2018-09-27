package com.hisham.hishambasicandroidsamples.service_bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MyLocalService extends Service {

    private final IBinder iBinder = new LocalBinder();
    private static final String TAG = "HishamSample";

    public class LocalBinder extends Binder {
        MyLocalService getService(){
            printThreadInfo("LocalBinider");
            Log.d(TAG, "getService: called");
            return MyLocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        printThreadInfo("onBind");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private void printThreadInfo(String caller) {
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        Log.d(TAG, "Caller: " + caller + " - Thread info: " + threadId + " - " + threadName );
    }

    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            printThreadInfo("handleMessage");
            Log.d(TAG, "handleMessage: msg" + msg);
        }
    };

    private Runnable runnable = () -> {
        printThreadInfo("getNumber");
        // time consuming task
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = new Random().nextInt();
        Log.d(TAG, "getNumber: " + i);

        Message msg = Message.obtain();
        msg.obj = i;
        handler.sendMessage(msg);

    };

    public void getNumber(){
        WorkerThread thread = new WorkerThread();
        thread.addTask(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service destroyed");
    }
}