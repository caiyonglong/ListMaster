package com.ckt.cyl.listmaster.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.transition.AutoTransition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.databinding.FragmentNewRecordBinding;
import com.ckt.cyl.listmaster.db.RecordLab;

/**
 * Created by D22434 on 2017/8/3.
 */

public class NewRecordFragment extends BaseFragment {


    CallBacks mCallbacks;
    FragmentNewRecordBinding mBinding;

    public interface CallBacks {
        void onRecordUpdated(Record record);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallbacks = (CallBacks) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }


    public static NewRecordFragment newInstance() {

        Bundle args = new Bundle();

        NewRecordFragment fragment = new NewRecordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_new_record, container, false);


//
        setSharedElementEnterTransition(new AutoTransition());
        mBinding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mBinding.etContent.getText().toString();

                Record record = new Record();
                record.setTitle(content);

                RecordLab recordLab = RecordLab.get(getActivity());
                recordLab.addRecord(record);
                Snackbar.make(v, "新增  " + content, Snackbar.LENGTH_SHORT).show();
                mBinding.etContent.setText("");
                mCallbacks.onRecordUpdated(record);
                hideInput(v);
                onBackPressed();

            }
        });
        mBinding.newContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInput(v);
            }
        });


        return mBinding.getRoot();
    }


    /**
     * 隐藏输入法
     *
     * @param view
     */
    private void hideInput(View view) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 返回键
     */
    private void onBackPressed() {
        getActivity().onBackPressed();
    }
}
