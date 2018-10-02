package com.hisham.hishamclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hisham.hishambasicandroidsamples.IRemoteService;
import com.hisham.hishambasicandroidsamples.R;

public class ClientMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_main);

        ComponentName name = new ComponentName("com.hisham.hishambasicandroidsamples",
                "com.hisham.hishambasicandroidsamples.service_bound.MyLocalServiceUsingAIDL");

        Intent intent = new Intent();
        intent.setComponent(name);

        bindService(intent, mConn, BIND_AUTO_CREATE);


        final Button tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                int finalPid = service.getPid();
                    long finalThreadID = service.getThreadId();
                    tv.setText("Process ID: " + finalPid + " -- Thread: " + finalThreadID);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private IRemoteService service;

    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IRemoteService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(Utils.TAG, "onServiceDisconnected: " + componentName.flattenToShortString());
        }
    };

}
