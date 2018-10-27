package com.hisham.hishambasicandroidsamples.job_scheduler;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.hisham.hishambasicandroidsamples.R;

public class ReceiverInvokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver_invoke);
        findViewById(R.id.btnRegister).setOnClickListener(v -> {
            Intent intent = new Intent("Custom");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        });
    }

    private BroadcastReceiver broadcastReceiver = new ChargingReceiver();

    @Override
    protected void onStart() {
        super.onStart();
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("Custom"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
