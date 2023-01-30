package com.example.updatchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Base_pager extends AppCompatActivity implements OnClickListener{

    RecyclerView recyclerView_users ;
    RecyclerViewAdapterUsers recyclerViewAdapterUsers ;
    ArrayList<User> userArrayList = new ArrayList<>();

    SwipeRefreshLayout swipeRefreshLayout ;
    ProgressBar progressBar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_pager);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiper);
        recyclerView_users = (RecyclerView) findViewById(R.id.r_v_users);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        get_users();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                get_users();
                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }

    @Override
    public void OnItemClickListener(int Position) {

    }


    public void get_users (){

        userArrayList.clear();
        FirebaseDatabase.getInstance().getReference("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    userArrayList.add(dataSnapshot.getValue(User.class));
                }

                recyclerViewAdapterUsers = new RecyclerViewAdapterUsers(Base_pager.this,userArrayList,Base_pager.this);
                recyclerView_users.setAdapter(recyclerViewAdapterUsers);
                recyclerView_users.setLayoutManager(new LinearLayoutManager(Base_pager.this));
                recyclerView_users.setHasFixedSize(true);
                recyclerViewAdapterUsers.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                recyclerView_users.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



}