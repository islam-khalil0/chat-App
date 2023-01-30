package com.example.updatchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_in extends AppCompatActivity {

    TextView tv_create_new_account ;
    EditText email , password ;
    Button sign_in ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

        tv_create_new_account = (TextView) findViewById(R.id.tv_create_new_account);
        email = (EditText) findViewById(R.id.email_in_sign_in);
        password = (EditText) findViewById(R.id.pass_in_sign_in);
        sign_in = (Button) findViewById(R.id.sign_in_button);


/*
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(Sign_in.this,Base_pager.class);
            startActivity(intent);

        }
*/



        tv_create_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_in.this, Sign_up_.class);
                startActivity(intent);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_in();
            }
        });




    }

    public void sign_in (){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Sign_in.this, "Welcome In Chat App", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Sign_in.this,Upload_photo.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(Sign_in.this, "error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });



    }


}