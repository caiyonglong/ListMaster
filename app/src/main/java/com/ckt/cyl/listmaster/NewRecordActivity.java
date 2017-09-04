package com.ckt.cyl.listmaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ckt.cyl.listmaster.fragment.NewRecordFragment;

/**
 * Created by D22434 on 2017/9/4.
 */

public class NewRecordActivity extends SingleFragmentActivity {
    private static final String EXTRA_PARAM = "extra_param";

    public static Intent newIntent(Context packageContext, Bundle bundle) {
        Intent intent = new Intent(packageContext, NewRecordActivity.class);
        intent.putExtra(EXTRA_PARAM, bundle);
        return intent;
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, NewRecordActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return NewRecordFragment.newInstance();
    }

    @Override
    protected void createView() {

    }
}