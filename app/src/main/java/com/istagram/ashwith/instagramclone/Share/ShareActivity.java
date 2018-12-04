package com.istagram.ashwith.instagramclone.Share;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.istagram.ashwith.instagramclone.R;
import com.istagram.ashwith.instagramclone.uitls.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ShareActivity extends AppCompatActivity {

    private static final String TAG = "ShareActivity";
    private static final int ACTIVITY_NUMBER =  2;
    private Context context = ShareActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setUpBottomNavigationView();
    }

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
