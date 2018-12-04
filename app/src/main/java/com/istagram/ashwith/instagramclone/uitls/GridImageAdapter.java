package com.istagram.ashwith.instagramclone.uitls;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.istagram.ashwith.instagramclone.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.util.ArrayList;
import java.util.List;

public class GridImageAdapter extends ArrayAdapter<String> {

    private Context context;
    private LayoutInflater mInflater;
    private int layoutResourse;
    private String mAppend;
    private ArrayList<String> imgUlrs;

    public GridImageAdapter(Context context, int layoutResourse, String mAppend, ArrayList<String> imgUlrs) {
        super(context, layoutResourse, imgUlrs);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.mAppend = mAppend;
        this.imgUlrs = imgUlrs;
        this.context = context;
        this.layoutResourse = layoutResourse;
    }

    private static class ViewHolder {
        SquareImageView profileImage;
        ProgressBar progressBar;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder viewHolder;

        if (convertView == null) {
            convertView = mInflater.inflate(layoutResourse, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.progressBar =  convertView.findViewById(R.id.gridImageProgressBar);
            viewHolder.profileImage = (SquareImageView) convertView.findViewById(R.id.gridImageView);
            convertView.setTag(viewHolder);
        } else  {
                viewHolder = (ViewHolder) convertView.getTag();

        }

        String imaulr = getItem(position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.displayImage(mAppend + imaulr, viewHolder.profileImage, new ImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {
                if(viewHolder.progressBar != null)
                    viewHolder.progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                if(viewHolder.progressBar != null)
                    viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                if(viewHolder.progressBar != null)
                    viewHolder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {
                if(viewHolder.progressBar != null)
                    viewHolder.progressBar.setVisibility(View.GONE);
            }
        });


        return convertView;

    }
}
