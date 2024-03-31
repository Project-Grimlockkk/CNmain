
package com.example.rr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learn.R;
import com.example.learn.databinding.ActivityLoginScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    private ActivityLoginScreenBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.login2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailLogin = binding.emailLogin.getText().toString();
                String passwordLogin = binding.passwordLogin.getText().toString();
                if (emailLogin.equals("") || passwordLogin.equals("")) {
                    Toast.makeText(LoginScreen.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    // Show loading dialog
                    ProgressDialog progressDialog = new ProgressDialog(LoginScreen.this);
                    progressDialog.setMessage("Logging in...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    // Authenticate user
                    auth.signInWithEmailAndPassword(emailLogin, passwordLogin)
                            .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss(); // Dismiss loading dialog

                                    if (task.isSuccessful()) {
                                        // User login successful, start welcome activity
                                        startActivity(new Intent(LoginScreen.this, welcome.class));
                                        finish();
                                    } else {
                                        // User login failed, display error message
                                        Toast.makeText(LoginScreen.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        binding.signUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SignUpPage activity
                startActivity(new Intent(LoginScreen.this, SignUpPage.class));
                finish();
            }
        });
    }
}
