package com.ckt.cyl.listmaster;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.databinding.FragmentListMasterBinding;

/**
 * Created by D22434 on 2017/8/1.
 */


public class ListFragment extends Fragment {
    private static final String TAG = "ListFragment";
    private FragmentListMasterBinding binding;

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

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        return binding.getRoot();
    }


}
