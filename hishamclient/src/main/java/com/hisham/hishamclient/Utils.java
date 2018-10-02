package com.hisham.hishamclient;

import android.util.Log;

import static android.os.Process.myPid;

/**
 * Created by Hisham on 28/Sep/2018 - 16:24
 */
public class Utils {
    public static final String TAG = "SampleUtils";
    public static void printThreadInfo(String caller) {
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        Log.d(TAG, "Caller: " + caller + " - Pid: " + myPid() + " - Thread info: " + threadId + " - " + threadName );
    }
}
