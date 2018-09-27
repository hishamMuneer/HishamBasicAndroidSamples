package com.hisham.hishambasicandroidsamples;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hisham.hishambasicandroidsamples.handler.HandlerActivity;
import com.hisham.hishambasicandroidsamples.service_bound.BoundedBinderActivity;
import com.hisham.hishambasicandroidsamples.service_bound.BoundedMessengerActivity;

public class NavActivity extends AppCompatActivity {

    private Class[] activities = new Class[]{
            HandlerActivity.class,
            BoundedBinderActivity.class,
            BoundedMessengerActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        RecyclerView mRecyclerView = findViewById(R.id.rV);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);
        // use a linear layout manager
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        MyAdapter adapter = new MyAdapter(activities, (item, position) -> {
            String activityToStart = activities[position].getName();
            try {
                Class<?> c = Class.forName(activityToStart);
                NavActivity.this.startActivity(new Intent(NavActivity.this, c));
            } catch (ClassNotFoundException ignored) {
            }
        });
        mRecyclerView.setAdapter(adapter);

    }

    public interface OnItemClickListener {
        void onItemClick(View item, int position);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        private final Class[] mDataset;
        private final OnItemClickListener onItemClickListener;

        public MyAdapter(Class[] myDataset, OnItemClickListener listener) {
            mDataset = myDataset;
            onItemClickListener = listener;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {// create a new view
            TextView v = (TextView) LayoutInflater.from(viewGroup.getContext())
                    .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            myViewHolder.bind(mDataset[i], i, onItemClickListener);
        }

        @Override
        public int getItemCount() {
            return mDataset.length;
        }
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        // each data item is just a string in this case
        public TextView mTextView;

        MyViewHolder(@NonNull TextView itemView) {
            super(itemView);
            mTextView = itemView;
        }

        public void bind(Class s, int position, OnItemClickListener listener) {
            mTextView.setText(s.getSimpleName());
            mTextView.setOnClickListener(item -> listener.onItemClick(item, position));
        }
    }

}
