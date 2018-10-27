package com.hisham.hishambasicandroidsamples.job_scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;

public class TestJobService extends JobService {

    @Override
    public boolean onStartJob(JobParameters params) {

//        IMPORTANT: This job is running on main thread, create a background thread if you dont want to freeze the ui
        new Thread() {
            @Override
            public void run() {
                super.run();
                String data = params.getExtras().getString("data");
                System.out.println("JOBSCHEDULER: " + data);

                for (int i = 0; i < 15; i++) {
                    printThreadInfo(Thread.currentThread(), i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                JobUtil.doTheJob(TestJobService.this);

            }
        }.start();
        return true;
    }

    private void printThreadInfo(Thread thread, int i) {
        long threadId = thread.getId();
        String threadName = thread.getName();
        System.out.println("JOBSCHEDULER: " + threadName + "-" + threadId + " ====>" + i);
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
