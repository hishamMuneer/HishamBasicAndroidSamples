package com.hisham.hishambasicandroidsamples.service_bound;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hisham.hishambasicandroidsamples.R;

public class BoundedBinderActivity extends AppCompatActivity {
    private static final String TAG = "HishamSample";
    private Button btn_date;
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLocalServiceUsingBinder.LocalBinder binder = (MyLocalServiceUsingBinder.LocalBinder) iBinder;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: " + componentName.flattenToShortString());
        }
    };
    private MyLocalServiceUsingBinder mService;
    private MyLocalServiceUsingBinder mService2;
    private ServiceConnection mConn2  = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLocalServiceUsingBinder.LocalBinder binder = (MyLocalServiceUsingBinder.LocalBinder) iBinder;
            mService2 = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private View btn_Stop1;
    private Button btn_date2;
    private View btn_Stop2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bounded_binder);
        printThreadInfo("Oncreate");


        new Thread(){
            @Override
            public void run() {
                super.run();
                printThreadInfo("New Thread");
                Intent intent = new Intent(BoundedBinderActivity.this, MyLocalServiceUsingBinder.class);
                bindService(intent, mConn, BIND_AUTO_CREATE);
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                super.run();
                printThreadInfo("New Thread");
                Intent intent = new Intent(BoundedBinderActivity.this, MyLocalServiceUsingBinder.class);
                bindService(intent, mConn2, BIND_AUTO_CREATE);
            }
        }.start();

        btn_date = findViewById(R.id.btn_date);
        btn_date2 = findViewById(R.id.btn_date2);
        btn_date2.setOnClickListener(view -> {
            mService2.getNumber();
//            btn_date2.setText("" + mService2.getNumber());
        });
        btn_date.setOnClickListener(view -> {
            printThreadInfo("Button");
            mService.getNumber();
//           btn_date.setText("" + mService.getNumber());
//            Toast.makeText(BoundedBinderActivity.this,mService.getNumber()+"",Toast.LENGTH_LONG).show();
        });
        btn_Stop1 = findViewById(R.id.btn_Stop1);
        btn_Stop1.setOnClickListener(view -> {
//            mService.unbindService(mConn);
            unbindService(mConn);
        });

        btn_Stop2 = findViewById(R.id.btn_Stop2);
        btn_Stop2.setOnClickListener(view -> {
            unbindService(mConn2);
        });

    }

    private void printThreadInfo(String caller) {
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        Log.d(TAG, "Caller: " + caller + " - Thread info: " + threadId + " - " + threadName );
    }


}
