package com.ckt.cyl.listmaster;

import android.databinding.DataBindingUtil;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ckt.cyl.listmaster.databinding.ActivityMainBinding;
import com.ckt.cyl.listmaster.fragment.LifeFragment;
import com.ckt.cyl.listmaster.fragment.ListFragment;
import com.ckt.cyl.listmaster.fragment.NewRecordFragment;

public class MainActivity extends SingleFragmentActivity
        implements NavigationView.OnNavigationItemSelectedListener, NewRecordFragment.CallBacks {

    ActivityMainBinding mBinding;

    @Override
    protected Fragment createFragment() {
        return ListFragment.newInstance(1);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void createView() {
        mBinding = DataBindingUtil.setContentView(this, getLayoutResId());

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragment_bottom, NewRecordFragment.newInstance())
                .commit();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.nav_today:
                fragment = ListFragment.newInstance(2);
                break;
            case R.id.nav_chart:
                fragment = ListFragment.newInstance(5);
                break;
            case R.id.nav_add:
                fragment = ListFragment.newInstance(8);
                break;
            case R.id.nav_manage:
                fragment = ListFragment.newInstance(12);
                break;
            case R.id.nav_live:
                fragment = LifeFragment.newInstance();
                break;
            case R.id.nav_settings:
                fragment = ListFragment.newInstance(20);
                break;
        }

        Log.e("----------------------", item.getTitle().toString());
        if (fragment != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fragment, item.getTitle().toString())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRecordUpdated(Record record) {

        ListFragment listFragment = (ListFragment) getSupportFragmentManager()
                .findFragmentByTag("今天");
        if (listFragment != null) {
            listFragment.updateUI();
        }
    }
}
