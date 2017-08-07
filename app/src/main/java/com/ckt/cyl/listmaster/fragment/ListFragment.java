package com.ckt.cyl.listmaster.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.DetailActivity;
import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.adapter.MyAdapter;
import com.ckt.cyl.listmaster.databinding.FragmentListMasterBinding;
import com.ckt.cyl.listmaster.db.RecordLab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by D22434 on 2017/8/1.
 */


public class ListFragment extends Fragment {
    public static final String KEY_FRAGMENT = "ListFragment";
    private static final String TAG = "ListFragment";
    private FragmentListMasterBinding binding;
    private ItemTouchHelper.Callback mITCallback;
    //适配器
    private MyAdapter myAdapter;
    private List<Record> mRecords = new ArrayList<>();

    //阈值
    private int mScrollThreshold = 0;

    public static ListFragment newInstance(int key) {

        Bundle args = new Bundle();
        args.putInt(KEY_FRAGMENT, key);
        ListFragment fragment = new ListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_list_master, container, false);
        int ii = getArguments().getInt(KEY_FRAGMENT);

        RecordLab recordLab = RecordLab.get(getActivity());
        for (int i = 0; i < ii; i++) {
            Record record = new Record(i);
            mRecords.add(record);
            recordLab.addRecord(record);
        }
        myAdapter = new MyAdapter(getActivity(), mRecords);
        //列表
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(myAdapter);
        binding.tvEmpty.setVisibility(View.GONE);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = DetailActivity.newIntent(getActivity());
                startActivity(i);
            }
        });


        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

//                Log.e(TAG, "state = " + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isSignificantDelta = Math.abs(dy) > mScrollThreshold;
                if (isSignificantDelta) {
                    if (dy > 0) {
                        //向下
                        binding.fab.hide();
                    } else {
                        //向上
                        binding.fab.show();
                    }
                }
//                Log.e(TAG, "state = " + dx + "----" + dy);
            }
        });


        mITCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {


            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                //拖动功能，
                int fromx = viewHolder.getAdapterPosition();//初始时的位置
                int tox = target.getAdapterPosition();//拖动后的位置

                Collections.swap(mRecords, fromx, tox);
                myAdapter.notifyItemMoved(fromx, tox);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();


                Log.e(TAG, "direction = " + direction);

                if (direction == ItemTouchHelper.LEFT) {
                    myAdapter.notifyItemRemoved(position);

                } else {
                    //删除数据
                    mRecords.remove(position);
                    myAdapter.notifyItemRemoved(position);

                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mITCallback);

        itemTouchHelper.attachToRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }


}
