package com.example.updatchatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Sign_up_ extends AppCompatActivity {

    EditText username, email , password ;
    Button sign_up_btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        username = (EditText) findViewById(R.id.user_name_in_sign_up);
        email = (EditText) findViewById(R.id.email_in_sign_up);
        password = (EditText) findViewById(R.id.pass_in_sign_up);
        sign_up_btn = (Button) findViewById(R.id.button_sign_up);



        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
                onBackPressed();
            }
        });




    }


    public void sign_up (){

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            FirebaseDatabase.getInstance().getReference("users/"+FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(new User(username.getText().toString(),email.getText().toString(),FirebaseAuth.getInstance().getUid(),password.getText().toString()));

                            Toast.makeText(Sign_up_.this, "successful operation \n please sign in now ", Toast.LENGTH_SHORT).show();


                        }else {
                            Toast.makeText(Sign_up_.this, "error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}