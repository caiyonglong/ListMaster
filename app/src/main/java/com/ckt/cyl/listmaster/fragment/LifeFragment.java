package com.ckt.cyl.listmaster.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.LRecord;
import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.adapter.TimeAdapter;
import com.ckt.cyl.listmaster.databinding.FragmentListMasterBinding;
import com.ckt.cyl.listmaster.db.RecordLab;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by D22434 on 2017/8/1.
 */


public class LifeFragment extends Fragment {
    public static final String KEY_FRAGMENT = "ListFragment";
    private static final String TAG = "ListFragment";
    private FragmentListMasterBinding binding;
    private ItemTouchHelper.Callback mITCallback;
    //适配器
    private TimeAdapter timeAdapter;
    private List<LRecord> mRecords = new ArrayList<>();

    //阈值
    private int mScrollThreshold = 0;

    public static LifeFragment newInstance() {

        Bundle args = new Bundle();
        LifeFragment fragment = new LifeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    //
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil
                .inflate(inflater, R.layout.fragment_list_master, container, false);

        RecordLab recordLab = RecordLab.get(getActivity());
        mRecords = recordLab.getmLRecords();

        timeAdapter = new TimeAdapter(getActivity(), mRecords);
        //列表
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(timeAdapter);
        binding.tvEmpty.setVisibility(View.GONE);

        binding.fab.setVisibility(View.GONE);


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
