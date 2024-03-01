package com.example.rr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.learn.R;
import com.example.learn.databinding.ActivityMainBinding;
import com.example.learn.databinding.ActivitySignUpPageBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpPage extends AppCompatActivity {

    private ActivitySignUpPageBinding binding;
    private FirebaseAuth auth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentusr = auth.getCurrentUser();
        if(currentusr!= null){
            startActivity(new Intent(SignUpPage.this,MainActivity.class));
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        binding.alreadyRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this,LoginScreen.class));
                finish();
            }
        });


        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usrname=binding.urname.getText().toString();
                String email = binding.email.getText().toString();
                String password = binding.password.getText().toString();

                if(usrname.equals("") || email.equals("") || password.equals("") ){
                    Toast.makeText(SignUpPage.this, "Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else{
                    auth.createUserWithEmailAndPassword(email,password)
                            .addOnCompleteListener(SignUpPage.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(SignUpPage.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUpPage.this,LoginScreen.class));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(SignUpPage.this, "Registration Failed!?", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
            }
        });

    }
}