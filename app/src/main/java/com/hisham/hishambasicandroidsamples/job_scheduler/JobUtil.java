package com.hisham.hishambasicandroidsamples.job_scheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.PersistableBundle;

/**
 * Created by Hisham on 28/Oct/2018 - 00:24
 */
public class JobUtil {

    public static final int JOB_ID = 0;

    public static void doTheJob(Context context){
        ComponentName serviceComponent = new ComponentName(context, TestJobService.class);
        JobInfo.Builder jobBuilder = new JobInfo.Builder(JOB_ID, serviceComponent);
        jobBuilder.setMinimumLatency(10000L);
        jobBuilder.setOverrideDeadline(50000L);
//        jobBuilder.setPeriodic(30 * 1000);
        PersistableBundle bundle = new PersistableBundle();
        bundle.putString("data", "This is the data");

        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.schedule(jobBuilder.build());

    }

}
