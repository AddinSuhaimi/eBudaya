package com.example.ebudaya.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.ebudaya.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText userName , password1;
    private Button btnLogin , btnLoginGoogle;
    private ProgressBar loginProgress , loginGoogleProgress;
    private FirebaseAuth mAuth;
    private Intent HomeActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userName = findViewById(R.id.ETUsername);
        password1 = findViewById(R.id.ETPassword);
        btnLogin = findViewById(R.id.BTNLogin);
        btnLoginGoogle = findViewById(R.id.BTNLoginGoogle);
        loginProgress = findViewById(R.id.LoginProgressBar);
        loginGoogleProgress = findViewById(R.id.LoginGoogleProgressBar);
        mAuth = FirebaseAuth.getInstance();
        // Either Home(this has navigation drawer) or HomeActivity
        HomeActivity = new Intent(this,com.example.ebudaya.Activities.Home.class);

        loginProgress.setVisibility(View.INVISIBLE);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginProgress.setVisibility(View.INVISIBLE);
                btnLogin.setVisibility(View.INVISIBLE);

                final String username = userName.getText().toString();
                final String password2 = password1.getText().toString();

                if (username.isEmpty() || password2.isEmpty()){
                    showMessage("Please verify all the field");
                }
                else{
                    signIn(username,password2);
                }
            }
        });

    }

    private void signIn(String username, String password2) {
        
        mAuth.signInWithEmailAndPassword(username,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                
                if (task.isSuccessful()){
                    loginProgress.setVisibility(View.INVISIBLE);
                    btnLogin.setVisibility(View.VISIBLE);
                    updateUI();
                }
                else
                    showMessage(task.getException().getMessage());
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
}