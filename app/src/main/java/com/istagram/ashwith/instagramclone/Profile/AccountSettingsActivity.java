package com.istagram.ashwith.instagramclone.Profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.istagram.ashwith.instagramclone.R;
import com.istagram.ashwith.instagramclone.uitls.BottomNavigationViewHelper;
import com.istagram.ashwith.instagramclone.uitls.SectonsStateeChaneAdapter;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class AccountSettingsActivity extends AppCompatActivity {


    private static final String TAG = "AccountSettingsActivity";
    private static final int ACTIVITY_NUMBER = 4;
    private Context context = AccountSettingsActivity.this;
    private ImageView backArrow;
    private SectonsStateeChaneAdapter pageAdapter;
    private ViewPager viewPager;
    private RelativeLayout mRelativeLayouot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_settings);
        backArrow = findViewById(R.id.ivBackArrow);
        viewPager = findViewById(R.id.vpPager);
        mRelativeLayouot = findViewById(R.id.rlLayout1);

        setupBottomNavigationView();
        setupSettingsList();
        setupFragments();

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupBottomNavigationView() {
        Log.d(TAG, "Setting up Bottom Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)
                findViewById(R.id.bnwBotomNavigationBar);
        BottomNavigationViewHelper.setupBpttpmNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigaton(context, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }

    private void setupSettingsList() {
        Log.d(TAG, "Setting up Settings list");

        ListView listView = findViewById(R.id.lvAccountSetting);

        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.edit_profile));
        options.add(getString(R.string.log_out));

        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, options);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setViewPager(position);
            }
        });

    }

    private void setupFragments() {
        pageAdapter = new SectonsStateeChaneAdapter(getSupportFragmentManager());
        pageAdapter.addFragment(new EditProfileFragment(), getString(R.string.edit_profile));
        pageAdapter.addFragment(new LogoutFragment(), getString(R.string.log_out));
    }

    private void setViewPager(int fragmentNumber) {
        mRelativeLayouot.setVisibility(View.GONE);
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(fragmentNumber);
    }
}
