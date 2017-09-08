package com.ckt.cyl.listmaster;

import android.databinding.DataBindingUtil;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.Toolbar;

import com.ckt.cyl.listmaster.databinding.ActivityMainBinding;
import com.ckt.cyl.listmaster.fragment.LifeFragment;
import com.ckt.cyl.listmaster.fragment.ListFragment;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends SingleFragmentActivity {

    ActivityMainBinding mBinding;

    @Override
    protected Fragment createFragment() {
        return ListFragment.newInstance();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createView() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutResId());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBinding.bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                FragmentManager fm = getSupportFragmentManager();
                Fragment fragment = fm.findFragmentById(R.id.fragment_container);
                if (tabId == R.id.tab_today) {
                    fragment = ListFragment.newInstance();
                } else if (tabId == R.id.tab_work) {
                    fragment = ListFragment.newInstance();
                } else if (tabId == R.id.tab_life) {

                    fragment = LifeFragment.newInstance();
                } else if (tabId == R.id.tab_other) {
                    fragment = LifeFragment.newInstance();
                }

                if (fragment != null)
                    fm.beginTransaction()
                            .replace(R.id.fragment_container, fragment,
                                    "fragment")
                            .commit();

            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}
