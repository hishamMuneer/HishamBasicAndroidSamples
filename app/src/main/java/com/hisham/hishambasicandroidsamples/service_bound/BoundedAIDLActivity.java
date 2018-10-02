package com.hisham.hishambasicandroidsamples.service_bound;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hisham.hishambasicandroidsamples.IRemoteService;
import com.hisham.hishambasicandroidsamples.R;

import static com.hisham.hishambasicandroidsamples.util.Utils.printThreadInfo;

public class BoundedAIDLActivity extends AppCompatActivity {
    private static final String TAG = "HishamSample";
    private ServiceConnection mConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            service = IRemoteService.Stub.asInterface(iBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: " + componentName.flattenToShortString());
        }
    };
    private Button btn_date;
    private IRemoteService service;
    private TextView tv;
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bounded_messenger);
        new Thread() {
            @Override
            public void run() {
                super.run();

                printThreadInfo("New Thread");
                Intent intent = new Intent(BoundedAIDLActivity.this, MyLocalServiceUsingAIDL.class);
                bindService(intent, mConn, BIND_AUTO_CREATE);

            }
        }.start();

        btn_date = findViewById(R.id.btn_date);
        tv = findViewById(R.id.tv);
        et = findViewById(R.id.et);
        btn_date.setOnClickListener(view -> {
            printThreadInfo("Button");

            tv.setText(tv.getText().toString() + System.getProperty("line.separator"));
            new Thread() {
                @Override
                public void run() {
                    printThreadInfo("T: ");
                    super.run();
                    int pid = 0;
                    long threadID = 0;
                    try {
                        pid = service.getPid();
                        threadID = service.getThreadId();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    int finalPid = pid;
                    long finalThreadID = threadID;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                tv.setText("Process ID: " + finalPid + "Thread: " + finalThreadID + " - " + service.getThreadName() + " Counter: " + service.getVariable() +  System.getProperty("line.separator"));
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }.start();


            new Thread() {
                @Override
                public void run() {
                    printThreadInfo("T: ");
                    super.run();
                    int pid = 0;
                    long threadID = 0;
                    try {
                        pid = service.getPid();
                        threadID = service.getThreadId();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    int finalPid = pid;
                    long finalThreadID = threadID;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tv.setText(tv.getText().toString() +  " ---- Process ID: " + finalPid + " -- Thread: " + finalThreadID);
                        }
                    });
                }
            }.start();


            new Thread() {
                @Override
                public void run() {
                    printThreadInfo("T: ");
                    super.run();
                    int pid = 0;
                    long threadID = 0;
                    try {
                        pid = service.getPid();
                        threadID = service.getThreadId();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    int finalPid = pid;
                    long finalThreadID = threadID;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tv.setText(tv.getText().toString() +  " ---- Process ID: " + finalPid + " -- Thread: " + finalThreadID);
                        }
                    });
                }
            }.start();


            new Thread() {
                @Override
                public void run() {
                    printThreadInfo("T: ");
                    super.run();
                    int pid = 0;
                    long threadID = 0;
                    try {
                        pid = service.getPid();
                        threadID = service.getThreadId();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    int finalPid = pid;
                    long finalThreadID = threadID;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tv.setText(tv.getText().toString() +  " ---- Process ID: " + finalPid + " -- Thread: " + finalThreadID);
                        }
                    });
                }
            }.start();


            new Thread() {
                @Override
                public void run() {
                    printThreadInfo("T: ");
                    super.run();
                    int pid = 0;
                    long threadID = 0;
                    try {
                        pid = service.getPid();
                        threadID = service.getThreadId();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    int finalPid = pid;
                    long finalThreadID = threadID;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tv.setText(tv.getText().toString() +  " ---- Process ID: " + finalPid + " -- Thread: " + finalThreadID);
                        }
                    });
                }
            }.start();


            new Thread() {
                @Override
                public void run() {
                    printThreadInfo("T: ");
                    super.run();
                    int pid = 0;
                    long threadID = 0;
                    try {
                        pid = service.getPid();
                        threadID = service.getThreadId();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    int finalPid = pid;
                    long finalThreadID = threadID;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tv.setText(tv.getText().toString() +  " ---- Process ID: " + finalPid + " -- Thread: " + finalThreadID);
                        }
                    });
                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    printThreadInfo("T: ");
                    super.run();
                    int pid = 0;
                    long threadID = 0;
                    try {
                        pid = service.getPid();
                        threadID = service.getThreadId();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    int finalPid = pid;
                    long finalThreadID = threadID;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            tv.setText(tv.getText().toString() +  " ---- Process ID: " + finalPid + " -- Thread: " + finalThreadID);
                        }
                    });
                }
            }.start();


            //btn_date.setText("" + messenger.getNumber());
//            Toast.makeText(BoundedBinderActivity.this,mService.getNumber()+"",Toast.LENGTH_LONG).show();
        });
    }


}
