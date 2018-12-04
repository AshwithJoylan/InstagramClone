package com.istagram.ashwith.instagramclone.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.istagram.ashwith.instagramclone.Login.LoginActivity;
import com.istagram.ashwith.instagramclone.R;

public class LogoutFragment extends Fragment {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;

    private final String TAG = "LogoutFragment";
    private Button btnConfirmSignout;
    private LinearLayout signOutProgress;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        View view = inflater.inflate(R.layout.fragment_sign_out, container, false);


        btnConfirmSignout = view.findViewById(R.id.btnConfirmSignOut);
        signOutProgress = view.findViewById(R.id.SinoutProgress);
        signOutProgress.setVisibility(View.GONE);

        btnConfirmSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Trying to sign out");

                signOutProgress.setVisibility(View.VISIBLE);
                auth.signOut();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
        setupFirebase();

        return view;
    }

    private void setupFirebase() {
        auth = FirebaseAuth.getInstance();

        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if(user != null) {

                } else {
                    Log.d(TAG, "Signed out");
                }
            }
        });
    }
}
