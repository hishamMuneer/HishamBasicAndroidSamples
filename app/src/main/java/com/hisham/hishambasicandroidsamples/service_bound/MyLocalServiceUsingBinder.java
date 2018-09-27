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

import java.util.Random;

public class MyLocalServiceUsingBinder extends Service {

    private final IBinder iBinder = new LocalBinder();
    private static final String TAG = "HishamSample";

    public class LocalBinder extends Binder {
        MyLocalServiceUsingBinder getService(){
            printThreadInfo("LocalBinider");
            Log.d(TAG, "getService: called");
            return MyLocalServiceUsingBinder.this;
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



    public int getNumber(){
        printThreadInfo("getNumber");
        // time consuming task
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = new Random().nextInt();
        Log.d(TAG, "getNumber: " + i);
        return i;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service destroyed");
    }
}