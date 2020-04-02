package com.example.nsbbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class show extends AppCompatActivity {
TextView yourNameEdt,yourAccEdt,loginTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        yourNameEdt=findViewById(R.id.yourNameEdt);
        yourAccEdt=findViewById(R.id.yourAccEdt);
        loginTxt.findViewById(R.id.loginTxt);

        Bundle bundle= getIntent().getExtras();

        yourNameEdt.setText(bundle.getString("name"));
        yourAccEdt.setText(bundle.getString("account_no"));

//        loginTxt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(show.this,SignInPage.class);
//                startActivity(intent);
//                finish();
//            }
//        });


    }
}
