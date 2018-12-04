package com.istagram.ashwith.instagramclone.Home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.istagram.ashwith.instagramclone.R;
import com.istagram.ashwith.instagramclone.uitls.BottomNavigationViewHelper;
import com.istagram.ashwith.instagramclone.uitls.SectionsPagerAdapter;
import com.istagram.ashwith.instagramclone.uitls.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private Context context = HomeActivity.this;
    private static final  int ACTIVITY_NUMBER = 0;
    //firebae
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initImageLoader();
        setUpBottomNavigationView();
        setupViewPager();


    }

    private void setupFireBase() {
        auth = FirebaseAuth.getInstance();
    }

    //ImageLoadeer
    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(context);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    //adding 3 tab fragments
    public void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragments());
        adapter.addFragment(new HomeFragments());
        adapter.addFragment(new MessagesFragments());
        ViewPager viewPager = (ViewPager) findViewById(R.id.vpPager);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tlTabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_logo);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
    }

    //Bottom navigatio view setup
    private void setUpBottomNavigationView() {
        Log.d(TAG, "Setting up Bottom Navigation View");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx)
                findViewById(R.id.bnwBotomNavigationBar);
        BottomNavigationViewHelper.setupBpttpmNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigaton(context, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUMBER);
        menuItem.setChecked(true);
    }
}
