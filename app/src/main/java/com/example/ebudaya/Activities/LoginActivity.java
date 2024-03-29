package com.example.ebudaya.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ebudaya.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText userMail , password1;
    private Button btnLogin , btnLoginGoogle;
    private ProgressBar loginProgress , loginGoogleProgress;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;
    private ImageView loginPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userMail = findViewById(R.id.ETEmail);
        password1 = findViewById(R.id.ETPassword);
        btnLogin = findViewById(R.id.BTNLogin);
        btnLoginGoogle = findViewById(R.id.BTNLoginGoogle);
        loginProgress = findViewById(R.id.LoginProgressBar);
        loginGoogleProgress = findViewById(R.id.LoginGoogleProgressBar);
        mAuth = FirebaseAuth.getInstance();
        HomeActivity = new Intent(this,com.example.ebudaya.Activities.Home.class);
        //RegisterActivity = new Intent(this,com.example.ebudaya.Activities.RegisterActivity.class);
        loginPhoto = findViewById(R.id.IVProfilePic);
        loginPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerActivity = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(registerActivity);
                finish();
            }
        });

        loginProgress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgress.setVisibility(View.INVISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String usermail = userMail.getText().toString();
                final String password2 = password1.getText().toString();

                if (usermail.isEmpty() || password2.isEmpty()){
                    showMessage("Please verify all the field");
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
                else{
                    signIn(usermail,password2);
                }
            }
        });

    }

    private void signIn(String usermail, String password2) {
        
        mAuth.signInWithEmailAndPassword(usermail,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                
                if (task.isSuccessful()){
                    loginProgress.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else {
                    showMessage(task.getException().getMessage());
                    btnLogin.setVisibility(View.VISIBLE);
                    loginProgress.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void updateUI() {

        startActivity(HomeActivity);
        finish();

    }

    private void showMessage(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null) {
            updateUI();
        }
    }
}