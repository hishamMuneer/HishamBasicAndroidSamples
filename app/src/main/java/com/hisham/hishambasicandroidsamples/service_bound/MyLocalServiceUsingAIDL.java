package com.hisham.hishambasicandroidsamples.service_bound;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hisham.hishambasicandroidsamples.IRemoteService;
import com.hisham.hishambasicandroidsamples.util.Utils;

import java.util.Random;

import static com.hisham.hishambasicandroidsamples.util.Utils.printThreadInfo;

public class MyLocalServiceUsingAIDL extends Service {

    private static final String TAG = "HishamSample";
    public static final int TO_UPPER_CASE = 1;
    public static final int TO_UPPER_CASE_RESPONSE = 2;
    private Message initialMsg;

    private int variable = 0;


    @Override
    public void onCreate() {
        super.onCreate();
    }

//        private void replyBack(Message msg) {
//            // This is the action
//            int msgType = msg.what;
//            switch(msgType) {
//                case TO_UPPER_CASE: {
//                    try {
//                        // Incoming data
//                        String data = msg.getData().getString("data");
//                        Message resp = Message.obtain(null, TO_UPPER_CASE_RESPONSE);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("respData", data.toUpperCase());
//                        resp.setData(bundle);
//
//                        msg.replyTo.send(resp);
//                    }
//                    catch (RemoteException e) {
//                        e.printStackTrace();
//                    }
//                    break;
//                }
////                default:
////                    super.handleMessage(msg);
//            }
//        }

    private IRemoteService.Stub binder = new IRemoteService.Stub() {
        @Override
        public int getPid() throws RemoteException {
            Utils.printThreadInfo("IRemote server: ");
            variable++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Process.myPid();
        }

        @Override
        public long getThreadId() throws RemoteException {
            Utils.printThreadInfo("IRemote server: ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return Thread.currentThread().getId();
        }

        @Override
        public String getThreadName() throws RemoteException {
            return Thread.currentThread().getName();
        }

        @Override
        public int getVariable() throws RemoteException {
            return variable;
        }

        @Override
        public void basicTypes(int anInt, String aString) throws RemoteException {

        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        printThreadInfo("onBind");
        return binder;
//        return messenger.getBinder();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }


    private Handler handler = new Handler(Looper.myLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            printThreadInfo("handleMessage");
            Log.d(TAG, "handleMessage: msg" + msg);
//            replyBack(msg);
        }
    };

    private Runnable runnable = () -> {
        printThreadInfo("getNumber");
        // time consuming task
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = new Random().nextInt();
        Log.d(TAG, "getNumber: " + i);

//        replyBack(initialMsg);

        handler.sendMessage(initialMsg);

    };

    public void getNumber(Message msg) {
        initialMsg = Message.obtain(msg);
        WorkerThread thread = new WorkerThread();
        thread.addTask(runnable);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: Service destroyed");
    }
}