/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ckt.cyl.listmaster.adapter;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.databinding.ListItemRecordBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D22434 on 2017/8/2.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context mContext;
    private List<Record> mRecords = new ArrayList<>();


    public MyAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 10; i++) {
            Record record = new Record(i);
            mRecords.add(record);
        }
    }

    public void setmRecords(List<Record> mRecords) {
        this.mRecords = mRecords;
    }

    public MyAdapter(Context context, List<Record> records) {
        mContext = context;
        mRecords = records;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ListItemRecordBinding itemRecordBinding = DataBindingUtil
                .inflate(LayoutInflater.from(mContext), R.layout.list_item_record, parent, false);

        return new MyViewHolder(itemRecordBinding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Record record = mRecords.get(position);
        holder.bind(record);

    }

    @Override
    public int getItemCount() {
        return mRecords.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private ListItemRecordBinding mBinding;

        public MyViewHolder(ListItemRecordBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(Record record) {
            mBinding.setRecord(record);

            mBinding.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                }
            });


        }
    }
}
