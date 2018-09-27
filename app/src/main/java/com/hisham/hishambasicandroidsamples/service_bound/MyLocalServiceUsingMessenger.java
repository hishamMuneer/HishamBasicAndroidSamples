package com.hisham.hishambasicandroidsamples.service_bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class MyLocalServiceUsingMessenger extends Service {

    private static final String TAG = "HishamSample";
    private final Messenger messenger = new Messenger(new IncomingHandler());
    public static final int TO_UPPER_CASE = 1;
    public static final int TO_UPPER_CASE_RESPONSE = 2;

    public class IncomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // This is the action
            int msgType = msg.what;
            switch(msgType) {
                case TO_UPPER_CASE: {
                    try {
                        // Incoming data
                        String data = msg.getData().getString("data");
                        Message resp = Message.obtain(null, TO_UPPER_CASE_RESPONSE);
                        Bundle bundle = new Bundle();
                        bundle.putString("respData", data.toUpperCase());
                        resp.setData(bundle);

                        msg.replyTo.send(resp);
                    }
                    catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                default:
                    super.handleMessage(msg);
            }
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        printThreadInfo("onBind");
        return messenger.getBinder();
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