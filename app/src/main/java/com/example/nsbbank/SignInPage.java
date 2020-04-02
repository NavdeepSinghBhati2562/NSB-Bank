package com.example.nsbbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignInPage extends AppCompatActivity {
DatabaseManager manager;
Button okBtn;
EditText enterPass,enterAcc;
ArrayList<SuitCase> arrdata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        okBtn=findViewById(R.id.okBtn);
        enterPass=findViewById(R.id.enterPass);
        enterAcc=findViewById(R.id.accEdt);

        manager=DatabaseManager.getDB(this);

        if(!manager.checkDB())
        {
            manager.createDB(this);

        }
        manager.openDB();
        arrdata=manager.gettingData();

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String acc = enterAcc.getText().toString();
                String mypassword = enterPass.getText().toString();
               if(manager.Authenticating(acc, mypassword)){
                   Toast.makeText(SignInPage.this, "YOU Signed In", Toast.LENGTH_SHORT).show();

                   Intent intent=new Intent(SignInPage.this,Transcation.class);
                   intent.putExtra("accNum",acc);
                   intent.putExtra("passEntered",mypassword);
                   startActivity(intent);
                   finish();
               } else {
                    Toast.makeText(SignInPage.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }


            }
        });



    }
}
