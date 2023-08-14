package com.example.saored.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.notification.NotificationListenerService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.saored.R;
import com.example.saored.adapters.AdapterRanking;
import com.example.saored.models.ModelRaking;
import com.firebase.ui.database.FirebaseArray;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class RankingFragment extends Fragment {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    AdapterRanking adapterRanking;
    ArrayList<ModelRaking> list;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ranking, container, false);
        recyclerView = v.findViewById(R.id.rankingRecyclerView);
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapterRanking = new AdapterRanking(getActivity(), list);
        recyclerView.setAdapter(adapterRanking);
        Query query = databaseReference.orderByChild("isAdmin").equalTo("0");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    ModelRaking ranking = userSnapshot.getValue(ModelRaking.class);
                    list.add(0,ranking);
                }
                Collections.sort(list, new Comparator<ModelRaking>() {
                    @Override
                    public int compare(ModelRaking o1, ModelRaking o2) {
                        // Sắp xếp theo thứ tự giảm dần của điểm số
                        return Double.compare(o2.getScores(), o1.getScores());
                    }
                });
                adapterRanking.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Xử lý lỗi nếu có
            }
        });
        return v;
    }


    }





