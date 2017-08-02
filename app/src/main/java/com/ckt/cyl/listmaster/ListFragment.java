package com.ckt.cyl.listmaster;

import android.databinding.DataBindingUtil;
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

import com.ckt.cyl.listmaster.adapter.MyAdapter;
import com.ckt.cyl.listmaster.databinding.FragmentListMasterBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by D22434 on 2017/8/1.
 */


public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private FragmentListMasterBinding binding;
    private ItemTouchHelper.Callback mITCallback;
    //适配器
    private MyAdapter myAdapter;
    private List<Record> mRecords = new ArrayList<>();

    //阈值
    private int mScrollThreshold = 0;

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

        for (int i = 0; i < 20; i++) {
            Record record = new Record(i);
            mRecords.add(record);
        }
        myAdapter = new MyAdapter(getActivity(), mRecords);
        //列表
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(myAdapter);
        binding.tvEmpty.setVisibility(View.GONE);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(mITCallback);

        itemTouchHelper.attachToRecyclerView(binding.recyclerView);


        return binding.getRoot();
    }

    /**
     * 更新RecyclerView,显示最新状态
     */
    public void updateUI() {


        if (myAdapter == null) {
            myAdapter = new MyAdapter(getActivity(), mRecords);
            binding.recyclerView.setAdapter(myAdapter);
        } else {
            myAdapter.setmRecords(mRecords);
            myAdapter.notifyDataSetChanged();
//            mAdapter.notifyItemChanged(positionClicked);
        }

    }

}
