/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ckt.cyl.listmaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import com.ckt.cyl.listmaster.DetailActivity;
import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.databinding.ListItemKindBinding;
import com.ckt.cyl.listmaster.databinding.ListItemRecordBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D22434 on 2017/8/2.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<Record> mRecords = new ArrayList<>();


    public MyAdapter(Context context) {
        mContext = context;
        for (int i = 0; i < 10; i++) {
            Record record = new Record(i);
            mRecords.add(record);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(mRecords.get(position).getTag());
    }


    public void setmRecords(List<Record> mRecords) {
        this.mRecords = mRecords;
    }

    public MyAdapter(Context context, List<Record> records) {
        mContext = context;
        mRecords = records;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 1) {
            ListItemRecordBinding itemRecordBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(mContext), R.layout.list_item_record, parent, false);

            return new MyViewHolder(itemRecordBinding);
        } else {
            ListItemKindBinding itemKindBinding = DataBindingUtil
                    .inflate(LayoutInflater.from(mContext), R.layout.list_item_kind, parent, false);
            return new TypeHolder(itemKindBinding);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            MyViewHolder mHolder = (MyViewHolder) holder;
            final Record record = mRecords.get(position);
            mHolder.bind(record);
            mHolder.mBinding.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("record", record);
                    Intent it = DetailActivity.newIntent(mContext, bundle);
                    mContext.startActivity(it);
                }
            });
        } else if (holder instanceof TypeHolder) {

        }


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
        }
    }

    public class TypeHolder extends RecyclerView.ViewHolder {

        private ListItemKindBinding mBinding;

        public TypeHolder(ListItemKindBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

    }
}
