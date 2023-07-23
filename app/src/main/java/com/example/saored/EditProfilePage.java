package com.example.saored;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.namespace.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
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
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class EditProfilePage extends AppCompatActivity {

	private FirebaseAuth firebaseAuth;
	FirebaseUser firebaseUser;
	FirebaseDatabase firebaseDatabase;
	DatabaseReference databaseReference;
	StorageReference storageReference;
	String storagepath = "Users_Profile_Cover_image/";
	String uid;
	ActionBar actionBar;
	ImageView set;
	TextView  editname,edtClass;
	ProgressDialog pd;
	private static final int CAMERA_REQUEST = 100;
	private static final int STORAGE_REQUEST = 200;
	private static final int IMAGEPICK_GALLERY_REQUEST = 300;
	private static final int IMAGE_PICKCAMERA_REQUEST = 400;
	String cameraPermission[];
	String storagePermission[];
	Uri imageuri;
	String profileOrCoverPhoto;
	Button updateProfile;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile_page);

		actionBar = getSupportActionBar();
		actionBar.setDisplayShowHomeEnabled(true);
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("");

		editname = findViewById(R.id.name);
		edtClass = findViewById(R.id.Class);
		set = findViewById(R.id.img_avatar);
		updateProfile = findViewById(R.id.updateButton);
		pd = new ProgressDialog(this);
		pd.setCanceledOnTouchOutside(false);
		firebaseAuth = FirebaseAuth.getInstance();
		firebaseUser = firebaseAuth.getCurrentUser();
		firebaseDatabase = FirebaseDatabase.getInstance();
		storageReference = FirebaseStorage.getInstance().getReference();
		databaseReference = firebaseDatabase.getReference("Users");
		cameraPermission = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
		storagePermission = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
		Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());
		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
					String strname = "" + dataSnapshot1.child("name").getValue();
					String image = "" + dataSnapshot1.child("image").getValue();
					String strclass = "" + dataSnapshot1.child("lop").getValue();


					editname.setText(strname);
					edtClass.setText(strclass);
					try {
						Glide.with(EditProfilePage.this).load(image).into(set);
					} catch (Exception e) {
						Glide.with(EditProfilePage.this).load(R.drawable.ic_avatar).into(set);
					}
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

		updateProfile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pd.setMessage("Updating Profile");
				showEditProfileDialog();
			}
		});
	}

	private void showEditProfileDialog() {
		String options[] = {"Chỉnh sửa ảnh", "Chỉnh sửa tên", "chỉnh sửa lớp trực"};
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Chọn sự thay đổi");
		b.setItems(options, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				if (i == 0){
					pd.setMessage("Cập nhật ảnh đại diện");
					profileOrCoverPhoto = "image";
					showImagePicDialog();
				} else if (i == 1){
					pd.setMessage("Cập nhật tên của bạn");
					showNamephoneupdate("name");
				} else if (i == 2){
					pd.setMessage("Cập nhật lớp của bạn");
					showNamephoneupdate("lop");
				}
			}
		});
		b.create().show();
	}

	@Override
	public boolean onSupportNavigateUp() {
		onBackPressed();
		return super.onSupportNavigateUp();
	}
	@Override
	protected void onPause() {
		super.onPause();
		Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());
		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

					String image = "" + dataSnapshot1.child("image").getValue();

					try {
						Glide.with(EditProfilePage.this).load(image).into(set);
					} catch (Exception e) {
						Glide.with(EditProfilePage.this).load(R.drawable.ic_avatar).into(set);
					}

				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

	}

	@Override
	protected void onStart() {
		super.onStart();
		Query query = databaseReference.orderByChild("email").equalTo(firebaseUser.getEmail());
		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
					String strname = "" + dataSnapshot1.child("name").getValue();
					String image = "" + dataSnapshot1.child("image").getValue();
					String strclass = "" + dataSnapshot1.child("lop").getValue();


					editname.setText(strname);
					edtClass.setText(strclass);
					try {
						Glide.with(EditProfilePage.this).load(image).placeholder(R.drawable.ic_avatar).into(set);
					} catch (Exception e) {
						Picasso.get().load(R.drawable.ic_avatar).into(set);
					}

				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

	}
	private void showNamephoneupdate(String key) {
		AlertDialog.Builder b = new AlertDialog.Builder(this);
		b.setTitle("Cập nhật");
		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		linearLayout.setPadding(10,10,10,10);

		EditText editText = new EditText(this);
		editText.setHint(key);
		linearLayout.addView(editText);

		b.setView(linearLayout);
		b.setPositiveButton("Cập nhật", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
			String value = editText.getText().toString().trim();
			if  (!TextUtils.isEmpty(value)){
				HashMap<String, Object> r = new HashMap<>();
				r.put(key, value);

				databaseReference.child(firebaseUser.getUid()).updateChildren(r).addOnSuccessListener(new OnSuccessListener<Void>() {
					@Override
					public void onSuccess(Void unused) {
						Toast.makeText(getApplicationContext(), "Cập nhật....", Toast.LENGTH_SHORT).show();
					}
				}).addOnFailureListener(new OnFailureListener() {
					@Override
					public void onFailure(@NonNull Exception e) {
						Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();

					}
				});
				} else {
				Toast.makeText(getApplicationContext(), "hãy nhập "+key, Toast.LENGTH_SHORT).show();
				}
			}
		});

		b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				dialogInterface.dismiss();
			}
		});
		b.create().show();
				}






	// Here we are showing image pic dialog where we will select
	// and image either from camera or gallery
	private void showImagePicDialog() {
		String options[] = {"Camera", "Gallery"};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Pick Image From");
		builder.setItems(options, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// if access is not given then we will request for permission
				if (which == 0) {

						pickFromCamera();
					}
				 else if (which == 1) {

						pickFromGallery();
					}
				}
		});
		builder.create().show();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == Activity.RESULT_OK) {
			if (requestCode == IMAGEPICK_GALLERY_REQUEST) {
				imageuri = data.getData();
				uploadProfileCoverPhoto(imageuri);
			}
			if (requestCode == IMAGE_PICKCAMERA_REQUEST) {
				uploadProfileCoverPhoto(imageuri);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		switch (requestCode) {
			case CAMERA_REQUEST: {
				if (grantResults.length > 0) {
					boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
					boolean writeStorageaccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;
					if (camera_accepted && writeStorageaccepted) {
						pickFromCamera();
					} else {
						Toast.makeText(this, "Please Enable Camera and Storage Permissions", Toast.LENGTH_LONG).show();
					}
				}
			}
			break;
			case STORAGE_REQUEST: {
				if (grantResults.length > 0) {
					boolean writeStorageaccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
					if (writeStorageaccepted) {
						pickFromGallery();
					} else {
						Toast.makeText(this, "Please Enable Storage Permissions", Toast.LENGTH_LONG).show();
					}
				}
			}
			break;
		}
	}

	// Here we will click a photo and then go to startactivityforresult for updating data
	private void pickFromCamera() {
		ContentValues contentValues = new ContentValues();
		contentValues.put(MediaStore.Images.Media.TITLE, "Temp_pic");
		contentValues.put(MediaStore.Images.Media.DESCRIPTION, "Temp Description");
		imageuri = this.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
		Intent camerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		camerIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
		startActivityForResult(camerIntent, IMAGE_PICKCAMERA_REQUEST);
	}

	// We will select an image from gallery
	private void pickFromGallery() {
		Intent galleryIntent = new Intent(Intent.ACTION_PICK);
		galleryIntent.setType("image/*");
		startActivityForResult(galleryIntent, IMAGEPICK_GALLERY_REQUEST);
	}

	// We will upload the image from here.
	private void uploadProfileCoverPhoto(final Uri uri) {
		pd.show();
		
		// We are taking the filepath as storagepath + firebaseauth.getUid()+".png"
		String filepathname = storagepath + "" + profileOrCoverPhoto + "_" + firebaseUser.getUid();
		StorageReference storageReference1 = storageReference.child(filepathname);
		storageReference1.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
				Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
				while (!uriTask.isSuccessful()) ;
				
				// We will get the url of our image using uritask
				final Uri downloadUri = uriTask.getResult();
				if (uriTask.isSuccessful()) {
					
					// updating our image url into the realtime database
					HashMap<String, Object> hashMap = new HashMap<>();
					hashMap.put(profileOrCoverPhoto, downloadUri.toString());
					databaseReference.child(firebaseUser.getUid()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
						@Override
						public void onSuccess(Void aVoid) {
							pd.dismiss();
							Toast.makeText(EditProfilePage.this, "Updated", Toast.LENGTH_LONG).show();
						}
					}).addOnFailureListener(new OnFailureListener() {
						@Override
						public void onFailure(@NonNull Exception e) {
							pd.dismiss();
							Toast.makeText(EditProfilePage.this, "Error Updating ", Toast.LENGTH_LONG).show();
						}
					});
				} else {
					pd.dismiss();
					Toast.makeText(EditProfilePage.this, "Error", Toast.LENGTH_LONG).show();
				}
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure(@NonNull Exception e) {
				pd.dismiss();
				Toast.makeText(EditProfilePage.this, "Error", Toast.LENGTH_LONG).show();
			}
		});
	}
}
