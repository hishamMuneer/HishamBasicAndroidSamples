package com.hisham.hishambasicandroidsamples.service_bound;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MyLocalService extends Service {

    private final IBinder iBinder = new LocalBinder();
    private static final String TAG = "MyLocalService";

    public class LocalBinder extends Binder {
        MyLocalService getService(){
            Log.d(TAG, "getService: called");
            return MyLocalService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: called and returned");
        return iBinder;
    }

    public int getNumber(){
        int i = new Random().nextInt();
        Log.d(TAG, "getNumber: " + i);
        return i;
    }

}