package com.ckt.cyl.listmaster.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.databinding.FragmentNewRecordBinding;

/**
 * Created by D22434 on 2017/8/3.
 */

public class NewRecordFragment extends BaseFragment {

    public static NewRecordFragment newInstance() {

        Bundle args = new Bundle();

        NewRecordFragment fragment = new NewRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentNewRecordBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_new_record, container, false);

        AppCompatActivity mAppCompatActivity = (AppCompatActivity) getActivity();
        mAppCompatActivity.setSupportActionBar(binding.toolbar);
        ActionBar actionBar = mAppCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("新增");
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(true);
        }
        return binding.getRoot();
    }


}
