package com.ckt.cyl.listmaster.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.databinding.FragmentRecordBinding;
import com.ckt.cyl.listmaster.db.RecordLab;
import com.ckt.cyl.listmaster.utils.TimeUtil;

import java.util.Date;

import static android.app.Activity.RESULT_OK;

/**
 * Created by D22434 on 2017/8/3.
 */

public class RecordFragment extends BaseFragment {

    private static String KEY = "record";
    private static final int REQUEST_DATE = 1;
    private static final int REQUEST_TIME = 2;
    FragmentRecordBinding mBinding;
    private Record record;

    public static RecordFragment newInstance(Record record) {

        Bundle args = new Bundle();
        args.putSerializable(KEY, record);
        RecordFragment fragment = new RecordFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_record, container, false);
        setHasOptionsMenu(true);
        record = (Record) getArguments().getSerializable(KEY);


        mBinding.title.setText(record.getTitle());
        mBinding.time.setText(TimeUtil.TimeFormat(record.getTime()));
        mBinding.date.setText(TimeUtil.TimeFormat(record.getDate()));

        mBinding.title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                record.setTitle(s.toString());
                updateRecord();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mBinding.itemDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(record.getDate().getTime() > record.getTime().getTime()
                                ? record.getDate() : record.getTime());
                dialog.setTargetFragment(RecordFragment.this, REQUEST_DATE);
                dialog.show(manager, "TAG_1");
            }
        });
        final String[] arr = new String[]{"永不", "每天", "每周", "每周工作日", "每月"};
        mBinding.itemRepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("重复")
                        .setSingleChoiceItems(arr, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                record.setMode(arr[which]);
                            }
                        })
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                updateRecord();
                                updateDate();
                            }
                        }).create();
                dialog.show();
            }
        });
        return mBinding.getRoot();
    }

    /**
     * 更新记录
     */
    private void updateRecord() {
        RecordLab.get(getActivity()).updateRecord(record);
    }

    private void updateDate() {
        //格式化日期
        mBinding.date.setText(TimeUtil.TimeFormat(record.getDate()) + " " + TimeUtil.HMFormat(record.getDate()));
        mBinding.time.setText(TimeUtil.TimeFormat(record.getTime()));
        mBinding.mode.setText(record.getMode());
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            record.setDate(date);
            updateRecord();
            updateDate();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_detail, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                RecordLab.get(getActivity()).deleteRecord(record);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
