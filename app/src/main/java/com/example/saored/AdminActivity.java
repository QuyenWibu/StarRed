package com.example.saored;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.saored.Fragment.ChangePasswordFragment;
import com.example.saored.Fragment.ChatListFragment;
import com.example.saored.Fragment.RankingFragment;
import com.example.saored.Fragment.UsersFragment;
import com.example.saored.Fragment.homeAdminFragment;
import com.example.saored.Fragment.homeFragment;
import com.example.saored.Fragment.lichFragment;
import com.example.saored.notification.Token;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    public  StorageReference storageReference;
    private FirebaseUser user;
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    public static final int MY_REQUEST_CODE = 10;
    private CircleImageView imgAvatar;
    private TextView tvname, tvEmail;
    String mUID;
    public static String SHARED_PREFS = "sharedPrefs";

    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    ActionBar actionBar;
    private NavigationView mNavigationView;
    FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        mNavigationView = findViewById(R.id.Nav_view);
        imgAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvname = mNavigationView.getHeaderView(0).findViewById(R.id.tvName);
        tvEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tvEmail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation1);
        bottomNavigationView.setBackground(null);

        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Users");

        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = "" + ds.child("name").getValue();
                    String image = "" + ds.child("image").getValue();
                    String email = "" + ds.child("email").getValue();

                    tvname.setText(name);
                    tvEmail.setText(email);
                    try {
                        Glide.with(AdminActivity.this).load(image).placeholder(R.drawable.ic_avatar).into(imgAvatar);
                    } catch (Exception e) {
                        Picasso.get().load(R.drawable.ic_avatar).into(imgAvatar);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if(itemId == R.id.home_nav){
                    openFragment(new homeAdminFragment());
                    return true;
                }
                 else if (itemId == R.id.rank_nav) {
                    openFragment(new RankingFragment());
                    return true;
                } else if (itemId == R.id.Users_nav) {
                      openFragment(new UsersFragment());
                      return true;}
                 else if (itemId == R.id.chat_nav) {
                    openFragment(new ChatListFragment());
                    return true;
                } else if (itemId == R.id.adduser_nav) {
                    startActivity(new Intent(AdminActivity.this, resgiter.class));
                    finishAffinity();
                    return true;
                }

                return false;
            }
        });
        fragmentManager = getSupportFragmentManager();
        openFragment(new homeAdminFragment());


        checkUserStatus();

    }

    @Override
    protected void onStart() {
        checkUserStatus();
        super.onStart();
    }

    @Override
    protected void onResume() {
        checkUserStatus();
        super.onResume();
    }

    private void updateToken(String token){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Tokens");
        Token mToken = new Token(token);
        ref.child(mUID).setValue(mToken);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_myprofile) {
            startActivity(new Intent(AdminActivity.this, EditProfileAdmin.class));
        } else if (itemId == R.id.changepass) {
            openFragment(new ChangePasswordFragment());
        } else if (itemId == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(AdminActivity.this, login.class));
            finish();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        super.onBackPressed();
    }

    private void openFragment(Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
    private void checkUserStatus(){
        user = mAuth.getCurrentUser();
        if (user != null){
            mUID = user.getUid();
            SharedPreferences sp = getSharedPreferences("SP_USER", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("Current_USERID", mUID);
            editor.apply();

            FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
                @Override
                public void onComplete(@NonNull Task<String> task) {
                    if (!task.isSuccessful()) {
                        Log.w("FCM Token", "Fetching FCM registration token failed", task.getException());
                        return;
                    }

                    // Lấy token thành công
                    String token = task.getResult();
                    updateToken(token);
                }
            });
        } else {

        }

    }

}






//    public void openGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        mActivityResultLauncher.launch(Intent.createChooser(intent, "Hãy chọn hình ảnh"));
//    }














//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        setHasOptionsMenu(true);
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public  void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
//        inflater.inflate(R.menu.menu_nav3, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected (MenuItem item){
//        int id = item.getItemId();
//        if (id == R.id.action_search){
//
//        }
//        return  super.onOptionsItemSelected(item);
//    }

//
//    public void openGallery() {
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//        mActivityResultLauncher.launch(Intent.createChooser(intent, "Chọn hình ảnh"));
//    }
//}



