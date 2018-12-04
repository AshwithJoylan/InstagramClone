package com.istagram.ashwith.instagramclone.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.istagram.ashwith.instagramclone.R;
import com.istagram.ashwith.instagramclone.uitls.FireBaseMethods;

public class RegisterActivity extends AppCompatActivity {

    private Context context = RegisterActivity.this;

    //firebase
    private FirebaseAuth auth;
    private FireBaseMethods fireBaseMethods;


    private EditText mEmail;
    private EditText mPassword;
    private EditText mName;
    private Button btnRegister;
    private LinearLayout registrationProgress;
    private String name = "";
    private String email = "";
    private String password = "";
    private String TAG = "RegisterActivity";
    private String newName = "";
    private FirebaseAuth.AuthStateListener listener;
    private String userId = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        fireBaseMethods = new FireBaseMethods(this);
        Log.d(TAG, "" + auth);

        btnRegister = findViewById(R.id.btnRegister);
        //back to login textview
        TextView back = findViewById(R.id.tvBacktoLogin);
        registrationProgress = findViewById(R.id.registerProgress);
        registrationProgress.setVisibility(View.GONE);


        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "done");
                finish();
            }
        });

    }


    /* ------------------------FireBase-------------------------------
     */
    private void init() {
        //login button Initilization

        btnRegister = findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.i(TAG, "clicked button");
                mEmail = findViewById(R.id.regisiterEmail);
                mPassword = findViewById(R.id.registerPasseord);
                mName = findViewById(R.id.registerUserName);
                email = mEmail.getText().toString().trim();
                password = mPassword.getText().toString().trim();
                name = mName.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(context, "Enter the Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(name)) {
                    Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                registrationProgress.setVisibility(View.VISIBLE);
                fireBaseMethods.creatUser(email, password);
                fireBaseMethods.checkIfUserNameExists(name, email, "www.google.com");
                auth.signOut();
                registrationProgress.setVisibility(View.GONE);

                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


}
