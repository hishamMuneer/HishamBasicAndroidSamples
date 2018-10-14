package com.hisham.hishambasicandroidsamples.customview.fanview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hisham.hishambasicandroidsamples.R;


public class FanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);

        DialView dialView = findViewById(R.id.dialView);
        dialView.setFanOffColor(Color.CYAN);
        dialView.setFanOnColor(Color.RED);
//        dialView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(FanActivity.this, "View ID: " + v.getId(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
