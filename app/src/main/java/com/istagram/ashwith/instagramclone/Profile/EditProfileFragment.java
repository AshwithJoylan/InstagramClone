package com.istagram.ashwith.instagramclone.Profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.istagram.ashwith.instagramclone.R;
import com.istagram.ashwith.instagramclone.uitls.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfileFragment extends Fragment {

    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        imageView = view.findViewById(R.id.civProfileImage);
        setProfileImage();

        ImageView backArrow = view.findViewById(R.id.ivBackArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    private void setProfileImage() {
        String imgUrl = "encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwKaF_WA6q0c_1M5k6mbigbX7q9kzq-GItTXlfJzwwyVR9j9HJkw";
        UniversalImageLoader.setImage(imgUrl, imageView, null, "https://");
    }
}
