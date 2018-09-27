package com.hisham.hishambasicandroidsamples.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hisham.hishambasicandroidsamples.R;

import java.util.concurrent.Callable;

import static android.os.Looper.prepare;

public class HandlerActivity extends AppCompatActivity {

    private static final String TAG = "HandlerSample";

    private AdvancedWorker worker;
    private Button btn;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            btn.setText(btn.getText() + " - " + ((CharSequence) msg.obj) + System.getProperty("line.separator"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        btn = findViewById(R.id.button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn.setOnClickListener(v -> {
            System.exit(1);
        });

        Runnable runnable = () -> Log.d(TAG, "onCreate: runnable for handlerthread");

        Thread mainThread = Looper.getMainLooper().getThread();
        Log.d(TAG, "Thread ID: " + mainThread.getId() + " - " + mainThread.getName());

        worker = new AdvancedWorker();

        worker.addTasks(runnable);

        worker.addTasks(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d(TAG, "Thread ID: " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());

            Message msg = Message.obtain();
            msg.obj = "Task 1 ended";
            handler.sendMessage(msg);

        }).addTasks(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Thread ID: " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
            Message msg = Message.obtain();
            msg.obj = "Task 2 ended";
            handler.sendMessage(msg);
        }).addTasks(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Thread ID: " + Thread.currentThread().getId() + " - " + Thread.currentThread().getName());
            Message msg = Message.obtain();
            msg.obj = "Task 3 ended";
            handler.sendMessage(msg);
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        worker.quit();
    }
}
