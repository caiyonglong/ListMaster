package com.ckt.cyl.listmaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ckt.cyl.listmaster.fragment.LifeFragment;

public class DetailActivity extends SingleFragmentActivity {
    private static final String EXTRA_PARAM = "extra_param";

    public static Intent newIntent(Context packageContext, Bundle bundle) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        intent.putExtra(EXTRA_PARAM, bundle);
        return intent;
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, DetailActivity.class);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        return LifeFragment.newInstance();
    }

    @Override
    protected void createView() {

    }

}
