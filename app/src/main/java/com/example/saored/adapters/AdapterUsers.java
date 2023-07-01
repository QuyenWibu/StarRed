package com.example.saored.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.saored.R;
import com.example.saored.chat;
import com.example.saored.models.ModelUser;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterUsers extends RecyclerView.Adapter<AdapterUsers.MyHolder>{

    Context context;
    List<ModelUser> userList;

    public AdapterUsers(Context context, List<ModelUser> userList){
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, viewGroup, false);

        return  new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder myholder, int i) {

        String hisuid = userList.get(i).getUid();
        String strImage = userList.get(i).getImage();
        String strName = userList.get(i).getName();
        String strEmail = userList.get(i).getEmail();

        myholder.mNametv.setText(strName);
        myholder.mEmailtv.setText(strEmail);
        try{
            Picasso.get().load(strImage).placeholder(R.drawable.ic_avatar).into(myholder.avatarIv);
        } catch (Exception e){
            Picasso.get().load(R.drawable.ic_avatar).into(myholder.avatarIv);
        }



        myholder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, chat.class);
                intent.putExtra("hisUid", hisuid);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        ImageView avatarIv;
        TextView mNametv, mEmailtv;

        public MyHolder(@NonNull View itemview){
            super(itemview);

            avatarIv =itemview.findViewById(R.id.avatartv);
            mNametv =itemview.findViewById(R.id.nametv);
            mEmailtv =itemview.findViewById(R.id.emailtv);
        }

    }
}
