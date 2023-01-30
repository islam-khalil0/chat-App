package com.example.updatchatapp;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapterUsers extends RecyclerView.Adapter<RecyclerViewAdapterUsers.ViewHolder> {



    Context context ;
    ArrayList <User> userArrayList_Rv_Main = new ArrayList<>();
    OnClickListener onClickListener ;


    public RecyclerViewAdapterUsers(Context context, ArrayList<User> userArrayList_Rv_Main, OnClickListener onClickListener) {
        this.context = context;
        this.userArrayList_Rv_Main = userArrayList_Rv_Main;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterUsers.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_rv_users_new,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder ;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterUsers.ViewHolder holder, int position) {
        User user = userArrayList_Rv_Main.get(position);
        holder.name_user.setText(user.getUserName());



    }

    @Override
    public int getItemCount() {
        return userArrayList_Rv_Main.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img_pro ;
        TextView name_user ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_pro = (CircleImageView) itemView.findViewById(R.id.photo_profile_user);
            name_user = (TextView) itemView.findViewById(R.id.name_user);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickListener.OnItemClickListener(getAdapterPosition());
                }
            });


        }
    }
}
