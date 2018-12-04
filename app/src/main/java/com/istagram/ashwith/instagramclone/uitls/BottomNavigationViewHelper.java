package com.istagram.ashwith.instagramclone.uitls;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.istagram.ashwith.instagramclone.Home.HomeActivity;
import com.istagram.ashwith.instagramclone.Likes.LikesActivity;
import com.istagram.ashwith.instagramclone.Profile.ProfileActivity;
import com.istagram.ashwith.instagramclone.R;
import com.istagram.ashwith.instagramclone.Search.SearchActivity;
import com.istagram.ashwith.instagramclone.Share.ShareActivity;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {
    private static final String TAG = "BottomNavigationViewHelper";

    public static void setupBpttpmNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.setTextVisibility(false);

    }

    public static void enableNavigaton(final Context context, BottomNavigationViewEx bottomNavigationViewEx) {
        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.ic_house:
                        context.startActivity(new Intent(context, HomeActivity.class));
                        break;
                    case R.id.ic_alert:
                        context.startActivity(new Intent(context, LikesActivity.class));
                        break;
                    case R.id.ic_android:
                        context.startActivity(new Intent(context, ProfileActivity.class));
                        break;
                    case R.id.ic_circle:
                        context.startActivity(new Intent(context, ShareActivity.class));
                        break;
                    case  R.id.ic_search:
                        context.startActivity(new Intent(context, SearchActivity.class));
                        break;
                }
                return false;
            }
        });

    }
}
