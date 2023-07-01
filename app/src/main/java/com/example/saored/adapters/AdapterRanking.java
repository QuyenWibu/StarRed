package com.example.saored.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saored.R;
import com.example.saored.models.ModelRaking;


import java.util.ArrayList;

//Create a custom adapter for the RecyclerView
public class AdapterRanking extends RecyclerView.Adapter<AdapterRanking.MyViewHolder>{

    Context context;
    ArrayList<ModelRaking> list;

    public AdapterRanking(Context context, ArrayList<ModelRaking> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_ranking, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelRaking user = list.get(position);
        holder.Class.setText(user.getLop());
        holder.score.setText(String.valueOf(user.getScores()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder{
            TextView Class, score;
    public MyViewHolder(@NonNull View view) {
        super(view);
        Class = view.findViewById(R.id.Class);
        score = view.findViewById(R.id.score);
    }
}

}


