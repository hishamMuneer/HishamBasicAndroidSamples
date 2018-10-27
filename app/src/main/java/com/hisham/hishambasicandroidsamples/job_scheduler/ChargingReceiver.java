package com.hisham.hishambasicandroidsamples.job_scheduler;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ChargingReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        printThreadInfo(Thread.currentThread());
       JobUtil.doTheJob(context);
    }

    private void printThreadInfo(Thread thread) {
        long threadId = thread.getId();
        String threadName = thread.getName();
        System.out.println("JOBSCHEDULER: " + threadName + "-" + threadId);
    }
}
