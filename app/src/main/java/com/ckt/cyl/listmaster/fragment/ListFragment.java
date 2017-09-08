package com.ckt.cyl.listmaster.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.NewRecordActivity;
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

    private RecordLab recordLab;
    //阈值
    private int mScrollThreshold = 0;

    private NewRecordFragment newRecordFragment = null;

    public static ListFragment newInstance() {

        Bundle args = new Bundle();
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

        recordLab = RecordLab.get(getActivity());
        mRecords = recordLab.getmRecords();

        myAdapter = new MyAdapter(getActivity(), mRecords);
        //列表
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(myAdapter);
        binding.tvEmpty.setVisibility(View.GONE);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = NewRecordActivity.newIntent(getActivity());
                getActivity().startActivity(intent);
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


        mITCallback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT) {

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

                if (direction == ItemTouchHelper.RIGHT) {
                    //删除数据
                    recordLab.deleteRecord(mRecords.get(position));
                    myAdapter.notifyItemRemoved(position);
                    mRecords.remove(position);
                    if (mRecords.size() == 0) {
                        updateUI();
                    }
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    Log.d("swipe ---", dX + "---" + dY + "------" + actionState + "---" + isCurrentlyActive);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mITCallback);

        itemTouchHelper.attachToRecyclerView(binding.recyclerView);

        return binding.getRoot();
    }

    /**
     * 更新RecyclerView,显示最新状态
     */
    public void updateUI() {
        RecordLab recordLab = RecordLab.get(getActivity());
        mRecords = recordLab.getmRecords();

        if (mRecords.size() == 0) {
            binding.tvEmpty.setVisibility(View.VISIBLE);
        } else {
            binding.tvEmpty.setVisibility(View.GONE);
        }

        if (myAdapter == null) {
            myAdapter = new MyAdapter(getActivity(), mRecords);
            binding.recyclerView.setAdapter(myAdapter);
        } else {
            myAdapter.setmRecords(mRecords);
            myAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

}
