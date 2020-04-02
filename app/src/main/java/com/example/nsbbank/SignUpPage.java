package com.example.nsbbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
Button submitBtn;
EditText nameEdt,phoneEdt,aadharEdt,emailEdt,nomineeEdt,passEdt;
TextView signEdt;
DatabaseManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        submitBtn=findViewById(R.id.submitBtn);
        nameEdt=findViewById(R.id.nameEdt);
        phoneEdt=findViewById(R.id.phoneEdt);
        aadharEdt=findViewById(R.id.aadharEdt);
        emailEdt=findViewById(R.id.emailEdt);
        nomineeEdt=findViewById(R.id.nomineeEdt);
        passEdt=findViewById(R.id.passEdt);
        signEdt=findViewById(R.id.signEdt);

        manager=DatabaseManager.getDB(this);

        if (!manager.checkDB())
        {
            manager.createDB(this);
        }

        manager.openDB();

        signEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUpPage.this,SignInPage.class);
                startActivity(intent);
                finish();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int phoneNo,aadharNo;
                SuitCase suitCase=new SuitCase();

                suitCase.name=""+nameEdt.getText();

                phoneNo= Integer.valueOf(phoneEdt.getText().toString());
                suitCase.phone=phoneNo;

                aadharNo=Integer.parseInt(aadharEdt.getText().toString());
                suitCase.aadharNo=aadharNo;

                suitCase.email=""+emailEdt.getText();
                suitCase.nomineeName=""+nomineeEdt.getText();
                suitCase.password=""+passEdt.getText();

                manager.settingDB(suitCase);
                Toast.makeText(SignUpPage.this, "Account Created Successfully.", Toast.LENGTH_SHORT).show();

                Intent intent=new Intent(SignUpPage.this,show.class);
                intent.putExtra("name",suitCase.name);
                intent.putExtra("account_no",suitCase.AccNo);
                startActivity(intent);
                finish();

            }
        });


    }
}
