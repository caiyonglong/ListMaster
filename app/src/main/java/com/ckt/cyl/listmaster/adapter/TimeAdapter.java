/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.ckt.cyl.listmaster.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.databinding.ListItemTimeBinding;

import java.util.List;

/**
 * Created by D22434 on 2017/8/2.
 */

public class TimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;

    public TimeAdapter(Context context, List<Record> records) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListItemTimeBinding itemTimeBinding = DataBindingUtil
                .inflate(LayoutInflater.from(mContext), R.layout.list_item_time, parent, false);

        return new TimeHolder(itemTimeBinding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeHolder) {
            TimeHolder mHolder = (TimeHolder) holder;
            if (position == 0) {
                mHolder.mBinding.startLine.setVisibility(View.GONE);
            } else if (position == 20 - 1) {
                mHolder.mBinding.endLine.setVisibility(View.GONE);
            } else {
                mHolder.mBinding.startLine.setVisibility(View.VISIBLE);
                mHolder.mBinding.endLine.setVisibility(View.VISIBLE);
            }

        }

    }

    @Override
    public int getItemCount() {
        return 20;
    }


    public class TimeHolder extends RecyclerView.ViewHolder {

        private ListItemTimeBinding mBinding;

        public TimeHolder(ListItemTimeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

    }
}
