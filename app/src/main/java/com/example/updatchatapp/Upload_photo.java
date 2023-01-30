package com.example.updatchatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Upload_photo extends AppCompatActivity {

    ImageView image_profile ;
    TextView upload_image_btn, skip_btn ;
    Uri image_uri ;
    StorageReference storageReference ;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);

        image_profile = (ImageView) findViewById(R.id.profile_image);
        upload_image_btn = (TextView) findViewById(R.id.upload_img);
        skip_btn = (TextView) findViewById(R.id.skip);



        image_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        upload_image_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload_image();
            }
        });

        skip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Upload_photo.this,Base_pager.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void upload_image() {

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File ...");
        progressDialog.show();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
        Date now = new Date();
        String fileName = dateFormat.format(now);


        storageReference = FirebaseStorage.getInstance().getReference("images/"+fileName);
        storageReference.putFile(image_uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(Upload_photo.this, "Successful", Toast.LENGTH_SHORT).show();

                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

                Intent intent = new Intent(Upload_photo.this, Base_pager.class);
                startActivity(intent);
                finish();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_photo.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();

                if (progressDialog.isShowing()){
                    progressDialog.dismiss();
                }
            }
        });

    }

    private void selectImage() {

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && data != null && data.getData() != null ){
            image_uri = data.getData();
            image_profile.setImageURI(image_uri);

        }
    }

}