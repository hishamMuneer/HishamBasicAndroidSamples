package com.hisham.hishambasicandroidsamples.service_bound;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hisham.hishambasicandroidsamples.R;

public class BoundedMessengerActivity extends AppCompatActivity {
    private static final String TAG = "HishamSample";
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            messenger = new Messenger(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: " + componentName.flattenToShortString());
        }
    };
    private Button btn_date;
    private Messenger messenger;
    private TextView tv;
    private EditText et;

    // This class handles the Service response
    class ResponseHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            int respCode = msg.what;

            switch (respCode) {
                case MyLocalServiceUsingMessenger.TO_UPPER_CASE_RESPONSE: {
                    String result = msg.getData().getString("respData");
                    tv.setText(result);
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bounded_messenger);
        printThreadInfo("New Thread");
        Intent intent = new Intent(BoundedMessengerActivity.this, MyLocalServiceUsingMessenger.class);
        bindService(intent, mConn, BIND_AUTO_CREATE);


        btn_date = findViewById(R.id.btn_date);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        btn_date.setOnClickListener(view -> {
            printThreadInfo("Button");

            String val = et.getText().toString();
            Message msg = Message.obtain(null, MyLocalServiceUsingMessenger.TO_UPPER_CASE);
            msg.replyTo = new Messenger(new ResponseHandler());
            // We pass the value
            Bundle b = new Bundle();
            b.putString("data", val);
            msg.setData(b);

            try {
                messenger.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }


            //btn_date.setText("" + messenger.getNumber());
//            Toast.makeText(BoundedBinderActivity.this,mService.getNumber()+"",Toast.LENGTH_LONG).show();
        });
    }

    private void printThreadInfo(String caller) {
        long threadId = Thread.currentThread().getId();
        String threadName = Thread.currentThread().getName();
        Log.d(TAG, "Caller: " + caller + " - Thread info: " + threadId + " - " + threadName);
    }


}