//    private ActionBar toolbar;
//
//
//    public static final int MY_REQUEST_CODE = 10;
//
//    private DrawerLayout mDrawerLayout;
//    FirebaseAuth mAuth;
//    private DatabaseReference databaseReference;
//    private FirebaseDatabase firebaseDatabase;
//    private FirebaseUser user;
//    private static final int FRAGMENT_HOME = 0;
//    private static final int FRAGMENT_LICH = 1;
//    private static final int FRAGMENT_PROFILE = 2;
//    private static final int FRAGMENT_CHANGEPASSWORD = 3;
//
//    private int mCurrentFragment = FRAGMENT_HOME;
//    private NavigationView mNavigationView;
//    private CircleImageView imgAvatar;
//    private TextView tvname, tvemail;
//    private profileFragment mprofileFragment = new profileFragment();
//
////    private ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(
////            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
////                @Override
////                public void onActivityResult(ActivityResult result) {
////                        if (result.getResultCode() == RESULT_OK){
////                            Intent intent = result.getData();
////                            if (result == null){
////                                return;
////                            }
////                            Uri uri = intent.getData();
////                            mprofileFragment.setmUri(uri);
////                            try {
////                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
////                                mprofileFragment.setBitmapImageView(bitmap);
////                            } catch ( IOException e){
////                                e.printStackTrace();
////                            }
////
////
////                        }
////                }
////            });
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.home_nav:
//                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                        homeFragment fragment1 = new homeFragment();
//                        FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
//                        ft1.replace(R.id.content, fragment1, "");
//                        ft1.commit();
//                        break;
//                    case R.id.rank_nav:
//                        Toast.makeText(MainActivity.this, "Ranhking", Toast.LENGTH_SHORT).show();
//                        TopFragment fragment2 = new TopFragment();
//                        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
//                        ft2.replace(R.id.content, fragment2, "");
//                        ft2.commit();
//                        break;
//                    case R.id.bantinh_nav:
//                        Toast.makeText(MainActivity.this, "Tính toán điểm sao đỏ", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this , bantinh.class));
//                        break;
//                    case R.id.chat_nav:
//                        Toast.makeText(MainActivity.this, "Chat", Toast.LENGTH_SHORT).show();
//                        UsersFragment fragment3 = new UsersFragment();
//                        FragmentTransaction ft3 = getSupportFragmentManager().beginTransaction();
//                        ft3.replace(R.id.content, fragment3, "");
//                        ft3.commit();
//                        break;
//                }
//                return true;
//            }
//        });
//
//
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        initUi();
//
//        mDrawerLayout = findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
//
//        mDrawerLayout.addDrawerListener(toggle);
//        toggle.syncState();
//
//        mNavigationView.setNavigationItemSelectedListener(this);
//
//
//        replaceFragment(new homeFragment());
//        mNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
//        showUserInformation();
//    }
//
//    private void initUi() {
//        mNavigationView = findViewById(R.id.Nav_view);
//
//    }
//
//    public void showUserInformation() {
////        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////        if (user == null) {
////            return;
////        }
////        String name = user.getDisplayName();
////        String strclass = user.toString().trim();
////        Uri photoUrl = user.getPhotoUrl();
////
////        tvclass.setText(strclass);
////        tvname.setText(name);
////        Glide.with(this).load(photoUrl).error(R.drawable.ic_avatar).into(imgAvatar);
//        mAuth = FirebaseAuth.getInstance();
//        user = mAuth.getCurrentUser();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        databaseReference = firebaseDatabase.getReference("Users");
//
//        Query query = databaseReference.orderByChild("email").equalTo(user.getEmail());
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds : dataSnapshot.getChildren()) {
//                    String name = "" + ds.child("name").getValue();
//                    String email = "" + ds.child("email").getValue();
//                    String image = "" + ds.child("image").getValue();
//
//                    tvname.setText(name);
//                    tvemail.setText(email);
//                    try {
//                        Picasso.get().load(image).into(imgAvatar);
//                    }
//                    catch (Exception e){
//                        Picasso.get().load(R.drawable.ic_avatar).into(imgAvatar);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    @SuppressLint("SuspiciousIndentation")
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.nav_home) {
//            if (mCurrentFragment != FRAGMENT_HOME)
//                replaceFragment(new homeFragment());
//            mCurrentFragment = FRAGMENT_HOME;
//        } else if (id == R.id.nav_lich) {
//            if (mCurrentFragment != FRAGMENT_LICH)
//                replaceFragment(new lichFragment());
//            mCurrentFragment = FRAGMENT_LICH;
//        } else if (id == R.id.nav_myprofile) {
//            if (mCurrentFragment != FRAGMENT_PROFILE)
//                replaceFragment(mprofileFragment);
//            mCurrentFragment = FRAGMENT_PROFILE;
//        } else if (id == R.id.changepass) {
//            if (mCurrentFragment != FRAGMENT_CHANGEPASSWORD)
//                replaceFragment(new ChangePasswordFragment());
//            mCurrentFragment = FRAGMENT_CHANGEPASSWORD;
//        } else if (id == R.id.logout) {
//            FirebaseAuth.getInstance().signOut();
//            startActivity(new Intent(MainActivity.this, login.class));
//            finish();
//        }
//
//        mDrawerLayout.closeDrawer(GravityCompat.START);
//        return true;
//
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
//            mDrawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    private void replaceFragment(Fragment fragment) {
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.content_frame, fragment);
//        transaction.commit();
//    }
//
