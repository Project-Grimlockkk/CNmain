package com.example.rr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learn.R;
import com.example.learn.databinding.ActivitySignUpPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpPage extends AppCompatActivity {

    private ActivitySignUpPageBinding binding;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();

        binding.alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to LoginScreen activity
                startActivity(new Intent(SignUpPage.this, LoginScreen.class));
                finish();
            }
        });

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Extract user input
                String username = binding.urname.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();

                // Validate user input
                if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignUpPage.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                } else {
                    // Show loading dialog
                    ProgressDialog progressDialog = new ProgressDialog(SignUpPage.this);
                    progressDialog.setMessage("Signing...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    // Perform user registration
                    auth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressDialog.dismiss(); // Dismiss loading dialog

                                    if (task.isSuccessful()) {
                                        // Registration successful
                                        Toast.makeText(SignUpPage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUpPage.this, LoginScreen.class));
                                        finish();
                                    } else {
                                        // Registration failed, display error message
                                        Toast.makeText(SignUpPage.this, "Registration Failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });
    }
}
