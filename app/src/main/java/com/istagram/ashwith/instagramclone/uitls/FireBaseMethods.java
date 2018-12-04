package com.istagram.ashwith.instagramclone.uitls;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.istagram.ashwith.instagramclone.models.User;
import com.istagram.ashwith.instagramclone.models.UserAccountSettings;

public class FireBaseMethods {

    private FirebaseAuth mAuth;
    private FirebaseFirestore mDb;

    private Context context;
    private final String TAG = "FireBaseMethods";
    private String newName = "";
    private String userId;

    public FireBaseMethods(Context context) {
        mAuth = FirebaseAuth.getInstance();
        mDb = FirebaseFirestore.getInstance();

        this.context = context;
    }

    public void creatUser(String email, String password) {

        Log.d(TAG, "Creating User");
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "Registration complete");
                        if(task.isSuccessful()) {
                            Log.d(TAG, "Verification email genration");
                            sendVerificactionEmail();
                        } else {
                            Toast.makeText(context, "Failed to authenticate", Toast.LENGTH_SHORT).show();

                        }
                        return;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d(TAG, "Registration Unsuccessfull!");
                        return;
                    }
                });
    }

    /**
     * add user to the data base
     * @param email
     * @param name
     * @param website
     */

    public void addUserToDatabase(String email, String name, String website) {

        Log.d(TAG, "Adding user to databse");
        userId = mAuth.getCurrentUser().getUid();

        Log.d(TAG, "New User is" + email+ name + website);

        User user = new User(userId, name, email, 1 );

        mDb.collection("users")
                .document(userId)
                .set(user)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "User added Successfully");
                    }
                });

        UserAccountSettings settings = new UserAccountSettings("I live in ", 0, 0, 0, name, "", name,"www.google.com");
        Log.d(TAG, "Adding uset Account Settings");
        mDb.collection("user_account_settings")
                .document(userId)
                .set(settings)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d(TAG, "Settings added succefully");
                    }
                });
    }

    /**
     * check if the user exists
     * @param name
     * @param email
     * @param website
     */
    public void checkIfUserNameExists(final String name, final String email, final String website) {

        newName = name;

        CollectionReference cRef = mDb.collection("users");

        cRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {
                    Log.d(TAG, "Task checking Succesfull");
                    for (DocumentSnapshot ds : task.getResult()) {
                        String userName = ds.getString("user_name");
                        Log.d(TAG, " +ssssssssss "+ userName);
                        if (userName.equals(name)) {
                            Log.d(TAG, "User name already exists in the database");
                            Toast.makeText(context, "User Name Already Exists! Appending random charectors", Toast.LENGTH_SHORT).show();
                            //newName = append(name);

                            Log.d(TAG, "Appending string");
                            String apnd = mAuth.getCurrentUser().getUid().substring(2, 8);
                            Log.d(TAG, "getting Substrin uid" + apnd);
                            newName = name + apnd;
                            Log.d(TAG, "nwe user name is" + newName);
                            break;
                        } else
                            Log.d(TAG, "User name does not exists");
                    }
                }
                else
                    Log.d(TAG, "Error getting docyuments");

                Log.d(TAG, "new name is " + newName);

                addUserToDatabase(email, newName, website);
            }
        });

    }

    public void sendVerificactionEmail() {
        Log.d(TAG, "Sending verificatio email to");
        FirebaseUser user = mAuth.getCurrentUser();
        
        if(user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()) {
                                Log.d(TAG, "Verificatin email sent");
                            }else {
                                Toast.makeText(context, "Couldn't send verification email", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    /**
     * Appending string if username exists inn the data base
     * @param name
     * @return
     */
    private String append(String name) {
        Log.d(TAG, "Appending string");
        String apnd = mAuth.getCurrentUser().getUid().substring(2, 6);
        Log.d(TAG, "getting Substrin uid" + apnd);
        name += apnd;
        return name;
    }
}
