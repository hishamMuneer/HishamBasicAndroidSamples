package com.hisham.hishambasicandroidsamples.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

/**
 * Created by Hisham on 23/Sep/2018 - 09:37
 */
public class AdvancedWorker extends HandlerThread {

    private static final String TAG = AdvancedWorker.class.getSimpleName();

    private Handler handler;

    public AdvancedWorker() {
        super(TAG);
//        setDaemon(true);
        start();
        handler = new Handler(getLooper());
        Log.d(TAG, "AdvancedWorker: isDaemon?? - " + isDaemon());
    }

    public AdvancedWorker addTasks(Runnable r){
        handler.post(r);
        return this;
    }



    @Override
    public void run() {
        super.run();

        try {
            sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.d(TAG, "run: Performing task in background");

    }


}
