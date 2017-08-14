package com.ckt.cyl.listmaster.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.utils.TimeUtil;

import java.util.Date;
import java.util.GregorianCalendar;

import static android.app.Activity.RESULT_OK;

/**
 * Created by D22434 on 2017/7/24.
 * 创建DatePickerFragment 代替 AlterDialog
 */

public class DatePickerFragment extends DialogFragment {

    public static final String EXTRA_DATE = "com.ckt.cyl.listmaster.fragment.date";

    private static final String ARG_DATE = "date";
    private CalendarView mCalendar;
    private TextView mTextView;
    private Date mDate;

    //获取传递的crime日期
    public static DatePickerFragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mDate = (Date) getArguments().getSerializable(ARG_DATE);


        //加载布局
        View v = LayoutInflater.from(getActivity())
                .inflate(R.layout.dialog_date, null);

        mCalendar = (CalendarView) v.findViewById(R.id.dialog_date_picker);
        mTextView = (TextView) v.findViewById(R.id.time_picker);
        updateUI();
        mCalendar.setDate(mDate.getTime());

        mCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mDate = new GregorianCalendar(year, month, dayOfMonth).getTime();
                updateUI();
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                //传递数据
                TimePickerFragment dialog = TimePickerFragment
                        .newInstance(mDate);
                dialog.setTargetFragment(DatePickerFragment.this, 1);
                dialog.show(manager, "DatePicker");
            }
        });


        return new AlertDialog.Builder(getActivity())
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                sendResult(RESULT_OK, mDate);
                            }
                        })
                .create();
    }

    /**
     * 更新UI
     *
     */
    private void updateUI() {
        mTextView.setText(TimeUtil.TimeFormat(mDate) + " " + TimeUtil.HMFormat(mDate));
    }

    //发送日期
    private void sendResult(int resultCode, Date date) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), resultCode, intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            mDate = (Date) data
                    .getSerializableExtra(TimePickerFragment.EXTRA_TIME);
            updateUI();
        }
    }
}
