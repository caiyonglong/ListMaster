package com.ckt.cyl.listmaster;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.ckt.cyl.listmaster.databinding.ActivityDetailBinding;
import com.ckt.cyl.listmaster.fragment.NewRecordFragment;

import java.util.UUID;

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
        return NewRecordFragment.newInstance();
    }

    @Override
    protected void createView() {

    }

}
