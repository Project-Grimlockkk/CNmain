
package com.example.rr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learn.R;
import com.example.learn.databinding.ActivityLoginScreenBinding;
import com.example.learn.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
                String emailLogin= binding.emailLogin.getText().toString();
                String passwordLogin = binding.passwordLogin.getText().toString();
                if(emailLogin.equals("") || passwordLogin.equals("") ){
                    Toast.makeText(LoginScreen.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.signInWithEmailAndPassword(emailLogin,passwordLogin)
                            .addOnCompleteListener(LoginScreen.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(LoginScreen.this,MainActivity.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(LoginScreen.this, "Registration Failed!?"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

        binding.signUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this,SignUpPage.class));
                finish();
            }
        });

    }
}