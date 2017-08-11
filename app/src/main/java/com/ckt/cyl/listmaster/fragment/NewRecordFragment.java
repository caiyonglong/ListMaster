package com.ckt.cyl.listmaster.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.transition.AutoTransition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ckt.cyl.listmaster.R;
import com.ckt.cyl.listmaster.Record;
import com.ckt.cyl.listmaster.databinding.FragmentNewRecordBinding;
import com.ckt.cyl.listmaster.db.RecordLab;

/**
 * Created by D22434 on 2017/8/3.
 */

public class NewRecordFragment extends BaseFragment {


    CallBacks mCallbacks;

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
        final FragmentNewRecordBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_new_record, container, false);

        setSharedElementEnterTransition(new AutoTransition());
        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = binding.etContent.getText().toString();

                Record record = new Record();
                record.setTitle(content);

                RecordLab recordLab = RecordLab.get(getActivity());
                recordLab.addRecord(record);
                mCallbacks.onRecordUpdated(record);
                Snackbar.make(v, content, Snackbar.LENGTH_SHORT).show();

            }
        });
        return binding.getRoot();
    }


}
