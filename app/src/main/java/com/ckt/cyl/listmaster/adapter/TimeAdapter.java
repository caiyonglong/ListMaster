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
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.LRecord;
import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.databinding.ListItemTimeBinding;

import java.util.List;

/**
 * Created by D22434 on 2017/8/2.
 */

public class TimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ATOM = 1;
    private static final int START = 2;
    private static final int END = 3;
    private static final int NORMAL = 4;

    private Context mContext;
    private List<LRecord> mLRecords;

    public TimeAdapter(Context context, List<LRecord> mLRecords) {
        mContext = context;
        this.mLRecords = mLRecords;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ListItemTimeBinding itemTimeBinding = DataBindingUtil
                .inflate(LayoutInflater.from(mContext), R.layout.list_item_time, parent, false);

        return new TimeHolder(itemTimeBinding, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TimeHolder) {
            TimeHolder mHolder = (TimeHolder) holder;
            mHolder.bind(mLRecords.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return mLRecords.size();
    }


    public class TimeHolder extends RecyclerView.ViewHolder {

        private ListItemTimeBinding mBinding;

        public TimeHolder(ListItemTimeBinding binding, int viewType) {
            super(binding.getRoot());
            mBinding = binding;
            if (viewType == ATOM) {
                mBinding.itemTimeLineMark.setBeginLine(null);
                mBinding.itemTimeLineMark.setEndLine(null);
            } else if (viewType == START) {
                mBinding.itemTimeLineMark.setBeginLine(null);
            } else if (viewType == END) {
                mBinding.itemTimeLineMark.setEndLine(null);
            }
        }

        public void bind(LRecord record) {
            mBinding.setLRecord(record);
        }
    }

    @Override
    public int getItemViewType(int position) {
        final int size = mLRecords.size() - 1;
        if (size == 0)
            return ATOM;
        else if (position == 0)
            return START;
        else if (position == size)
            return END;
        else return NORMAL;
    }
}
