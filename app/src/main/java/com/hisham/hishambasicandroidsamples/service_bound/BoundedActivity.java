package com.hisham.hishambasicandroidsamples.service_bound;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hisham.hishambasicandroidsamples.R;

public class BoundedActivity extends AppCompatActivity {

    private Button btn_date;
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyLocalService.LocalBinder binder = (MyLocalService.LocalBinder) iBinder;
            mService = binder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    private MyLocalService mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(BoundedActivity.this, MyLocalService.class);
        bindService(intent, mConn, BIND_AUTO_CREATE);
        btn_date = findViewById(R.id.btn_date);
        btn_date.setOnClickListener(view -> {
           btn_date.setText(mService.getNumber());
        });

    }
}
