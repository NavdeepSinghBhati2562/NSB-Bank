package com.example.nsbbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class User extends AppCompatActivity {
Button signInBtn,signUpBtn;
ImageView adBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        signInBtn=findViewById(R.id.signInBtn);
        signUpBtn=findViewById(R.id.signUpBtn);
        adBtn=findViewById(R.id.adBtn);

        adBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User.this,SignUpPage.class);
                startActivity(intent);
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User.this,SignInPage.class);
                startActivity(intent);
            }
        });


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(User.this,SignUpPage.class);
                startActivity(intent);
            }
        });



    }
}
