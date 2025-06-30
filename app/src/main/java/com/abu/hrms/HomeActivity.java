package com.abu.hrms;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private String mUsername;
    public static final String ANONYMOUS = "anonymous";
    public static final int RC_SIGN_IN = 1;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private CardView yellow, red, blue;
    DBhandler dBhandler;

    public HomeActivity() {
        mUsername = ANONYMOUS;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dBhandler = new DBhandler(this, null, null, 1);
        login();
        setUpButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            AuthUI.getInstance().signOut(HomeActivity.this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            dBhandler.deleteAll();
                        }
                    });
        }

        return super.onOptionsItemSelected(item);
    }

    private void login() {
        mUsername = ANONYMOUS;
        mFirebaseAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    onSignedInInitialize(user.getDisplayName());
                } else {
                    onSignedOutCleanup();
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(Arrays.asList(
                                            new AuthUI.IdpConfig.GoogleBuilder().build(),
                                            new AuthUI.IdpConfig.PhoneBuilder().build()
                                    ))
                                    .setLogo(R.mipmap.cloral_logo)
                                    .setIsSmartLockEnabled(false)  // You can try disabling Smart Lock if it's causing issues
                                    .build(),
                            RC_SIGN_IN);
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user != null && user.getDisplayName() != null) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
                }
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign In Cancelled!", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        yellow.setCardBackgroundColor(getResources().getColor(R.color.yellow));
        red.setCardBackgroundColor(getResources().getColor(R.color.red));
        blue.setCardBackgroundColor(getResources().getColor(R.color.blue));
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    private void onSignedInInitialize(String username) {
        mUsername = username;
    }

    private void onSignedOutCleanup() {
        mUsername = ANONYMOUS;
    }

    private void setUpButtons() {
        yellow = findViewById(R.id.yellow);
        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);

        yellow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yellow.setCardBackgroundColor(getResources().getColor(R.color.yellowDark));
                startActivity(new Intent(HomeActivity.this, NewCandidateActivity.class));
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                red.setCardBackgroundColor(getResources().getColor(R.color.redDark));
                startActivity(new Intent(HomeActivity.this, AllApplicationsActivity.class));
            }
        });

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blue.setCardBackgroundColor(getResources().getColor(R.color.blueDark));
                startActivity(new Intent(HomeActivity.this, SelectedCandidatesActivity.class));
            }
        });
    }
}