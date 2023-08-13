package com.example.saored.adapters;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.saored.AddPostActivity;
import com.example.saored.R;
import com.example.saored.models.ModelPost;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;


import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterPosts extends RecyclerView.Adapter<AdapterPosts.MyHolder>{

    Context context;
    List<ModelPost> postList;
    String myUid;
    public AdapterPosts(Context context, List<ModelPost> postList) {
        this.context = context;
        this.postList = postList;
        myUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_posts, viewGroup, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final String uid = postList.get(position).getUid();
        String uEmail = postList.get(position).getuEmail();
        String uName = postList.get(position).getuName();
        String uDp = postList.get(position).getuDp();
        final String pId = postList.get(position).getpId();
        String pTitle = postList.get(position).getpTitle();
        String pDescription = postList.get(position).getpDescr();
        final  String pImage = postList.get(position).getpImage();
        String pTimeStamp = postList.get(position).getpTime();

        Calendar calendar = Calendar.getInstance(Locale.getDefault());
        calendar.setTimeInMillis(Long.parseLong(pTimeStamp));
        String ptime = DateFormat.format("dd/MM/yyyy hh:mm aa", calendar).toString();

        holder.uNameTv.setText(uName);
        holder.pTimeTv.setText(ptime);
        holder.pTitleTv.setText(pTitle);
        holder.pDescriptionTv.setText(pDescription);
        try{
            Picasso.get().load(uDp).placeholder(R.drawable.ic_avatar).into(holder.uPictureIv);
        } catch (Exception e){

        }

        if(pImage.equals("noImage")){
            holder.pImageIv.setVisibility(View.GONE);
        } else {

            holder.pImageIv.setVisibility(View.VISIBLE);
            try{
                Picasso.get().load(pImage).into(holder.pImageIv);
            } catch (Exception e){

            }
        }
        holder.moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMoreOptions(holder.moreBtn, uid, myUid, pId, pImage);
            }
        });
    }

private void showMoreOptions(ImageButton moreBtn, String uid ,String myUid,final String pId,final String pImage){
    PopupMenu popupMenu = new PopupMenu(context, moreBtn, Gravity.END);
    if(uid.equals(myUid)){
        popupMenu.getMenu().add(Menu.NONE, 0,0,"Delete");
        popupMenu.getMenu().add(Menu.NONE, 1,0,"Edit");
    }
    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int id = item.getItemId();
            if(id == 0){
                beginDelete(pId, pImage);
            } else if(id == 1){
                Intent intent = new Intent(context, AddPostActivity.class);
                intent.putExtra("key", "editPost");
                intent.putExtra("editPostId", pId);
                context.startActivity(intent);
            }
            return false;
        }
    });
    popupMenu.show();
}
private void beginDelete(String pId, String pImage){
        if(pImage.equals("noImage")){
            deleteWithoutImage(pId);
        } else {
            deleteWithImage(pId,pImage);
        }
}
private void deleteWithoutImage(String pId){
    final ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage("Deleting...");

    Query query = FirebaseDatabase.getInstance().getReference("Posts").orderByChild("pId").equalTo(pId);
    query.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot ds: dataSnapshot.getChildren()){
                ds.getRef().removeValue();
            }
            Toast.makeText(context, "Xóa thành công!!!", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    });
}
private void deleteWithImage(String pId, String pImage){
        final ProgressDialog progressDialog = new ProgressDialog(context);
    progressDialog.setMessage("Deleting...");

    StorageReference picRef = FirebaseStorage.getInstance().getReferenceFromUrl(pImage);
    picRef.delete()
            .addOnSuccessListener(new OnSuccessListener<Void>() {
        @Override
        public void onSuccess(Void unused) {
            Query query = FirebaseDatabase.getInstance().getReference("Posts").orderByChild("pId").equalTo(pId);
            query.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds: dataSnapshot.getChildren()){
                            ds.getRef().removeValue();
                        }
                    Toast.makeText(context, "Xóa thành công!!!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            progressDialog.dismiss();
            Toast.makeText(context, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });
}
    @Override
    public int getItemCount() {
        return postList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView uPictureIv, pImageIv;
        TextView uNameTv, pTimeTv, pTitleTv, pDescriptionTv, pLikesTv;
        ImageButton moreBtn;
        Button likeBtn, commentBtn, shareBtn;

        public MyHolder(@NonNull View itemView){
            super(itemView);

            uPictureIv = itemView.findViewById(R.id.uPicture);
            pImageIv = itemView.findViewById(R.id.pImageIv);
            uNameTv = itemView.findViewById(R.id.uNameTv);
            pTimeTv = itemView.findViewById(R.id.uTimeTv);
            pTitleTv = itemView.findViewById(R.id.pTitleTv);
            pDescriptionTv = itemView.findViewById(R.id.pDescriptionTv);
//            pLikesTv = itemView.findViewById(R.id.pLikesTv);
            moreBtn = itemView.findViewById(R.id.morebtn);
//            likeBtn = itemView.findViewById(R.id.likebtn);
//            commentBtn = itemView.findViewById(R.id.commentBtn);
//            shareBtn = itemView.findViewById(R.id.shareBtn);

        }
    }
}
