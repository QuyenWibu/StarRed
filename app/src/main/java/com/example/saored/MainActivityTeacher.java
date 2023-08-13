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
import android.view.MenuItem;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.saored.Fragment.ChangePasswordFragment;
import com.example.saored.Fragment.ChatListFragment;
import com.example.saored.Fragment.RankingFragment;
import com.example.saored.Fragment.UsersFragment;
import com.example.saored.Fragment.homeAdminFragment;
import com.example.saored.Fragment.homeFragment;
import com.example.saored.Fragment.homeTeacherFragment;
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

public class MainActivityTeacher extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase;
    public  StorageReference storageReference;
    private FirebaseUser user;
    public static String SHARED_PREFS = "sharedPrefs";
    DrawerLayout drawerLayout;
    FirebaseAuth mAuth;
    public static final int MY_REQUEST_CODE = 10;
    private CircleImageView imgAvatar;
    private TextView tvname, tvclass;
    BottomNavigationView bottomNavigationView;
    Toolbar toolbar;
    ActionBar actionBar;
    private NavigationView mNavigationView;
    FragmentManager fragmentManager;
    String mUID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);

        mNavigationView = findViewById(R.id.Nav_view);
        imgAvatar = mNavigationView.getHeaderView(0).findViewById(R.id.img_avatar);
        tvname = mNavigationView.getHeaderView(0).findViewById(R.id.tvName);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);

        bottomNavigationView = findViewById(R.id.bottom_navigation2);
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

                    tvname.setText(name);
                    try {
                        Glide.with(MainActivityTeacher.this).load(image).placeholder(R.drawable.ic_avatar).into(imgAvatar);
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
                    openFragment(new homeFragment());
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
            startActivity(new Intent(MainActivityTeacher.this, EditProfileTeacher.class));
        } else if (itemId == R.id.changepass) {
            openFragment(new ChangePasswordFragment());
        } else if (itemId == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(MainActivityTeacher.this, login.class));
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
