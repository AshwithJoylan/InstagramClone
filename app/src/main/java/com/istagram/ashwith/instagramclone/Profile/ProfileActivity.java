package com.istagram.ashwith.instagramclone.Profile;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.istagram.ashwith.instagramclone.R;
import com.istagram.ashwith.instagramclone.uitls.BottomNavigationViewHelper;
import com.istagram.ashwith.instagramclone.uitls.GridImageAdapter;
import com.istagram.ashwith.instagramclone.uitls.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "ProfileActivity";
    private static final int ACTIVITY_NUMBER = 4;
    private static final int NUM_GRID_COLUMN = 3;
    private Context context = ProfileActivity.this;
    private ProgressBar progressBar;
    private ImageView profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        progressBar = findViewById(R.id.pbActivityProgress);
        progressBar.setVisibility(View.GONE);

        profileImage = findViewById(R.id.profile_image);

        //setup Prpfile Photo
        setUpProfileImage();

        //setUpBottomNavigationView();
        setupToolbar();
        setUpBottomNavigationView();

        tempGridSettup();
    }

    private void tempGridSettup() {
        ArrayList<String> imgUlrs = new ArrayList<>();
        imgUlrs.add("encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT8QdBm8R5gAe4pN0yyGNn49tsK-ofR8soQDlejArGxLdev55pj");
        imgUlrs.add("images.unsplash.com/photo-1533143740700-51c42e3d3012?ixlib=rb-0.3.5&ixid=eyJhcHBfaWQiOjEyMDd9&s=14bc555778086e01f89a82a9b9543e77&w=1000&q=80");
        imgUlrs.add("encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSSNM6aQF4l2frLjgPW8tCjfX2lIibUA1XoqwEDuCbkaOtWW9sM4A");
        imgUlrs.add("c.tribune.com.pk/2017/12/1578082-mahirakhan_cover-1512626380-862-640x480.jpg");
        imgUlrs.add("encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSC1E6QjRb1BVwqg5uKmwQdHX7EBxwv2m-QntaJWMr_gyck5UojpQ");
        imgUlrs.add("encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkgUij-XCPrzKKUZ32_4mDbTlrwy46gl5tFyUBRaPiHudMgZMJ");
        imgUlrs.add("appdiya.com/wp-content/uploads/2018/06/1528048541_156_best-attitude-girls-images-for-dp-facebook.png");
        imgUlrs.add("www.menstylefashion.com/wp-content/uploads/2014/03/Jesus-Palacios-Spanish-Male-Model-2014-1.jpg");
        imgUlrs.add("www.dhresource.com/0x0s/f2-albu-g5-M01-AE-29-rBVaI1juz0qAK31lAAYis5cGe04916.jpg/beautiful-girl-g-zel-sanatlar-ya-l-boya-bask.jpg");
        imgUlrs.add("i.pinimg.com/236x/41/d8/b9/41d8b9f61e3183ba4e5f6204640d4b6e--the-beauty-most-beautiful.jpg");

        setupImageGrid(imgUlrs);
    }

    private  void setupImageGrid(ArrayList<String> images) {
        GridView gridView = findViewById(R.id.gvGridview);

        int gridwidth = getResources().getDisplayMetrics().widthPixels;
        int imageWidth = gridwidth/NUM_GRID_COLUMN;

        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(context, R.layout.layout_grid_imageview, "https://", images);
        gridView.setAdapter(adapter);
    }

    private void setUpProfileImage() {
        String imgUrl = "encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKaF_WA6q0c_1M5k6mbigbX7q9kzq-GItTXlfJzwwyVR9j9HJkw";
        UniversalImageLoader.setImage(imgUrl, profileImage, progressBar, "https://");
    }

    private void setupToolbar( ){
        Toolbar toolbar = findViewById(R.id.tbProfileToolbar);
        setSupportActionBar(toolbar);

        ImageView profileImage = findViewById(R.id.ivProfileMenu);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this, AccountSettingsActivity.class));
            }
        });
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
