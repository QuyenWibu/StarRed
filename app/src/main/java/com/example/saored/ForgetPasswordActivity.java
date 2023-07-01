package com.example.saored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private Button btnForgetpass,Opensignup;
    private String email;
    private EditText Foremail;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();

        Foremail = findViewById(R.id.Foremail);
        btnForgetpass = findViewById(R.id.btnForgetpass);
        Opensignup = findViewById(R.id.Opensignup);

        Opensignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgetPasswordActivity.this ,login.class));
            }
        });
        btnForgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
            }


        });

    }



    private void validateData() {
        email = Foremail.getText().toString();
        if(email.isEmpty()){
            Foremail.setError("Hãy nhập email của bạn!!!!!");
        } else {
            forgetpass();
        }
    }

    private void forgetpass() {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this, "Hãy kiểm tra Email của bạn", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgetPasswordActivity.this, login.class));
                    finish();
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "Lỗi:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}