package com.example.saored;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Objects;

public class resgiter extends AppCompatActivity {

    private EditText edtEmail,edtPass,edtclass, edtName,edtIsAdmin;
    private Button btnsignup, back;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgiter);

        initUi();
        initListener();
    }

    private void initListener() {
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnClickSignUp();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(resgiter.this, AdminActivity.class));
            }
        });
    }

    private void OnClickSignUp() {
        String strEmail  = edtEmail.getText().toString().trim();
        String strPass  = edtPass.getText().toString().trim();
        String strClass  = edtclass.getText().toString().trim();
        String strName  = edtName.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()){
            edtEmail.setError("Email không hợp lệ");
            edtEmail.setFocusable(true);
        }else if (strPass.length() < 6){
            edtPass.setError("Mật khẩu phải trên 6 kí tự");
            edtPass.setFocusable(true);
        } else {
            resgiterUser(strEmail, strPass);
        }

    }

    private void resgiterUser(String strEmail, String strPass) {
        progressDialog.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(strEmail, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            // Sign in success, update UI with the signed-in user's information

                            FirebaseUser user = mAuth.getCurrentUser();
                            String email = user.getEmail();
                            String uid = user.getUid();
                            String strClass  = edtclass.getText().toString().trim();
                            String strName  = edtName.getText().toString().trim();
                            String admin  = edtIsAdmin.getText().toString().trim();

                            HashMap<Object, String> hashMap = new HashMap<>();
                            HashMap<Object, Double> hashMap1 = new HashMap<>();
                            if(Objects.equals(admin, "0")) {
                                hashMap.put("email", email);
                                hashMap.put("uid", uid);
                                hashMap.put("name", strName);
                                hashMap.put("lop", strClass);
                                hashMap.put("image", "");
                                hashMap1.put("scores",  0.0);
                                hashMap.put("isAdmin", admin);


                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Users");

                                reference.child(uid).setValue(hashMap);
                            }

                            if(Objects.equals(admin, "1")) {
                                hashMap.put("email", email);
                                hashMap.put("uid", uid);
                                hashMap.put("name", strName);
                                hashMap.put("image", "");
                                hashMap.put("isAdmin", admin);


                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Users");

                                reference.child(uid).setValue(hashMap);
                            }

                            if(Objects.equals(admin, "2")) {
                                hashMap.put("email", email);
                                hashMap.put("uid", uid);
                                hashMap.put("name", strName);
                                hashMap.put("image", "");
                                hashMap.put("isAdmin", admin);


                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference reference = database.getReference("Users");

                                reference.child(uid).setValue(hashMap);
                            }

                            Toast.makeText(resgiter.this, "Đã thêm!!!!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(resgiter.this, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }

    private void initUi(){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        edtEmail = findViewById(R.id.email);
        edtIsAdmin = findViewById(R.id.isAdmin);
        edtclass = findViewById(R.id.Class);
        edtName = findViewById(R.id.name);
        edtPass = findViewById(R.id.password);
        back = findViewById(R.id.back);
        btnsignup = findViewById(R.id.btnsignup);
    }

}