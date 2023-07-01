package com.example.saored;

import static android.content.ContentValues.TAG;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.saored.Fragment.ChangePasswordFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import java.lang.String;
import java.util.HashMap;
import java.util.Objects;

public class login extends AppCompatActivity {

  private LinearLayout linearLayoutsignup;
  private EditText edtEmail, edtPass;
  private Button btnSignIn;
  TextView OpenForgetPass;
    private ProgressDialog progressDialog;
  @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

      progressDialog = new ProgressDialog(this);
      progressDialog.setMessage("Loading....");
      edtEmail = findViewById(R.id.email);
      edtPass = findViewById(R.id.password);
      btnSignIn = findViewById(R.id.btnsignin);
      OpenForgetPass = findViewById(R.id.OpenForgetPass);



      btnSignIn.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              onClickSignIn();
          }
      });

      OpenForgetPass.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(getApplicationContext(), ForgetPasswordActivity.class));
          }
      });

    }
    private void onClickSignIn() {
        String strEmail = edtEmail.getText().toString().trim();
        String strPass = edtPass.getText().toString().trim();
        if (!Patterns.EMAIL_ADDRESS.matcher(strEmail).matches()) {
            edtEmail.setError("Email không hợp lệ");
            edtEmail.setFocusable(true);
        } else if (TextUtils.isEmpty(strEmail)) {
            Toast.makeText(this, "vui lòng nhập email!!", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(strPass)) {
            Toast.makeText(this, "vui lòng nhập password!!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            loginUser(strEmail, strPass);
        }
    }

    private void loginUser(String strEmail, String strPass) {
        progressDialog.show();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(strEmail, strPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            String uid = task.getResult().getUser().getUid();
                            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                            firebaseDatabase.getReference().child("Users").child(uid).child("isAdmin").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String isAdmin = snapshot.getValue(String.class);
                                    if(Objects.equals(isAdmin, "0")){
                                        Intent in = new Intent(login.this, MainActivity.class);
                                        startActivity(in);
                                        finishAffinity();
                                    }
                                    if (Objects.equals(isAdmin, "1")){
                                        Intent intent = new Intent(login.this, AdminActivity.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                    if (Objects.equals(isAdmin, "2")){
                                        Intent intent = new Intent(login.this, MainActivityTeacher.class);
                                        startActivity(intent);
                                        finishAffinity();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });


                            Toast.makeText(getApplicationContext(),"Đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(login.this, MainActivity.class);
//                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(login.this, "Đăng nhập thất bại",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(login.this, ""+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

 }



//    private EditText emailedit, passedit;
//    private Button btnsignin;
//    private FirebaseAuth mAuth;
////    FirebaseDatabase database;
////    DatabaseReference reference;
//
//    @Override
//    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        mAuth = FirebaseAuth.getInstance();
//
//        emailedit = findViewById(R.id.Email);
//        passedit = findViewById(R.id.Password);
//        btnsignin = findViewById(R.id.btnsignin);
//
//        btnsignin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                login();
//            }
//        });
//    }
//
//    private void login() {
//        String email, pass;
//        email = emailedit.getText().toString();
//        pass = passedit.getText().toString();
//
//        if (TextUtils.isEmpty(email)) {
//            Toast.makeText(this, "vui lòng nhập email!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        if (TextUtils.isEmpty(pass)) {
//            Toast.makeText(this, "vui lòng nhập password!!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//
//                    FirebaseUser user = mAuth.getCurrentUser();
//
////                    if(task.getResult().getAdditionalUserInfo().isNewUser()){
////                        String email = user.getEmail();
////                        String uid = user.getUid();
////                        HashMap<Object, String> hashMap = new HashMap<>();
////
////                        hashMap.put("Email", email);
////                        hashMap.put("Name", "");
////                        hashMap.put("Image", "");
////                        hashMap.put("Class", "");
////                        hashMap.put("Cover", "");
////                    FirebaseDatabase database = FirebaseDatabase.getInstance();
////                    DatabaseReference reference = database.getReference("Users");
////                    reference.child(uid).setValue(hashMap);
////                    }
////
//
//                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công!!", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(login.this, MainActivity.class);
//                    startActivity(intent);
//
//                }
//                else{
//                    Toast.makeText(getApplicationContext(),"Đăng nhập không thành công!!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });