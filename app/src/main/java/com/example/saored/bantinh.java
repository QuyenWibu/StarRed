package com.example.saored;

import android.Manifest;
import android.app.usage.NetworkStats;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.common.net.InternetDomainName;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class bantinh extends AppCompatActivity {
    Button btn_capture, btn_capnhat;
    TextView tv_data;
    private static final int REQUEST_CAMERA_CODE = 800;
    Bitmap bitmap;
    char[] chars;
    String s;
    ActionBar actionBar;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    String luuketquadatach;
    EditText b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20, b21, b22, b23, b24, b25, b26, b27, b28, b29, b30, b31, b32, b33, b34, b35, b36, b37, b38, b39, b40, b41, b42, b43, b44, b45, b46, b47, b48, b49, b50, b51, b52, b53, b54, b55, b56, b57, b58, b59, b60, b61, b62, b63, b64, b65, b66, b67, b68, b69, b70, b71, b72, b73, b74, b75, b76, b77, b78, b79, b80;
    Button btn,back;
    TextView kqtb1, kqtb2, kqtb3, kqtb4, kqtb5, kqtb6, kqtb7, kqtb8, kqtb9, kqtb10, kqtb11, kqtb12, kqtb13, kqtb14, kqtb15, kqtb16, dtb1, dtb2;
    String[] stg;
    TextView kqnmb1, kqnmb2, kqnmb3, kqnmb4, kqnmb5, kqnmb6, kqnmb7, kqnmb8, kqnmb9, kqnmb10, kqtongdtb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bantinh);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = firebaseDatabase.getReference("Users");

//        Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//
//                    String strclass = "" + ds.child("class").getValue();
//
//
//
//                    tvclass.setText(strclass);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

        btn_capnhat = findViewById(R.id.button_capnhat);
        btn = findViewById(R.id.btn);
        btn_capture = findViewById(R.id.button_capture);
        tv_data = findViewById(R.id.text_data);
        back=findViewById(R.id.back);
        kqtongdtb = (TextView) findViewById(R.id.kqtongdtb);

        kqnmb1 = (TextView) findViewById(R.id.kqnmb1);
        kqnmb2 = (TextView) findViewById(R.id.kqnmb2);
        kqnmb3 = (TextView) findViewById(R.id.kqnmb3);
        kqnmb4 = (TextView) findViewById(R.id.kqnmb4);
        kqnmb5 = (TextView) findViewById(R.id.kqnmb5);
        kqnmb6 = (TextView) findViewById(R.id.kqnmb6);
        kqnmb7 = (TextView) findViewById(R.id.kqnmb7);
        kqnmb8 = (TextView) findViewById(R.id.kqnmb8);
        kqnmb9 = (TextView) findViewById(R.id.kqnmb9);
        kqnmb10 = (TextView) findViewById(R.id.kqnmb10);

        dtb2 = (TextView) findViewById(R.id.dtb2);
        dtb1 = (TextView) findViewById(R.id.dtb1);
        kqtb1 = (TextView) findViewById(R.id.kq1);
        kqtb2 = (TextView) findViewById(R.id.kq2);
        kqtb3 = (TextView) findViewById(R.id.kq3);
        kqtb4 = (TextView) findViewById(R.id.kq4);
        kqtb5 = (TextView) findViewById(R.id.kq5);
        kqtb6 = (TextView) findViewById(R.id.kq6);
        kqtb7 = (TextView) findViewById(R.id.kq7);
        kqtb8 = (TextView) findViewById(R.id.kq8);
        kqtb9 = (TextView) findViewById(R.id.kq9);
        kqtb10 = (TextView) findViewById(R.id.kq10);
        kqtb11 = (TextView) findViewById(R.id.kq11);
        kqtb12 = (TextView) findViewById(R.id.kq12);
        kqtb13 = (TextView) findViewById(R.id.kq13);
        kqtb14 = (TextView) findViewById(R.id.kq14);
        kqtb15 = (TextView) findViewById(R.id.kq15);
        kqtb16 = (TextView) findViewById(R.id.kq16);

        b1 = (EditText) findViewById(R.id.bang1);
        b2 = (EditText) findViewById(R.id.bang2);
        b3 = (EditText) findViewById(R.id.bang3);
        b4 = (EditText) findViewById(R.id.bang4);
        b5 = (EditText) findViewById(R.id.bang5);
        b6 = (EditText) findViewById(R.id.bang6);
        b7 = (EditText) findViewById(R.id.bang7);
        b8 = (EditText) findViewById(R.id.bang8);
        b9 = (EditText) findViewById(R.id.bang9);
        b10 = (EditText) findViewById(R.id.bang10);
        b11 = (EditText) findViewById(R.id.bang11);
        b12 = (EditText) findViewById(R.id.bang12);
        b13 = (EditText) findViewById(R.id.bang13);
        b14 = (EditText) findViewById(R.id.bang14);
        b15 = (EditText) findViewById(R.id.bang15);
        b16 = (EditText) findViewById(R.id.bang16);
        b17 = (EditText) findViewById(R.id.bang17);
        b18 = (EditText) findViewById(R.id.bang18);
        b19 = (EditText) findViewById(R.id.bang19);
        b20 = (EditText) findViewById(R.id.bang20);
        b21 = (EditText) findViewById(R.id.bang21);
        b22 = (EditText) findViewById(R.id.bang22);
        b23 = (EditText) findViewById(R.id.bang23);
        b24 = (EditText) findViewById(R.id.bang24);
        b25 = (EditText) findViewById(R.id.bang25);
        b26 = (EditText) findViewById(R.id.bang26);
        b27 = (EditText) findViewById(R.id.bang27);
        b28 = (EditText) findViewById(R.id.bang28);
        b29 = (EditText) findViewById(R.id.bang29);
        b30 = (EditText) findViewById(R.id.bang30);
        b31 = (EditText) findViewById(R.id.bang31);
        b32 = (EditText) findViewById(R.id.bang32);
        b33 = (EditText) findViewById(R.id.bang33);
        b34 = (EditText) findViewById(R.id.bang34);
        b35 = (EditText) findViewById(R.id.bang35);
        b36 = (EditText) findViewById(R.id.bang36);
        b37 = (EditText) findViewById(R.id.bang37);
        b38 = (EditText) findViewById(R.id.bang38);
        b39 = (EditText) findViewById(R.id.bang39);
        b40 = (EditText) findViewById(R.id.bang40);
        b41 = (EditText) findViewById(R.id.bang41);
        b42 = (EditText) findViewById(R.id.bang42);
        b43 = (EditText) findViewById(R.id.bang43);
        b44 = (EditText) findViewById(R.id.bang44);
        b45 = (EditText) findViewById(R.id.bang45);
        b46 = (EditText) findViewById(R.id.bang46);
        b47 = (EditText) findViewById(R.id.bang47);
        b48 = (EditText) findViewById(R.id.bang48);
        b49 = (EditText) findViewById(R.id.bang49);
        b50 = (EditText) findViewById(R.id.bang50);
        b51 = (EditText) findViewById(R.id.bang51);
        b52 = (EditText) findViewById(R.id.bang52);
        b53 = (EditText) findViewById(R.id.bang53);
        b54 = (EditText) findViewById(R.id.bang54);
        b55 = (EditText) findViewById(R.id.bang55);
        b56 = (EditText) findViewById(R.id.bang56);
        b57 = (EditText) findViewById(R.id.bang57);
        b58 = (EditText) findViewById(R.id.bang58);
        b59 = (EditText) findViewById(R.id.bang59);
        b60 = (EditText) findViewById(R.id.bang60);
        b61 = (EditText) findViewById(R.id.bang61);
        b62 = (EditText) findViewById(R.id.bang62);
        b63 = (EditText) findViewById(R.id.bang63);
        b64 = (EditText) findViewById(R.id.bang64);
        b65 = (EditText) findViewById(R.id.bang65);
        b66 = (EditText) findViewById(R.id.bang66);
        b67 = (EditText) findViewById(R.id.bang67);
        b68 = (EditText) findViewById(R.id.bang68);
        b69 = (EditText) findViewById(R.id.bang69);
        b70 = (EditText) findViewById(R.id.bang70);
        b71 = (EditText) findViewById(R.id.bang71);
        b72 = (EditText) findViewById(R.id.bang72);
        b73 = (EditText) findViewById(R.id.bang73);
        b74 = (EditText) findViewById(R.id.bang74);
        b75 = (EditText) findViewById(R.id.bang75);
        b76 = (EditText) findViewById(R.id.bang76);
        b77 = (EditText) findViewById(R.id.bang77);
        b78 = (EditText) findViewById(R.id.bang78);
        b79 = (EditText) findViewById(R.id.bang79);
        b80 = (EditText) findViewById(R.id.bang80);

        if (ContextCompat.checkSelfPermission(bantinh.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(bantinh.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_CODE);
        }
        btn_capture.setOnClickListener(view -> CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(bantinh.this));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(bantinh.this, MainActivity.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int chia = 0;
                int chia2 = 0;
                if (b1.getText().toString().length() == 0){
                    b1.setText("0");
                }
                int nmb1 = Integer.parseInt(b1.getText().toString());
                if (b2.getText().toString().length() == 0){
                    b2.setText("0");
                }
                int nmb2 = Integer.parseInt(b2.getText().toString());
                if (b3.getText().toString().length() == 0){
                    b3.setText("0");
                }
                int nmb3 = Integer.parseInt(b3.getText().toString());
                if (b4.getText().toString().length() == 0){
                    b4.setText("0");
                }
                int nmb4 = Integer.parseInt(b4.getText().toString());
                if (b5.getText().toString().length() == 0){
                    b5.setText("0");
                }
                int nmb5 = Integer.parseInt(b5.getText().toString());
                int nmbkq1 = nmb1 + nmb2 + nmb3 + nmb4 + nmb5;
                kqtb1.setText(String.valueOf(nmbkq1));
                if (b6.getText().toString().length() == 0){
                    b6.setText("0");
                }
                int nmb6 = Integer.parseInt(b6.getText().toString());
                if (b7.getText().toString().length() == 0){
                    b7.setText("0");
                }
                int nmb7 = Integer.parseInt(b7.getText().toString());
                if (b8.getText().toString().length() == 0){
                    b8.setText("0");
                }
                int nmb8 = Integer.parseInt(b8.getText().toString());
                if (b9.getText().toString().length() == 0){
                    b9.setText("0");
                }
                int nmb9 = Integer.parseInt(b9.getText().toString());
                if (b10.getText().toString().length() == 0){
                    b10.setText("0");
                }
                int nmb10 = Integer.parseInt(b10.getText().toString());
                int nmbkq2 = nmb6 + nmb7 + nmb8 + nmb9 + nmb10;
                kqtb2.setText(String.valueOf(nmbkq2));
                if (b11.getText().toString().length() == 0){
                    b11.setText("0");
                }
                int nmb11 = Integer.parseInt(b11.getText().toString());
                if (b12.getText().toString().length() == 0){
                    b12.setText("0");
                }
                int nmb12 = Integer.parseInt(b12.getText().toString());
                if (b13.getText().toString().length() == 0){
                    b13.setText("0");
                }
                int nmb13 = Integer.parseInt(b13.getText().toString());
                if (b14.getText().toString().length() == 0){
                    b14.setText("0");
                }
                int nmb14 = Integer.parseInt(b14.getText().toString());
                if (b15.getText().toString().length() == 0){
                    b15.setText("0");
                }
                int nmb15 = Integer.parseInt(b15.getText().toString());
                int nmbkq3 = nmb11 + nmb12 + nmb13 + nmb14 + nmb15;
                kqtb3.setText(String.valueOf(nmbkq3));
                if (b16.getText().toString().length() == 0){
                    b16.setText("0");
                }
                int nmb16 = Integer.parseInt(b16.getText().toString());
                if (b17.getText().toString().length() == 0){
                    b17.setText("0");
                }
                int nmb17 = Integer.parseInt(b17.getText().toString());
                if (b18.getText().toString().length() == 0){
                    b18.setText("0");
                }
                int nmb18 = Integer.parseInt(b18.getText().toString());
                if (b19.getText().toString().length() == 0){
                    b19.setText("0");
                }
                int nmb19 = Integer.parseInt(b19.getText().toString());
                if (b20.getText().toString().length() == 0){
                    b20.setText("0");
                }
                int nmb20 = Integer.parseInt(b20.getText().toString());
                int nmbkq4 = nmb16 + nmb17 + nmb18 + nmb19 + nmb20;
                kqtb4.setText(String.valueOf(nmbkq4));
                if (b21.getText().toString().length() == 0){
                    b21.setText("0");
                }
                int nmb21 = Integer.parseInt(b21.getText().toString());
                if (b22.getText().toString().length() == 0){
                    b22.setText("0");
                }
                int nmb22 = Integer.parseInt(b22.getText().toString());
                if (b23.getText().toString().length() == 0){
                    b23.setText("0");
                }
                int nmb23 = Integer.parseInt(b23.getText().toString());
                if (b24.getText().toString().length() == 0){
                    b24.setText("0");
                }
                int nmb24 = Integer.parseInt(b24.getText().toString());
                if (b25.getText().toString().length() == 0){
                    b25.setText("0");
                }
                int nmb25 = Integer.parseInt(b25.getText().toString());
                int nmbkq5 = nmb21 + nmb22 + nmb23 + nmb24 + nmb25;
                kqtb5.setText(String.valueOf(nmbkq5));
                if (b26.getText().toString().length() == 0){
                    b26.setText("0");
                }
                int nmb26 = Integer.parseInt(b26.getText().toString());
                if (b27.getText().toString().length() == 0){
                    b27.setText("0");
                }
                int nmb27 = Integer.parseInt(b27.getText().toString());
                if (b28.getText().toString().length() == 0){
                    b28.setText("0");
                }
                int nmb28 = Integer.parseInt(b28.getText().toString());
                if (b29.getText().toString().length() == 0){
                    b29.setText("0");
                }
                int nmb29 = Integer.parseInt(b29.getText().toString());
                if (b30.getText().toString().length() == 0){
                    b30.setText("0");
                }
                int nmb30 = Integer.parseInt(b30.getText().toString());
                int nmbkq6 = nmb26 + nmb27 + nmb28 + nmb29 + nmb30;
                kqtb6.setText(String.valueOf(nmbkq6));
                if (b31.getText().toString().length() == 0){
                    b31.setText("0");
                }
                int nmb31 = Integer.parseInt(b31.getText().toString());
                if (b32.getText().toString().length() == 0){
                    b32.setText("0");
                }
                int nmb32 = Integer.parseInt(b32.getText().toString());
                if (b33.getText().toString().length() == 0){
                    b33.setText("0");
                }
                int nmb33 = Integer.parseInt(b33.getText().toString());
                if (b34.getText().toString().length() == 0){
                    b34.setText("0");
                }
                int nmb34 = Integer.parseInt(b34.getText().toString());
                if (b35.getText().toString().length() == 0){
                    b35.setText("0");
                }
                int nmb35 = Integer.parseInt(b35.getText().toString());
                int nmbkq7 = nmb31 + nmb32 + nmb33 + nmb34 + nmb35;
                kqtb7.setText(String.valueOf(nmbkq7));
                if (b36.getText().toString().length() == 0){
                    b36.setText("0");
                }
                int nmb36 = Integer.parseInt(b36.getText().toString());
                if (b37.getText().toString().length() == 0){
                    b37.setText("0");
                }
                int nmb37 = Integer.parseInt(b37.getText().toString());
                if (b38.getText().toString().length() == 0){
                    b38.setText("0");
                }
                int nmb38 = Integer.parseInt(b38.getText().toString());
                if (b39.getText().toString().length() == 0){
                    b39.setText("0");
                }
                int nmb39 = Integer.parseInt(b39.getText().toString());
                if (b40.getText().toString().length() == 0){
                    b40.setText("0");
                }
                int nmb40 = Integer.parseInt(b40.getText().toString());
                int nmbkq8 = nmb36 + nmb37 + nmb38 + nmb39 + nmb40;
                kqtb8.setText(String.valueOf(nmbkq8));
                if (b41.getText().toString().length() == 0){
                    b41.setText("0");
                }
                int nmb41 = Integer.parseInt(b41.getText().toString());
                if (b42.getText().toString().length() == 0){
                    b42.setText("0");
                }
                int nmb42 = Integer.parseInt(b42.getText().toString());
                if (b43.getText().toString().length() == 0){
                    b43.setText("0");
                }
                int nmb43 = Integer.parseInt(b43.getText().toString());
                if (b44.getText().toString().length() == 0){
                    b44.setText("0");
                }
                int nmb44 = Integer.parseInt(b44.getText().toString());
                if (b45.getText().toString().length() == 0){
                    b45.setText("0");
                }
                int nmb45 = Integer.parseInt(b45.getText().toString());
                int nmbkq9 = nmb41 + nmb42 + nmb43 + nmb44 + nmb45;
                kqtb9.setText(String.valueOf(nmbkq9));
                if (b46.getText().toString().length() == 0){
                    b46.setText("0");
                }
                int nmb46 = Integer.parseInt(b46.getText().toString());
                if (b47.getText().toString().length() == 0){
                    b47.setText("0");
                }
                int nmb47 = Integer.parseInt(b47.getText().toString());
                if (b48.getText().toString().length() == 0){
                    b48.setText("0");
                }
                int nmb48 = Integer.parseInt(b48.getText().toString());
                if (b49.getText().toString().length() == 0){
                    b49.setText("0");
                }
                int nmb49 = Integer.parseInt(b49.getText().toString());
                if (b50.getText().toString().length() == 0){
                    b50.setText("0");
                }
                int nmb50 = Integer.parseInt(b50.getText().toString());
                int nmbkq10 = nmb46 + nmb47 + nmb48 + nmb49 + nmb50;
                kqtb10.setText(String.valueOf(nmbkq10));
                if (b51.getText().toString().length() == 0){
                    b51.setText("0");
                }
                int nmb51 = Integer.parseInt(b51.getText().toString());
                if (b52.getText().toString().length() == 0){
                    b52.setText("0");
                }
                int nmb52 = Integer.parseInt(b52.getText().toString());
                if (b53.getText().toString().length() == 0){
                    b53.setText("0");
                }
                int nmb53 = Integer.parseInt(b53.getText().toString());
                if (b54.getText().toString().length() == 0){
                    b54.setText("0");
                }
                int nmb54 = Integer.parseInt(b54.getText().toString());
                if (b55.getText().toString().length() == 0){
                    b55.setText("0");
                }
                int nmb55 = Integer.parseInt(b55.getText().toString());
                int nmbkq11 = nmb51 + nmb52 + nmb53 + nmb54 + nmb55;
                kqtb11.setText(String.valueOf(nmbkq11));
                if (b56.getText().toString().length() == 0){
                    b56.setText("0");
                }
                int nmb56 = Integer.parseInt(b56.getText().toString());
                if (b57.getText().toString().length() == 0){
                    b57.setText("0");
                }
                int nmb57 = Integer.parseInt(b57.getText().toString());
                if (b58.getText().toString().length() == 0){
                    b58.setText("0");
                }
                int nmb58 = Integer.parseInt(b58.getText().toString());
                if (b59.getText().toString().length() == 0){
                    b59.setText("0");
                }
                int nmb59 = Integer.parseInt(b59.getText().toString());
                if (b60.getText().toString().length() == 0){
                    b60.setText("0");
                }
                int nmb60 = Integer.parseInt(b60.getText().toString());
                int nmbkq12 = nmb56 + nmb57 + nmb58 + nmb59 + nmb60;
                kqtb12.setText(String.valueOf(nmbkq12));
                if (b61.getText().toString().length() == 0){
                    b61.setText("0");
                }
                int nmb61 = Integer.parseInt(b61.getText().toString());
                if (b62.getText().toString().length() == 0){
                    b62.setText("0");
                }
                int nmb62 = Integer.parseInt(b62.getText().toString());
                if (b63.getText().toString().length() == 0){
                    b63.setText("0");
                }
                int nmb63 = Integer.parseInt(b63.getText().toString());
                if (b64.getText().toString().length() == 0){
                    b64.setText("0");
                }
                int nmb64 = Integer.parseInt(b64.getText().toString());
                if (b65.getText().toString().length() == 0){
                    b65.setText("0");
                }
                int nmb65 = Integer.parseInt(b65.getText().toString());
                int nmbkq13 = nmb61 + nmb62 + nmb63 + nmb64 + nmb65;
                kqtb13.setText(String.valueOf(nmbkq13));
                if (b66.getText().toString().length() == 0){
                    b66.setText("0");
                }
                int nmb66 = Integer.parseInt(b66.getText().toString());
                if (b67.getText().toString().length() == 0){
                    b67.setText("0");
                }
                int nmb67 = Integer.parseInt(b67.getText().toString());
                if (b68.getText().toString().length() == 0){
                    b68.setText("0");
                }
                int nmb68 = Integer.parseInt(b68.getText().toString());
                if (b69.getText().toString().length() == 0){
                    b69.setText("0");
                }
                int nmb69 = Integer.parseInt(b69.getText().toString());
                if (b70.getText().toString().length() == 0){
                    b70.setText("0");
                }
                int nmb70 = Integer.parseInt(b70.getText().toString());
                int nmbkq14 = nmb66 + nmb67 + nmb68 + nmb69 + nmb70;
                kqtb14.setText(String.valueOf(nmbkq14));
                if (b71.getText().toString().length() == 0){
                    b71.setText("0");
                }
                int nmb71 = Integer.parseInt(b71.getText().toString());
                if (b72.getText().toString().length() == 0){
                    b72.setText("0");
                }
                int nmb72 = Integer.parseInt(b72.getText().toString());
                if (b73.getText().toString().length() == 0){
                    b73.setText("0");
                }
                int nmb73 = Integer.parseInt(b73.getText().toString());
                if (b74.getText().toString().length() == 0){
                    b74.setText("0");
                }
                int nmb74 = Integer.parseInt(b74.getText().toString());
                if (b75.getText().toString().length() == 0){
                    b75.setText("0");
                }
                int nmb75 = Integer.parseInt(b75.getText().toString());
                int nmbkq15 = nmb71 + nmb72 + nmb73 + nmb74 + nmb75;
                kqtb15.setText(String.valueOf(nmbkq15));
                if (b76.getText().toString().length() == 0){
                    b76.setText("0");
                }
                int nmb76 = Integer.parseInt(b76.getText().toString());
                if (b77.getText().toString().length() == 0){
                    b77.setText("0");
                }
                int nmb77 = Integer.parseInt(b77.getText().toString());
                if (b78.getText().toString().length() == 0){
                    b78.setText("0");
                }
                int nmb78 = Integer.parseInt(b78.getText().toString());
                if (b79.getText().toString().length() == 0){
                    b79.setText("0");
                }
                int nmb79 = Integer.parseInt(b79.getText().toString());
                if (b80.getText().toString().length() == 0){
                    b80.setText("0");
                }
                int nmb80 = Integer.parseInt(b80.getText().toString());
                int nmbkq16 = nmb76 + nmb77 + nmb78 + nmb79 + nmb80;
                kqtb16.setText(String.valueOf(nmbkq16));
                int tkqnmb1 = 0, tkqnmb2 = 0, tkqnmb3 = 0, tkqnmb4 = 0, tkqnmb5 = 0, tkqnmb6 = 0, tkqnmb7 = 0, tkqnmb8 = 0, tkqnmb9 = 0, tkqnmb10 = 0;
                tkqnmb1 = nmb1 + nmb11 + nmb21 + nmb31 + nmb41 +nmb51 + nmb61 + nmb71;
                tkqnmb2 = nmb2 + nmb12 + nmb22 + nmb32 + nmb42 +nmb52 + nmb62 + nmb72;
                tkqnmb3 = nmb3 + nmb13 + nmb23 + nmb33 + nmb43 +nmb53 + nmb63 + nmb73;
                tkqnmb4 = nmb4 + nmb14 + nmb24 + nmb34 + nmb44 +nmb54 + nmb64 + nmb74;
                tkqnmb5 = nmb5 + nmb15 + nmb25 + nmb35 + nmb45 +nmb55 + nmb65 + nmb75;
                tkqnmb6 = nmb6 + nmb16 + nmb26 + nmb36 + nmb46 +nmb56 + nmb66 + nmb76;
                tkqnmb7 = nmb7 + nmb17 + nmb27 + nmb37 + nmb47 +nmb57 + nmb67 + nmb77;
                tkqnmb8 = nmb8 + nmb18 + nmb28 + nmb38 + nmb48 +nmb58 + nmb68 + nmb78;
                tkqnmb9 = nmb9 + nmb19 + nmb29 + nmb39 + nmb49 +nmb59 + nmb69 + nmb79;
                tkqnmb10 = nmb10 + nmb20 + nmb30 + nmb40 + nmb50 +nmb60 + nmb70 + nmb80;
                kqnmb1.setText(String.valueOf(tkqnmb1));
                kqnmb2.setText(String.valueOf(tkqnmb2));
                kqnmb3.setText(String.valueOf(tkqnmb3));
                kqnmb4.setText(String.valueOf(tkqnmb4));
                kqnmb5.setText(String.valueOf(tkqnmb5));
                kqnmb6.setText(String.valueOf(tkqnmb6));
                kqnmb7.setText(String.valueOf(tkqnmb7));
                kqnmb8.setText(String.valueOf(tkqnmb8));
                kqnmb9.setText(String.valueOf(tkqnmb9));
                kqnmb10.setText(String.valueOf(tkqnmb10));

                if (nmb1 != 0){
                    chia++;
                }
                if (nmb2 != 0){
                    chia++;
                }
                if (nmb3 != 0){
                    chia++;
                }
                if (nmb4 != 0){
                    chia++;
                }
                if (nmb5 != 0){
                    chia++;
                }
                if (nmb6 != 0){
                    chia2++;
                }
                if (nmb7 != 0){
                    chia2++;
                }
                if (nmb8 != 0){
                    chia2++;
                }
                if (nmb9 != 0){
                    chia2++;
                }
                if (nmb10 != 0){
                    chia2++;
                }
                if (nmb11 != 0){
                    chia++;
                }
                if (nmb12 != 0){
                    chia++;
                }
                if (nmb13 != 0){
                    chia++;
                }
                if (nmb14 != 0){
                    chia++;
                }
                if (nmb15 != 0){
                    chia++;
                }
                if (nmb16 != 0){
                    chia2++;
                }
                if (nmb17 != 0){
                    chia2++;
                }
                if (nmb18 != 0){
                    chia2++;
                }
                if (nmb19 != 0){
                    chia2++;
                }
                if (nmb20 != 0){
                    chia2++;
                }
                if (nmb21 != 0){
                    chia++;
                }
                if (nmb22 != 0){
                    chia++;
                }
                if (nmb23 != 0){
                    chia++;
                }
                if (nmb24 != 0){
                    chia++;
                }
                if (nmb25 != 0){
                    chia++;
                }
                if (nmb26 != 0){
                    chia2++;
                }
                if (nmb27 != 0){
                    chia2++;
                }
                if (nmb28 != 0){
                    chia2++;
                }
                if (nmb29 != 0){
                    chia2++;
                }
                if (nmb30 != 0){
                    chia2++;
                }
                if (nmb31 != 0){
                    chia++;
                }
                if (nmb32 != 0){
                    chia++;
                }
                if (nmb33 != 0){
                    chia++;
                }
                if (nmb34 != 0){
                    chia++;
                }
                if (nmb35 != 0){
                    chia++;
                }
                if (nmb36 != 0){
                    chia2++;
                }
                if (nmb37 != 0){
                    chia2++;
                }
                if (nmb38 != 0){
                    chia2++;
                }
                if (nmb39 != 0){
                    chia2++;
                }
                if (nmb40 != 0){
                    chia2++;
                }
                if (nmb41 != 0){
                    chia++;
                }
                if (nmb42 != 0){
                    chia++;
                }
                if (nmb43 != 0){
                    chia++;
                }
                if (nmb44 != 0){
                    chia++;
                }
                if (nmb45 != 0){
                    chia++;
                }
                if (nmb46 != 0){
                    chia2++;
                }
                if (nmb47 != 0){
                    chia2++;
                }
                if (nmb48 != 0){
                    chia2++;
                }
                if (nmb49 != 0){
                    chia2++;
                }
                if (nmb50 != 0){
                    chia2++;
                }
                if (nmb51 != 0){
                    chia++;
                }
                if (nmb52 != 0){
                    chia++;
                }
                if (nmb53 != 0){
                    chia++;
                }
                if (nmb54 != 0){
                    chia++;
                }
                if (nmb55 != 0){
                    chia++;
                }
                if (nmb56 != 0){
                    chia2++;
                }
                if (nmb57 != 0){
                    chia2++;
                }
                if (nmb58 != 0){
                    chia2++;
                }
                if (nmb59 != 0){
                    chia2++;
                }
                if (nmb60 != 0){
                    chia2++;
                }
                if (nmb61 != 0){
                    chia++;
                }
                if (nmb62 != 0){
                    chia++;
                }
                if (nmb63 != 0){
                    chia++;
                }
                if (nmb64 != 0){
                    chia++;
                }
                if (nmb65 != 0){
                    chia++;
                }
                if (nmb66 != 0){
                    chia2++;
                }
                if (nmb67 != 0){
                    chia2++;
                }
                if (nmb68 != 0){
                    chia2++;
                }
                if (nmb69 != 0){
                    chia2++;
                }
                if (nmb70 != 0){
                    chia2++;
                }
                if (nmb71 != 0){
                    chia++;
                }
                if (nmb72 != 0){
                    chia++;
                }
                if (nmb73 != 0){
                    chia++;
                }
                if (nmb74 != 0){
                    chia++;
                }
                if (nmb75 != 0){
                    chia++;
                }
                if (nmb76 != 0){
                    chia2++;
                }
                if (nmb77 != 0){
                    chia2++;
                }
                if (nmb78 != 0){
                    chia2++;
                }
                if (nmb79 != 0){
                    chia2++;
                }
                if (nmb80 != 0){
                    chia2++;
                }
                double nmdtb = (double) (tkqnmb1 + tkqnmb2 + tkqnmb3 + tkqnmb4 + tkqnmb5) / chia;
                double ltnmdtb = Math.round(nmdtb * 100) / 100.0;
                dtb1.setText(String.valueOf(ltnmdtb));
                double nmdtb2 = (double) (tkqnmb6 + tkqnmb7 + tkqnmb8 + tkqnmb9 + tkqnmb10) / chia2;
                double ltnmdtb2 = Math.round(nmdtb2 * 100) / 100.0;
                dtb2.setText(String.valueOf(ltnmdtb2));
                double tongdtb = ((nmdtb2 * 2) + nmdtb) / 3;
                double lttongdtb = Math.round(tongdtb * 100) / 100.0;
                kqtongdtb.setText(String.valueOf(lttongdtb));

                updateData();
             }


        });
    }

    private void updateData() {
        TextView textView = findViewById(R.id.kqtongdtb);
        double value = Double.parseDouble(textView.getText().toString());

        // Here we are updating the new name
        HashMap<String, Object> result = new HashMap<>();
        result.put("scores", value);


        databaseReference.child(firebaseUser.getUid()).updateChildren(result).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {


                // after updated we will show updated
                Toast.makeText(bantinh.this, " updated score", Toast.LENGTH_LONG).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(bantinh.this, "Unable to update", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    StringBuilder stringBuilder = new StringBuilder();
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //lấy hình ảnh từ máy ảnh
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                assert result != null;
                Uri resultUri = result.getUri();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                    getTextFromImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        btn.setOnClickListener(view -> {
            getTextKetQua(stg);
        });
        btn_capnhat.setOnClickListener(view -> {

        });
    }
    private void getTextKetQua(String[] stg){

            if(b1.getText().toString().length() == 0){
                b1.setText("0");
            }
            if(b2.getText().toString().length() == 0){
                b2.setText("0");
            }
            if (b3.getText().toString().length() == 0){
                b3.setText("0");
            }
            if (b4.getText().toString().length() == 0){
                b4.setText("0");
            }
            if (b5.getText().toString().length() == 0){
                b5.setText("0");
            }
            if (b6.getText().toString().length() == 0){
                b6.setText("0");
            }
            if (b7.getText().toString().length() == 0){
                b7.setText("0");
            }
            if (b8.getText().toString().length() == 0){
                b8.setText("0");
            }
            if (b9.getText().toString().length() == 0){
                b9.setText("0");
            }
            if (b10.getText().toString().length() == 0){
                b10.setText("0");
            }
            if (b11.getText().toString().length() == 0){
                b11.setText("0");
            }
            if (b12.getText().toString().length() == 0){
                b12.setText("0");
            }
            if (b13.getText().toString().length() == 0){
                b13.setText("0");
            }
            if (b14.getText().toString().length() == 0){
                b14.setText("0");
            }
            if (b15.getText().toString().length() == 0){
                b15.setText("0");
            }
            if (b16.getText().toString().length() == 0){
                b16.setText("0");
            }
            if (b17.getText().toString().length() == 0){
                b17.setText("0");
            }
            if (b18.getText().toString().length() == 0){
                b18.setText("0");
            }
            if (b19.getText().toString().length() == 0){
                b19.setText("0");
            }
            if (b20.getText().toString().length() == 0){
                b20.setText("0");
            }
            if (b21.getText().toString().length() == 0){
                b21.setText("0");
            }
            if (b22.getText().toString().length() == 0){
                b22.setText("0");
            }
            if (b23.getText().toString().length() == 0){
                b23.setText("0");
            }
            if (b24.getText().toString().length() == 0){
                b24.setText("0");
            }
            if (b25.getText().toString().length() == 0){
                b25.setText("0");
            }
            if (b26.getText().toString().length() == 0){
                b26.setText("0");
            }
            if (b27.getText().toString().length() == 0){
                b27.setText("0");
            }
            if (b28.getText().toString().length() == 0){
                b28.setText("0");
            }
            if (b29.getText().toString().length() == 0){
                b29.setText("0");
            }
            if (b30.getText().toString().length() == 0){
                b30.setText("0");
            }
            if (b31.getText().toString().length() == 0){
                b31.setText("0");
            }
            if (b32.getText().toString().length() == 0){
                b32.setText("0");
            }
            if (b33.getText().toString().length() == 0){
                b33.setText("0");
            }
            if (b34.getText().toString().length() == 0){
                b34.setText("0");
            }
            if (b35.getText().toString().length() == 0){
                b35.setText("0");
            }
            if (b36.getText().toString().length() == 0){
                b36.setText("0");
            }
            if (b37.getText().toString().length() == 0){
                b37.setText("0");
            }
            if (b38.getText().toString().length() == 0){
                b38.setText("0");
            }
            if (b39.getText().toString().length() == 0){
                b39.setText("0");
            }
            if (b40.getText().toString().length() == 0){
                b40.setText("0");
            }
            if (b41.getText().toString().length() == 0){
                b41.setText("0");
            }
            if (b42.getText().toString().length() == 0){
                b42.setText("0");
            }
            if (b43.getText().toString().length() == 0){
                b43.setText("0");
            }
            if (b44.getText().toString().length() == 0){
                b44.setText("0");
            }
            if (b45.getText().toString().length() == 0){
                b45.setText("0");
            }
            if (b46.getText().toString().length() == 0){
                b46.setText("0");
            }
            if (b47.getText().toString().length() == 0){
                b47.setText("0");
            }
            if (b48.getText().toString().length() == 0){
                b48.setText("0");
            }
            if (b49.getText().toString().length() == 0){
                b49.setText("0");
            }
            if (b50.getText().toString().length() == 0){
                b50.setText("0");
            }
            if (b51.getText().toString().length() == 0){
                b51.setText("0");
            }
            if (b52.getText().toString().length() == 0){
                b52.setText("0");
            }
            if (b53.getText().toString().length() == 0){
                b53.setText("0");
            }
            if (b54.getText().toString().length() == 0){
                b54.setText("0");
            }
            if (b55.getText().toString().length() == 0){
                b55.setText("0");
            }
            if (b56.getText().toString().length() == 0){
                b56.setText("0");
            }
            if (b57.getText().toString().length() == 0){
                b57.setText("0");
            }
            if (b58.getText().toString().length() == 0){
                b58.setText("0");
            }
            if (b59.getText().toString().length() == 0){
                b59.setText("0");
            }
            if (b60.getText().toString().length() == 0){
                b60.setText("0");
            }
            if (b61.getText().toString().length() == 0){
                b61.setText("0");
            }
            if (b62.getText().toString().length() == 0){
                b62.setText("0");
            }
            if (b63.getText().toString().length() == 0){
                b63.setText("0");
            }
            if (b64.getText().toString().length() == 0){
                b64.setText("0");
            }
            if (b65.getText().toString().length() == 0){
                b65.setText("0");
            }
            if (b66.getText().toString().length() == 0){
                b66.setText("0");
            }
            if (b67.getText().toString().length() == 0){
                b67.setText("0");
            }
            if (b68.getText().toString().length() == 0){
                b68.setText("0");
            }
            if (b69.getText().toString().length() == 0){
                b69.setText("0");
            }
            if (b70.getText().toString().length() == 0){
                b70.setText("0");
            }
            if (b71.getText().toString().length() == 0){
                b71.setText("0");
            }
            if (b72.getText().toString().length() == 0){
                b72.setText("0");
            }
            if (b73.getText().toString().length() == 0){
                b73.setText("0");
            }
            if (b74.getText().toString().length() == 0){
                b74.setText("0");
            }
            if (b75.getText().toString().length() == 0){
                b75.setText("0");
            }
            if (b76.getText().toString().length() == 0){
                b76.setText("0");
            }
            if (b77.getText().toString().length() == 0){
                b77.setText("0");
            }
            if (b78.getText().toString().length() == 0){
                b78.setText("0");
            }
            if (b79.getText().toString().length() == 0){
                b79.setText("0");
            }
            if (b80.getText().toString().length() == 0){
                b80.setText("0");
            }
                int nmbang1 = 0;
                int nmbang2 = 0;
                int nmbang3 = 0;
                int nmbang4 = 0;
                int nmbang5 = 0;
                int nmbang6 = 0;
                int nmbang7 = 0;
                int nmbang8 = 0;
                int nmbang9 = 0;
                int nmbang10 = 0;
                int nmbang11 = 0;
                int nmbang12 = 0;
                int nmbang13 = 0;
                int nmbang14 = 0;
                int nmbang15 = 0;
                int nmbang16 = 0;
                int nmbang17 = 0;
                int nmbang18 = 0;
                int nmbang19 = 0;
                int nmbang20 = 0;
                int nmbang21 = 0;
                int nmbang22 = 0;
                int nmbang23 = 0;
                int nmbang24 = 0;
                int nmbang25 = 0;
                int nmbang26 = 0;
                int nmbang27 = 0;
                int nmbang28 = 0;
                int nmbang29 = 0;
                int nmbang30 = 0;
                int nmbang31 = 0;
                int nmbang32 = 0;
                int nmbang33 = 0;
                int nmbang34 = 0;
                int nmbang35 = 0;
                int nmbang36 = 0;
                int nmbang37 = 0;
                int nmbang38 = 0;
                int nmbang39 = 0;
                int nmbang40 = 0;
                int nmbang41 = 0;
                int nmbang42 = 0;
                int nmbang43 = 0;
                int nmbang44 = 0;
                int nmbang45 = 0;
                int nmbang46 = 0;
                int nmbang47 = 0;
                int nmbang48 = 0;
                int nmbang49 = 0;
                int nmbang50 = 0;
                int nmbang51 = 0;
                int nmbang52 = 0;
                int nmbang53 = 0;
                int nmbang54 = 0;
                int nmbang55 = 0;
                int nmbang56 = 0;
                int nmbang57 = 0;
                int nmbang58 = 0;
                int nmbang59 = 0;
                int nmbang60 = 0;
                int nmbang61 = 0;
                int nmbang62 = 0;
                int nmbang63 = 0;
                int nmbang64 = 0;
                int nmbang65 = 0;
                int nmbang66 = 0;
                int nmbang67 = 0;
                int nmbang68 = 0;
                int nmbang69 = 0;
                int nmbang70 = 0;
                int nmbang71 = 0;
                int nmbang72 = 0;
                int nmbang73 = 0;
                int nmbang74 = 0;
                int nmbang75 = 0;
                int nmbang76 = 0;
                int nmbang77 = 0;
                int nmbang78 = 0;
                int nmbang79 = 0;
                int nmbang80 = 0;
                int kq1 = 0;
                int kq2 = 0;
                int kq3 = 0;
                int kq4 = 0;
                int kq5 = 0;
                int kq6 = 0;
                int kq7 = 0;
                int kq8 = 0;
                int kq9 = 0;
                int kq10 = 0;
                int kq11 = 0;
                int kq12 = 0;
                int kq13 = 0;
                int kq14 = 0;
                int kq15 = 0;
                int kq16 = 0;
                int chia = 0;
                int chia2 = 0;
                for (int i = 0; i < stg.length; i++) {
                    if (i == 0) {
                            if((b1.getText().toString()) != stg[i] ){
                                nmbang1 = Integer.parseInt(b1.getText().toString());
                            }
                            else{
                                String regexStr = "^[0-9]*$";

                                if(b1.getText().toString().matches(regexStr))
                                {
                                    //write code here for success
                                    nmbang1 = Integer.parseInt(stg[i]);
                                }
                                else{
                                    nmbang1 = 0;
                                    b1.setText("0");
                                }
                            }
                            if(nmbang1 != 0){
                                chia++;
                            }
                    } else if (i == 1) {
                            if (b2.getText().toString() != stg[i]) {
                                nmbang2 = Integer.parseInt(b2.getText().toString());
                            } else {
                                nmbang2 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang2 != 0){
                            chia++;
                        }
                    } else if (i == 2) {
                            if (b3.getText().toString() != stg[i]) {
                                nmbang3 = Integer.parseInt(b3.getText().toString());
                            } else {
                                nmbang3 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang3 != 0){
                            chia++;
                        }
                    } else if (i == 3) {
                            if(b4.getText().toString() != stg[i]){
                                nmbang4 = Integer.parseInt(b4.getText().toString());
                            }
                            else{
                                nmbang4 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang4 != 0){
                            chia++;
                        }
                    } else if (i == 4) {
                            if(b5.getText().toString() != stg[i]){
                                nmbang5 = Integer.parseInt(b5.getText().toString());
                            }
                            else{
                                nmbang5 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang5 != 0){
                            chia++;
                        }
                    } else if (i == 5) {
                            if(b6.getText().toString() != stg[i]){
                                nmbang6 = Integer.parseInt(b6.getText().toString());
                            }
                            else{
                                nmbang6 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang6 != 0){
                            chia2++;
                        }
                    } else if (i == 6) {
                            if(b7.getText().toString() != stg[i]){
                                nmbang7 = Integer.parseInt(b7.getText().toString());
                            }
                            else{
                                nmbang7 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang7 != 0){
                            chia2++;
                        }
                    } else if (i == 7) {
                            if(b8.getText().toString() != stg[i]){
                                nmbang8 = Integer.parseInt(b8.getText().toString());
                            }
                            else{
                                nmbang8 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang8 != 0){
                            chia2++;
                        }
                    } else if (i == 8) {
                            if(b9.getText().toString() != stg[i]){
                                nmbang9 = Integer.parseInt(b9.getText().toString());
                            }
                            else{
                                nmbang9 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang9 != 0){
                            chia2++;
                        }
                    } else if (i == 9) {
                            if(b10.getText().toString() != stg[i]){
                                nmbang10 = Integer.parseInt(b10.getText().toString());
                            }
                            else{
                                nmbang10 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang10 != 0){
                            chia2++;
                        }
                    } else if (i == 10) {
                            if(b11.getText().toString() != stg[i]){
                                nmbang11 = Integer.parseInt(b11.getText().toString());
                            }
                            else{
                                nmbang11 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang11 != 0){
                            chia++;
                        }
                    } else if (i == 11) {
                            if(b12.getText().toString() != stg[i]){
                                nmbang12 = Integer.parseInt(b12.getText().toString());
                            }
                            else{
                                nmbang12 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang12 != 0){
                            chia++;
                        }
                    } else if (i == 12) {
                            if(b13.getText().toString() != stg[i]){
                                nmbang13 = Integer.parseInt(b13.getText().toString());
                            }
                            else{
                                nmbang13 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang13 != 0){
                            chia++;
                        }
                    } else if (i == 13) {
                            if(b14.getText().toString() != stg[i]){
                                nmbang14 = Integer.parseInt(b14.getText().toString());
                            }
                            else{
                                nmbang14 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang14 != 0){
                            chia++;
                        }
                    } else if (i == 14) {
                            if(b15.getText().toString() != stg[i]){
                                nmbang15 = Integer.parseInt(b15.getText().toString());
                            }
                            else{
                                nmbang15 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang15 != 0){
                            chia++;
                        }
                    } else if (i == 15) {
                            if(b16.getText().toString() != stg[i]){
                                nmbang16 = Integer.parseInt(b16.getText().toString());
                            }
                            else{
                                nmbang16 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang16 != 0){
                            chia2++;
                        }
                    } else if (i == 16) {
                            if(b17.getText().toString() != stg[i]){
                                nmbang17 = Integer.parseInt(b17.getText().toString());
                            }
                            else{
                                nmbang17 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang17 != 0){
                            chia2++;
                        }
                    } else if (i == 17) {
                            if(b18.getText().toString() != stg[i]){
                                nmbang18 = Integer.parseInt(b18.getText().toString());
                            }
                            else{
                                nmbang18 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang18 != 0){
                            chia2++;
                        }
                    } else if (i == 18) {
                            if(b19.getText().toString() != stg[i]){
                                nmbang19 = Integer.parseInt(b19.getText().toString());
                            }
                            else{
                                nmbang19 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang19 != 0){
                            chia2++;
                        }
                    } else if (i == 19) {
                            if(b20.getText().toString() != stg[i]){
                                nmbang20 = Integer.parseInt(b20.getText().toString());
                            }
                            else{
                                nmbang20 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang20 != 0){
                            chia2++;
                        }
                    } else if (i == 20) {
                            if(b21.getText().toString() != stg[i]){
                                nmbang21 = Integer.parseInt(b21.getText().toString());
                            }
                            else{
                                nmbang21 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang21 != 0){
                            chia++;
                        }
                    } else if (i == 21) {
                            if(b22.getText().toString() != stg[i]){
                                nmbang22 = Integer.parseInt(b22.getText().toString());
                            }
                            else{
                                nmbang22 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang22 != 0){
                            chia++;
                        }
                    } else if (i == 22) {
                            if(b23.getText().toString() != stg[i]){
                                nmbang23 = Integer.parseInt(b23.getText().toString());
                            }
                            else{
                                nmbang23 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang23 != 0){
                            chia++;
                        }
                    } else if (i == 23) {
                            if(b24.getText().toString() != stg[i]){
                                nmbang24 = Integer.parseInt(b24.getText().toString());
                            }
                            else{
                                nmbang24 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang24 != 0){
                            chia++;
                        }
                    } else if (i == 24) {
                            if(b25.getText().toString() != stg[i]){
                                nmbang25 = Integer.parseInt(b25.getText().toString());
                            }
                            else{
                                nmbang25 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang25 != 0){
                            chia++;
                        }
                    } else if (i == 25) {
                            if(b26.getText().toString() != stg[i]){
                                nmbang26 = Integer.parseInt(b26.getText().toString());
                            }
                            else{
                                nmbang26 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang26 != 0){
                            chia2++;
                        }
                    } else if (i == 26) {
                            if(b27.getText().toString() != stg[i]){
                                nmbang27 = Integer.parseInt(b27.getText().toString());
                            }
                            else{
                                nmbang27 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang27 != 0){
                            chia2++;
                        }
                    } else if (i == 27) {
                            if(b28.getText().toString() != stg[i]){
                                nmbang28 = Integer.parseInt(b28.getText().toString());
                            }
                            else{
                                nmbang28 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang28 != 0){
                            chia2++;
                        }
                    } else if (i == 28) {
                            if(b29.getText().toString() != stg[i]){
                                nmbang29 = Integer.parseInt(b29.getText().toString());
                            }
                            else{
                                nmbang29 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang29 != 0){
                            chia2++;
                        }
                    } else if (i == 29) {
                            if(b30.getText().toString() != stg[i]){
                                nmbang30 = Integer.parseInt(b30.getText().toString());
                            }
                            else{
                                nmbang30 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang30 != 0){
                            chia2++;
                        }
                    } else if (i == 30) {
                            if(b31.getText().toString() != stg[i]){
                                nmbang31 = Integer.parseInt(b31.getText().toString());
                            }
                            else{
                                nmbang31 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang31 != 0){
                            chia++;
                        }
                    } else if (i == 31) {
                            if(b32.getText().toString() != stg[i]){
                                nmbang32 = Integer.parseInt(b32.getText().toString());
                            }
                            else{
                                nmbang32 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang32 != 0){
                            chia++;
                        }
                    } else if (i == 32) {
                            if(b33.getText().toString() != stg[i]){
                                nmbang33 = Integer.parseInt(b33.getText().toString());
                            }
                            else{
                                nmbang33 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang33 != 0){
                            chia++;
                        }
                    } else if (i == 33) {
                            if(b34.getText().toString() != stg[i]){
                                nmbang34 = Integer.parseInt(b34.getText().toString());
                            }
                            else{
                                nmbang34 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang34 != 0){
                            chia++;
                        }
                    } else if (i == 34) {
                            if(b35.getText().toString() != stg[i]){
                                nmbang35 = Integer.parseInt(b35.getText().toString());
                            }
                            else{
                                nmbang35 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang35 != 0){
                            chia++;
                        }
                    } else if (i == 35) {
                            if(b36.getText().toString() != stg[i]){
                                nmbang36 = Integer.parseInt(b36.getText().toString());
                            }
                            else{
                                nmbang36 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang36 != 0){
                            chia2++;
                        }
                    } else if (i == 36) {
                            if(b37.getText().toString() != stg[i]){
                                nmbang37 = Integer.parseInt(b37.getText().toString());
                            }
                            else{
                                nmbang37 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang37 != 0){
                            chia2++;
                        }
                    } else if (i == 37) {
                            if(b38.getText().toString() != stg[i]){
                                nmbang38 = Integer.parseInt(b38.getText().toString());
                            }
                            else{
                                nmbang38 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang38 != 0){
                            chia2++;
                        }
                    } else if (i == 38) {
                            if(b39.getText().toString() != stg[i]){
                                nmbang39 = Integer.parseInt(b39.getText().toString());
                            }
                            else{
                                nmbang39 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang39 != 0){
                            chia2++;
                        }
                    } else if (i == 39) {
                            if(b40.getText().toString() != stg[i]){
                                nmbang40 = Integer.parseInt(b40.getText().toString());
                            }
                            else{
                                nmbang40 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang40 != 0){
                            chia2++;
                        }
                    } else if (i == 40) {
                            if(b41.getText().toString() != stg[i]){
                                nmbang41 = Integer.parseInt(b41.getText().toString());
                            }
                            else{
                                nmbang41 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang41 != 0){
                            chia++;
                        }
                    } else if (i == 41) {
                            if(b42.getText().toString() != stg[i]){
                                nmbang42 = Integer.parseInt(b42.getText().toString());
                            }
                            else{
                                nmbang42 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang42 != 0){
                            chia++;
                        }
                    } else if (i == 42) {
                            if(b43.getText().toString() != stg[i]){
                                nmbang43 = Integer.parseInt(b43.getText().toString());
                            }
                            else{
                                nmbang43 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang43 != 0){
                            chia++;
                        }
                    } else if (i == 43) {
                            if(b44.getText().toString() != stg[i]){
                                nmbang44 = Integer.parseInt(b44.getText().toString());
                            }
                            else{
                                nmbang44 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang44 != 0){
                            chia++;
                        }
                    } else if (i == 44) {
                            if(b45.getText().toString() != stg[i]){
                                nmbang45 = Integer.parseInt(b45.getText().toString());
                            }
                            else{
                                nmbang45 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang45 != 0){
                            chia++;
                        }
                    } else if (i == 45) {
                            if(b46.getText().toString() != stg[i]){
                                nmbang46 = Integer.parseInt(b46.getText().toString());
                            }
                            else{
                                nmbang46 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang46 != 0){
                            chia2++;
                        }
                    } else if (i == 46) {
                            if(b47.getText().toString() != stg[i]){
                                nmbang47 = Integer.parseInt(b47.getText().toString());
                            }
                            else{
                                nmbang47 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang47 != 0){
                            chia2++;
                        }
                    } else if (i == 47) {
                            if(b48.getText().toString() != stg[i]){
                                nmbang48 = Integer.parseInt(b48.getText().toString());
                            }
                            else{
                                nmbang48 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang48 != 0){
                            chia2++;
                        }
                    } else if (i == 48) {
                            if(b49.getText().toString() != stg[i]){
                                nmbang49 = Integer.parseInt(b49.getText().toString());
                            }
                            else{
                                nmbang49 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang49 != 0){
                            chia2++;
                        }
                    } else if (i == 49) {
                            if(b50.getText().toString() != stg[i]){
                                nmbang50 = Integer.parseInt(b50.getText().toString());
                            }
                            else{
                                nmbang50 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang50 != 0){
                            chia2++;
                        }
                    } else if (i == 50) {
                            if(b51.getText().toString() != stg[i]){
                                nmbang51 = Integer.parseInt(b51.getText().toString());
                            }
                            else{
                                nmbang51 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang51 != 0){
                            chia++;
                        }
                    } else if (i == 51) {
                            if(b52.getText().toString() != stg[i]){
                                nmbang52 = Integer.parseInt(b52.getText().toString());
                            }
                            else{
                                nmbang52 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang52 != 0){
                            chia++;
                        }
                    } else if (i == 52) {
                            if(b53.getText().toString() != stg[i]){
                                nmbang53 = Integer.parseInt(b53.getText().toString());
                            }
                            else{
                                nmbang53 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang53 != 0){
                            chia++;
                        }
                    } else if (i == 53) {
                            if(b54.getText().toString() != stg[i]){
                                nmbang54 = Integer.parseInt(b54.getText().toString());
                            }
                            else{
                                nmbang54 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang54 != 0){
                            chia++;
                        }
                    } else if (i == 54) {
                            if(b55.getText().toString() != stg[i]){
                                nmbang55 = Integer.parseInt(b55.getText().toString());
                            }
                            else{
                                nmbang55 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang55 != 0){
                            chia++;
                        }
                    } else if (i == 55) {
                            if(b56.getText().toString() != stg[i]){
                                nmbang56 = Integer.parseInt(b56.getText().toString());
                            }
                            else{
                                nmbang56 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang56 != 0){
                            chia2++;
                        }
                    } else if (i == 56) {
                            if(b57.getText().toString() != stg[i]){
                                nmbang57 = Integer.parseInt(b57.getText().toString());
                            }
                            else{
                                nmbang57 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang57 != 0){
                            chia2++;
                        }
                    } else if (i == 57) {
                            if(b58.getText().toString() != stg[i]){
                                nmbang58 = Integer.parseInt(b58.getText().toString());
                            }
                            else{
                                nmbang58 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang58 != 0){
                            chia2++;
                        }
                    } else if (i == 58) {
                            if(b59.getText().toString() != stg[i]){
                                nmbang59 = Integer.parseInt(b59.getText().toString());
                            }
                            else{
                                nmbang59 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang59 != 0){
                            chia2++;
                        }
                    } else if (i == 59) {
                            if(b60.getText().toString() != stg[i]){
                                nmbang60 = Integer.parseInt(b60.getText().toString());
                            }
                            else{
                                nmbang60 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang60 != 0){
                            chia2++;
                        }
                    } else if (i == 60) {
                            if(b61.getText().toString() != stg[i]){
                                nmbang61 = Integer.parseInt(b61.getText().toString());
                            }
                            else{
                                nmbang61 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang61 != 0){
                            chia++;
                        }
                    } else if (i == 61) {
                            if(b62.getText().toString() != stg[i]){
                                nmbang62 = Integer.parseInt(b62.getText().toString());
                            }
                            else{
                                nmbang62 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang62 != 0){
                            chia++;
                        }
                    } else if (i == 62) {
                            if(b63.getText().toString() != stg[i]){
                                nmbang63 = Integer.parseInt(b63.getText().toString());
                            }
                            else{
                                nmbang63 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang63 != 0){
                            chia++;
                        }
                    } else if (i == 63) {
                            if(b64.getText().toString() != stg[i]){
                                nmbang64 = Integer.parseInt(b64.getText().toString());
                            }
                            else{
                                nmbang64 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang64 != 0){
                            chia++;
                        }
                    } else if (i == 64) {
                            if(b65.getText().toString() != stg[i]){
                                nmbang65 = Integer.parseInt(b65.getText().toString());
                            }
                            else{
                                nmbang65 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang65 != 0){
                            chia++;
                        }
                    } else if (i == 65) {
                            if(b66.getText().toString() != stg[i]){
                                nmbang66 = Integer.parseInt(b66.getText().toString());
                            }
                            else{
                                nmbang66 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang66 != 0){
                            chia2++;
                        }
                    } else if (i == 66) {
                            if(b67.getText().toString() != stg[i]){
                                nmbang67 = Integer.parseInt(b67.getText().toString());
                            }
                            else{
                                nmbang67 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang67 != 0){
                            chia2++;
                        }
                    } else if (i == 67) {
                            if(b68.getText().toString() != stg[i]){
                                nmbang68 = Integer.parseInt(b68.getText().toString());
                            }
                            else{
                                nmbang68 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang68 != 0){
                            chia2++;
                        }
                    } else if (i == 68) {
                            if(b69.getText().toString() != stg[i]){
                                nmbang69 = Integer.parseInt(b69.getText().toString());
                            }
                            else{
                                nmbang69 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang69 != 0){
                            chia2++;
                        }
                    } else if (i == 69) {
                            if(b70.getText().toString() != stg[i]){
                                nmbang70 = Integer.parseInt(b70.getText().toString());
                            }
                            else{
                                nmbang70 = Integer.parseInt(stg[i]);
                            }
                        if(nmbang70 != 0){
                            chia2++;
                        }
                    } else if (i == 70) {
                            if(b71.getText().toString() != stg[i]){
                                nmbang71 = Integer.parseInt(b71.getText().toString());
                            }
                            else{
                                nmbang71 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang71 != 0){
                            chia++;
                        }
                    } else if (i == 71) {
                            if(b72.getText().toString() != stg[i]){
                                nmbang72 = Integer.parseInt(b72.getText().toString());
                            }
                            else{
                                nmbang72 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang72 != 0){
                            chia++;
                        }
                    } else if (i == 72) {
                            if(b73.getText().toString() != stg[i]){
                                nmbang73 = Integer.parseInt(b73.getText().toString());
                            }
                            else{
                                nmbang73 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang73 != 0){
                            chia++;
                        }
                    } else if (i == 73) {
                            if(b74.getText().toString() != stg[i]){
                                nmbang74 = Integer.parseInt(b74.getText().toString());
                            }
                            else{
                                nmbang74 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang74 != 0){
                            chia++;
                        }
                    } else if (i == 74) {
                            if(b75.getText().toString() != stg[i]){
                                nmbang75 = Integer.parseInt(b75.getText().toString());
                            }
                            else{
                                nmbang75 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang75 != 0){
                            chia++;
                        }
                    } else if (i == 75) {
                            if(b76.getText().toString() != stg[i]){
                                nmbang76 = Integer.parseInt(b76.getText().toString());
                            }
                            else{
                                nmbang76 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang76 != 0){
                            chia2++;
                        }
                    } else if (i == 76) {
                            if(b77.getText().toString() != stg[i]){
                                nmbang77 = Integer.parseInt(b77.getText().toString());
                            }
                            else{
                                nmbang77 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang77 != 0){
                            chia2++;
                        }
                    } else if (i == 77) {
                            if(b78.getText().toString() != stg[i]){
                                nmbang78 = Integer.parseInt(b78.getText().toString());
                            }
                            else{
                                nmbang78 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang78 != 0){
                            chia2++;
                        }
                    } else if (i == 78) {
                            if(b79.getText().toString() != stg[i]){
                                nmbang79 = Integer.parseInt(b79.getText().toString());
                            }
                            else{
                                nmbang79 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang79 != 0){
                            chia2++;
                        }
                    } else if (i == 79) {
                            if(b80.getText().toString() != stg[i]){
                                nmbang80 = Integer.parseInt(b80.getText().toString());
                            }
                            else{
                                nmbang80 = Integer.parseInt(stg[i]);
                        }
                        if(nmbang80 != 0){
                            chia2++;
                        }
                    }
                };
        int tkqnmb1 = 0, tkqnmb2 = 0, tkqnmb3 = 0, tkqnmb4 = 0, tkqnmb5 = 0, tkqnmb6 = 0, tkqnmb7 = 0, tkqnmb8 = 0, tkqnmb9 = 0, tkqnmb10 = 0;

        String regexStr = "^[0-9]*$";

            if((String.valueOf(nmbang1)).trim().matches(regexStr))
            {
                kq1 = nmbang1 + nmbang2 + nmbang3 + nmbang4 + nmbang5;
                tkqnmb1 = nmbang1 + nmbang11 + nmbang21 + nmbang31 + nmbang41 + nmbang51 + nmbang61 + nmbang71;
            }
            else{
                Toast.makeText(bantinh.this, "Dữ liệu không hợp lệ!", Toast.LENGTH_SHORT).show();
            }
            kqtb1.setText(String.valueOf(kq1));
            kq2 = nmbang6 + nmbang7 + nmbang8 + nmbang9 + nmbang10;
            kqtb2.setText(String.valueOf(kq2));
            kq3 = nmbang11 + nmbang12 + nmbang13 + nmbang14 + nmbang15;
            kqtb3.setText(String.valueOf(kq3));
            kq4 = nmbang16 + nmbang17 + nmbang18 + nmbang19 + nmbang20;
            kqtb4.setText(String.valueOf(kq4));
            kq5 = nmbang21 + nmbang22 + nmbang23 + nmbang24 + nmbang25;
            kqtb5.setText(String.valueOf(kq5));
            kq6 = nmbang26 + nmbang27 + nmbang28 + nmbang29 + nmbang30;
            kqtb6.setText(String.valueOf(kq6));
            kq7 = nmbang31 + nmbang32 + nmbang33 + nmbang34 + nmbang35;
            kqtb7.setText(String.valueOf(kq7));
            kq8 = nmbang36 + nmbang37 + nmbang38 + nmbang39 + nmbang40;
            kqtb8.setText(String.valueOf(kq8));
            kq9 = nmbang41 + nmbang42 + nmbang43 + nmbang44 + nmbang45;
            kqtb9.setText(String.valueOf(kq9));
            kq10 = nmbang46 + nmbang47 + nmbang48 + nmbang49 + nmbang50;
            kqtb10.setText(String.valueOf(kq10));
            kq11 = nmbang51 + nmbang52 + nmbang53 + nmbang54 + nmbang55;
            kqtb11.setText(String.valueOf(kq11));
            kq12 = nmbang56 + nmbang57 + nmbang58 + nmbang59 + nmbang60;
            kqtb12.setText(String.valueOf(kq12));
            kq13 = nmbang61 + nmbang62 + nmbang63 + nmbang64 + nmbang65;
            kqtb13.setText(String.valueOf(kq13));
            kq14 = nmbang66 + nmbang67 + nmbang68 + nmbang69 + nmbang70;
            kqtb14.setText(String.valueOf(kq14));
            kq15 = nmbang71 + nmbang72 + nmbang73 + nmbang74 + nmbang75;
            kqtb15.setText(String.valueOf(kq15));
            kq16 = nmbang76 + nmbang77 + nmbang78 + nmbang79 + nmbang80;
            kqtb16.setText(String.valueOf(kq16));


            tkqnmb2 = nmbang2 + nmbang12 + nmbang22 + nmbang32 + nmbang42 + nmbang52 + nmbang62 + nmbang72;
            tkqnmb3 = nmbang3 + nmbang13 + nmbang23 + nmbang33 + nmbang43 + nmbang53 + nmbang63 + nmbang73;
            tkqnmb4 = nmbang4 + nmbang14 + nmbang24 + nmbang34 + nmbang44 + nmbang54 + nmbang64 + nmbang74;
            tkqnmb5 = nmbang5 + nmbang15 + nmbang25 + nmbang35 + nmbang45 + nmbang55 + nmbang65 + nmbang75;
            tkqnmb6 = nmbang6 + nmbang16 + nmbang26 + nmbang36 + nmbang46 + nmbang56 + nmbang66 + nmbang76;
            tkqnmb7 = nmbang7 + nmbang17 + nmbang27 + nmbang37 + nmbang47 + nmbang57 + nmbang67 + nmbang77;
            tkqnmb8 = nmbang8 + nmbang18 + nmbang28 + nmbang38 + nmbang48 + nmbang58 + nmbang68 + nmbang78;
            tkqnmb9 = nmbang9 + nmbang19 + nmbang29 + nmbang39 + nmbang49 + nmbang59 + nmbang69 + nmbang79;
            tkqnmb10 = nmbang10 + nmbang20 + nmbang30 + nmbang40 + nmbang50 + nmbang60 + nmbang70 + nmbang80;
            kqnmb1.setText(String.valueOf(tkqnmb1));
            kqnmb2.setText(String.valueOf(tkqnmb2));
            kqnmb3.setText(String.valueOf(tkqnmb3));
            kqnmb4.setText(String.valueOf(tkqnmb4));
            kqnmb5.setText(String.valueOf(tkqnmb5));
            kqnmb6.setText(String.valueOf(tkqnmb6));
            kqnmb7.setText(String.valueOf(tkqnmb7));
            kqnmb8.setText(String.valueOf(tkqnmb8));
            kqnmb9.setText(String.valueOf(tkqnmb9));
            kqnmb10.setText(String.valueOf(tkqnmb10));
            double kqdtb1 = (double) (tkqnmb1 + tkqnmb2 + tkqnmb3 + tkqnmb4 + tkqnmb5 ) / chia;
            double ltkqdtb1 = Math.round(kqdtb1 * 100) / 100.0;
            dtb1.setText(String.valueOf(ltkqdtb1));
            double kqdtb2 = (double) (tkqnmb6 + tkqnmb7 + tkqnmb8 + tkqnmb9 + tkqnmb10) / chia2;
            double ltkqdtb2 = Math.round(kqdtb2 * 100) / 100.0;
            dtb2.setText(String.valueOf(ltkqdtb2));

            double dkqtongdtb = ((kqdtb2 * 2) + kqdtb1)/3;
            double ltkqtongdtb = Math.round(dkqtongdtb * 100) / 100.0;
            kqtongdtb.setText(String.valueOf(ltkqtongdtb));
            }



    private void getTextFromImage(Bitmap bitmap) {
        TextRecognizer recognizer = new TextRecognizer.Builder(this).build();
        if (!recognizer.isOperational()) {
            Toast.makeText(bantinh.this, "Error Occurred!!!", Toast.LENGTH_SHORT).show();
        } else {
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> textBlockSparseArray = recognizer.detect(frame);
            for (int i = 0; i < textBlockSparseArray.size(); i++) {
                TextBlock textBlock = textBlockSparseArray.valueAt(i);
                stringBuilder.append(textBlock.getValue());
            }
            s = stringBuilder.toString();
            luuketquadatach = "";
            chars = s.toCharArray();
            for (int i = 0; i < chars.length; i++) {
                if ( (chars[i] == 'O' || chars[i] == 'o' ) && chars[i-1] != '1'){
                    luuketquadatach = luuketquadatach + "0 ";
                }
                if ((chars[i] == '1' && chars[i + 1] == '0') || (chars[i] == 'A' && chars[i+1] == '0') || (chars[i] == '4' && chars[i+1] == '0') ) {
                    luuketquadatach = luuketquadatach + "10 ";
                    i++;
                } else if (chars[i] != ' ') {
                    luuketquadatach = luuketquadatach + chars[i] + " ";
                }
            }
            stg = luuketquadatach.split(" ");
            List<String> list = new ArrayList<>(Arrays.asList(stg));
            list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");list.add("0");
            stg = list.toArray(new String[0]);
            for (int i = 0; i < stg.length; i++) {
                String regexStr = "^[0-9]*$";

                if (i == 0) {
                    if(stg[0].matches(regexStr))
                    {
                        b1.setText(stg[0]);
                    }
                    else{
                        b1.setText("10");
                    }
                }
                else if (i == 1) {
                    if(stg[i].matches(regexStr))
                    {
                        b2.setText(stg[i]);
                    }
                    else{
                        b2.setText("10");
                    }
                } else if (i == 2) {
                    if(stg[i].matches(regexStr))
                    {
                        b3.setText(stg[i]);
                    }
                    else{
                        b3.setText("10");
                    }
                } else if (i == 3) {
                    if(stg[i].matches(regexStr))
                    {
                        b4.setText(stg[i]);
                    }
                    else{
                        b4.setText("10");
                    }
                } else if (i == 4) {
                    if(stg[i].matches(regexStr))
                    {
                        b5.setText(stg[i]);
                    }
                    else{
                        b5.setText("10");
                    }
                } else if (i == 5) {
                    if(stg[i].matches(regexStr))
                    {
                        b6.setText(stg[i]);
                    }
                    else{
                        b6.setText("10");
                    }
                } else if (i == 6) {
                    if(stg[i].matches(regexStr))
                    {
                        b7.setText(stg[i]);
                    }
                    else{
                        b7.setText("10");
                    }
                } else if (i == 7) {
                    if(stg[i].matches(regexStr))
                    {
                        b8.setText(stg[i]);
                    }
                    else{
                        b8.setText("10");
                    }
                } else if (i == 8) {
                    if(stg[i].matches(regexStr))
                    {
                        b9.setText(stg[i]);
                    }
                    else{
                        b9.setText("10");
                    }

                } else if (i == 9) {
                    if(stg[i].matches(regexStr))
                    {
                        b10.setText(stg[i]);
                    }
                    else{
                        b10.setText("10");
                    }
                } else if (i == 10) {
                    if(stg[i].matches(regexStr))
                    {
                        b11.setText(stg[i]);
                    }
                    else{
                        b11.setText("10");
                    }

                } else if (i == 11) {
                    if(stg[i].matches(regexStr))
                    {
                        b12.setText(stg[i]);
                    }
                    else{
                        b12.setText("10");
                    }

                } else if (i == 12) {
                    if(stg[i].matches(regexStr))
                    {
                        b13.setText(stg[i]);
                    }
                    else{
                        b13.setText("10");
                    }
                } else if (i == 13) {
                    if(stg[i].matches(regexStr))
                    {
                        b14.setText(stg[i]);
                    }
                    else{
                        b14.setText("10");
                    }
                } else if (i == 14) {
                    if(stg[i].matches(regexStr))
                    {
                        b15.setText(stg[i]);
                    }
                    else{
                        b15.setText("10");
                    }
                } else if (i == 15) {
                    if(stg[i].matches(regexStr))
                    {
                        b16.setText(stg[i]);
                    }
                    else{
                        b16.setText("10");
                    }
                } else if (i == 16) {
                    if(stg[i].matches(regexStr))
                    {
                        b17.setText(stg[i]);
                    }
                    else{
                        b17.setText("10");
                    }
                } else if (i == 17) {
                    if(stg[i].matches(regexStr))
                    {
                        b18.setText(stg[i]);
                    }
                    else{
                        b18.setText("10");
                    }
                } else if (i == 18) {
                    if(stg[i].matches(regexStr))
                    {
                        b19.setText(stg[i]);
                    }
                    else{
                        b19.setText("10");
                    }
                } else if (i == 19) {
                    if(stg[i].matches(regexStr))
                    {
                        b20.setText(stg[i]);
                    }
                    else{
                        b20.setText("10");
                    }
                } else if (i == 20) {
                    if(stg[i].matches(regexStr))
                    {
                        b21.setText(stg[i]);
                    }
                    else{
                        b21.setText("10");
                    }
                } else if (i == 21) {
                    if(stg[i].matches(regexStr))
                    {
                        b22.setText(stg[i]);
                    }
                    else{
                        b22.setText("10");
                    }

                } else if (i == 22) {
                    if(stg[i].matches(regexStr))
                    {
                        b23.setText(stg[i]);
                    }
                    else{
                        b23.setText("10");
                    }
                } else if (i == 23) {
                    if(stg[i].matches(regexStr))
                    {
                        b24.setText(stg[i]);
                    }
                    else{
                        b24.setText("10");
                    }

                } else if (i == 24) {
                    if(stg[i].matches(regexStr))
                    {
                        b25.setText(stg[i]);
                    }
                    else{
                        b25.setText("10");
                    }

                } else if (i == 25) {
                    if(stg[i].matches(regexStr))
                    {
                        b26.setText(stg[i]);
                    }
                    else{
                        b26.setText("10");
                    }
                } else if (i == 26) {
                    if(stg[i].matches(regexStr))
                    {
                        b27.setText(stg[i]);
                    }
                    else{
                        b27.setText("10");
                    }
                } else if (i == 27) {
                    if(stg[i].matches(regexStr))
                    {
                        b28.setText(stg[i]);
                    }
                    else{
                        b28.setText("10");
                    }
                } else if (i == 28) {
                    if(stg[i].matches(regexStr))
                    {
                        b29.setText(stg[i]);
                    }
                    else{
                        b29.setText("10");
                    }
                } else if (i == 29) {
                    if(stg[i].matches(regexStr))
                    {
                        b30.setText(stg[i]);
                    }
                    else{
                        b30.setText("10");
                    }

                } else if (i == 30) {
                    if(stg[i].matches(regexStr))
                    {
                        b31.setText(stg[i]);
                    }
                    else{
                        b31.setText("10");
                    }
                } else if (i == 31) {
                    if(stg[i].matches(regexStr))
                    {
                        b32.setText(stg[i]);
                    }
                    else{
                        b32.setText("10");
                    }
                } else if (i == 32) {
                    if(stg[i].matches(regexStr))
                    {
                        b33.setText(stg[i]);
                    }
                    else{
                        b33.setText("10");
                    }

                } else if (i == 33) {
                    if(stg[i].matches(regexStr))
                    {
                        b34.setText(stg[i]);
                    }
                    else{
                        b34.setText("10");
                    }
                } else if (i == 34) {
                    if(stg[i].matches(regexStr))
                    {
                        b35.setText(stg[i]);
                    }
                    else{
                        b35.setText("10");
                    }
                } else if (i == 35) {
                    if(stg[i].matches(regexStr))
                    {
                        b36.setText(stg[i]);
                    }
                    else{
                        b36.setText("10");
                    }

                } else if (i == 36) {
                    if(stg[i].matches(regexStr))
                    {
                        b37.setText(stg[i]);
                    }
                    else{
                        b37.setText("10");
                    }
                } else if (i == 37) {
                    if(stg[i].matches(regexStr))
                    {
                        b38.setText(stg[i]);
                    }
                    else{
                        b38.setText("10");
                    }
                } else if (i == 38) {
                    if(stg[i].matches(regexStr))
                    {
                        b39.setText(stg[i]);
                    }
                    else{
                        b39.setText("10");
                    }
                } else if (i == 39) {
                    if(stg[i].matches(regexStr))
                    {
                        b40.setText(stg[i]);
                    }
                    else{
                        b40.setText("10");
                    }
                } else if (i == 40) {
                    if(stg[i].matches(regexStr))
                    {
                        b41.setText(stg[i]);
                    }
                    else{
                        b41.setText("10");
                    }
                } else if (i == 41) {
                    if(stg[i].matches(regexStr))
                    {
                        b42.setText(stg[i]);
                    }
                    else{
                        b42.setText("10");
                    }
                } else if (i == 42) {
                    if(stg[i].matches(regexStr))
                    {
                        b43.setText(stg[i]);
                    }
                    else{
                        b43.setText("10");
                    }
                } else if (i == 43) {
                    if(stg[i].matches(regexStr))
                    {
                        b44.setText(stg[i]);
                    }
                    else{
                        b44.setText("10");
                    }
                } else if (i == 44) {
                    if(stg[i].matches(regexStr))
                    {
                        b45.setText(stg[i]);
                    }
                    else{
                        b45.setText("10");
                    }
                } else if (i == 45) {
                    if(stg[i].matches(regexStr))
                    {
                        b46.setText(stg[i]);
                    }
                    else{
                        b46.setText("10");
                    }
                } else if (i == 46) {
                    if(stg[i].matches(regexStr))
                    {
                        b47.setText(stg[i]);
                    }
                    else{
                        b47.setText("10");
                    }
                } else if (i == 47) {
                    if(stg[i].matches(regexStr))
                    {
                        b48.setText(stg[i]);
                    }
                    else{
                        b48.setText("10");
                    }
                } else if (i == 48) {
                    if(stg[i].matches(regexStr))
                    {
                        b49.setText(stg[i]);
                    }
                    else{
                        b49.setText("10");
                    }
                } else if (i == 49) {
                    if(stg[i].matches(regexStr))
                    {
                        b50.setText(stg[i]);
                    }
                    else{
                        b50.setText("10");
                    }
                } else if (i == 50) {
                    if(stg[i].matches(regexStr))
                    {
                        b51.setText(stg[i]);
                    }
                    else{
                        b51.setText("10");
                    }
                } else if (i == 51) {
                    if(stg[i].matches(regexStr))
                    {
                        b52.setText(stg[i]);
                    }
                    else{
                        b52.setText("10");
                    }
                } else if (i == 52) {
                    if(stg[i].matches(regexStr))
                    {
                        b53.setText(stg[i]);
                    }
                    else{
                        b53.setText("10");
                    }
                } else if (i == 53) {
                    if(stg[i].matches(regexStr))
                    {
                        b54.setText(stg[i]);
                    }
                    else{
                        b54.setText("10");
                    }
                } else if (i == 54) {
                    if(stg[i].matches(regexStr))
                    {
                        b55.setText(stg[i]);
                    }
                    else{
                        b55.setText("10");
                    }
                } else if (i == 55) {
                    if(stg[i].matches(regexStr))
                    {
                        b56.setText(stg[i]);
                    }
                    else{
                        b56.setText("10");
                    }
                } else if (i == 56) {
                    if(stg[i].matches(regexStr))
                    {
                        b57.setText(stg[i]);
                    }
                    else{
                        b57.setText("10");
                    }
                } else if (i == 57) {
                    if(stg[i].matches(regexStr))
                    {
                        b58.setText(stg[i]);
                    }
                    else{
                        b58.setText("10");
                    }
                } else if (i == 58) {
                    if(stg[i].matches(regexStr))
                    {
                        b59.setText(stg[i]);
                    }
                    else{
                        b59.setText("10");
                    }
                } else if (i == 59) {
                    if(stg[i].matches(regexStr))
                    {
                        b60.setText(stg[i]);
                    }
                    else{
                        b60.setText("10");
                    }
                } else if (i == 60) {
                    if(stg[i].matches(regexStr))
                    {
                        b61.setText(stg[i]);
                    }
                    else{
                        b61.setText("10");
                    }
                } else if (i == 61) {
                    if(stg[i].matches(regexStr))
                    {
                        b62.setText(stg[i]);
                    }
                    else{
                        b62.setText("10");
                    }
                } else if (i == 62) {                    if(stg[i].matches(regexStr))

                {
                        b63.setText(stg[i]);
                    }
                    else{
                        b63.setText("10");
                    }
                } else if (i == 63) {                    if(stg[i].matches(regexStr))

                {
                        b64.setText(stg[i]);
                    }
                    else{
                        b64.setText("10");
                    }
                } else if (i == 64) {                    if(stg[i].matches(regexStr))

                {
                        b65.setText(stg[i]);
                    }
                    else{
                        b65.setText("10");
                    }
                } else if (i == 65) {                    if(stg[i].matches(regexStr))

                {
                        b66.setText(stg[i]);
                    }
                    else{
                        b66.setText("10");
                    }
                } else if (i == 66) {                    if(stg[i].matches(regexStr))

                {
                        b67.setText(stg[i]);
                    }
                    else{
                        b67.setText("10");
                    }
                } else if (i == 67) {                    if(stg[i].matches(regexStr))

                {
                        b68.setText(stg[i]);
                    }
                    else{
                        b68.setText("10");
                    }
                } else if (i == 68) {                    if(stg[i].matches(regexStr))

                {
                        b69.setText(stg[i]);
                    }
                    else{
                        b69.setText("10");
                    }
                } else if (i == 69) {                    if(stg[i].matches(regexStr))

                {
                        b70.setText(stg[i]);
                    }
                    else{
                        b70.setText("10");
                    }
                } else if (i == 70) {                    if(stg[i].matches(regexStr))

                {
                        b71.setText(stg[i]);
                    }
                    else{
                        b71.setText("10");
                    }
                } else if (i == 71) {                    if(stg[i].matches(regexStr))

                {
                        b72.setText(stg[i]);
                    }
                    else{
                        b72.setText("10");
                    }
                } else if (i == 72) {                    if(stg[i].matches(regexStr))

                {
                        b73.setText(stg[i]);
                    }
                    else{
                        b73.setText("10");
                    }
                } else if (i == 73) {                    if(stg[i].matches(regexStr))

                {
                        b74.setText(stg[i]);
                    }
                    else{
                        b74.setText("10");
                    }
                } else if (i == 74) {                    if(stg[i].matches(regexStr))

                {
                        b75.setText(stg[i]);
                    }
                    else{
                        b75.setText("10");
                    }
                } else if (i == 75) {                    if(stg[i].matches(regexStr))

                {
                        b76.setText(stg[i]);
                    }
                    else{
                        b76.setText("10");
                    }
                } else if (i == 76) {                    if(stg[i].matches(regexStr))

                {
                        b77.setText(stg[i]);
                    }
                    else{
                        b77.setText("10");
                    }
                } else if (i == 77) {                    if(stg[i].matches(regexStr))

                {
                        b78.setText(stg[i]);
                    }
                    else{
                        b78.setText("10");
                    }
                } else if (i == 78) {                    if(stg[i].matches(regexStr))

                {
                        b79.setText(stg[i]);
                    }
                    else{
                        b79.setText("10");
                    }
                } else if (i == 79) {                    if(stg[i].matches(regexStr))

                {
                        b80.setText(stg[i]);
                    }
                    else{
                        b80.setText("10");
                    }
                }
            };
            tv_data.setText(stringBuilder.toString());
            btn_capture.setText("Retake");
            btn_capnhat.setVisibility(View.VISIBLE);
        }
    }


}
