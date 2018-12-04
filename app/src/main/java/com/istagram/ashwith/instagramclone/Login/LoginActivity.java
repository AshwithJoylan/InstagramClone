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
import com.google.firebase.auth.FirebaseUser;
import com.istagram.ashwith.instagramclone.Home.HomeActivity;
import com.istagram.ashwith.instagramclone.R;

public class LoginActivity extends AppCompatActivity {

    //firebase
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authStateListener;

    private Context context = LoginActivity.this;
    private static final String TAG = "LoginActivity";
    private EditText mEmail;
    private EditText mPassword;
    private LinearLayout loginProgress;
    private Button btnLogin;
    private TextView register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fireBaseSetup();

        checkCurrentUser();

        loginProgress = findViewById(R.id.loginProgress);
        loginProgress.setVerticalGravity(View.GONE);

        mEmail = findViewById(R.id.inputEmail);
        mPassword = findViewById(R.id.inputPassword);
        register = findViewById(R.id.tvSignUp);

        init();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, RegisterActivity.class));
            }
        });

        auth.signOut();

    }



    /* ------------------------FireBase-------------------------------
    */
    private void fireBaseSetup() {
        auth = FirebaseAuth.getInstance();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {

                } else {

                }
            }
        };
    }

    /**
     * initilization
     */
    private void init() {
        //login button Initilization

        btnLogin = findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString();
                final String password =mPassword.getText().toString();

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(context, "Enter the Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(context, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                loginProgress.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                loginProgress.setVisibility(View.GONE);

                                if (!task.isSuccessful()) {
                                    if (password.length() < 6) {
                                        mPassword.setError(getString(R.string.error_length_password));
                                    } else {
                                        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    FirebaseUser user = auth.getCurrentUser();
                                    try {
                                        Log.d(TAG, "Checking if email is vareified");
                                        if (user.isEmailVerified()) {
                                            startActivity(new Intent(context, HomeActivity.class));
                                            finish();
                                        } else {
                                            Toast.makeText(context, "Verify the email firset", Toast.LENGTH_SHORT).show();
                                            loginProgress.setVisibility(View.GONE);
                                            auth.signOut();
                                        }
                                    } catch (Exception e) {
                                        Log.e(TAG, "OnComplete:nullPointer exception");
                                    }
                                }
                            }
                        });


            }
        });
    }

    /*
                *check if user is loggeg in or not
    */


    private void checkCurrentUser() {
        if(auth.getCurrentUser() != null) {
            startActivity(new Intent(context, HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(authStateListener);
    }
}
