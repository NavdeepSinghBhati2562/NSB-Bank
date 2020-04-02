package com.example.nsbbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Transcation extends AppCompatActivity {
TextView showNameBtn,showAccBtn,showAmountBtn;
EditText amountEdt;
Button creditBtn,debitBtn;
DatabaseManager manager;
String s;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transcation);
        showNameBtn=findViewById(R.id.showNameBtn);
        showAccBtn=findViewById(R.id.showAccBtn);
        showAmountBtn=findViewById(R.id.showAmountBtn);
        amountEdt=findViewById(R.id.amtEdt);
        creditBtn=findViewById(R.id.creditBtn);
        debitBtn=findViewById(R.id.debitBtn);

        manager=DatabaseManager.getDB(this);

        if (!manager.checkDB())
        {
            manager.createDB(this);
        }

        manager.openDB();



        Bundle bundle=getIntent().getExtras();
        showAccBtn.setText(bundle.getString("accNum"));
        final String  passacc=showAccBtn.getText().toString();
        SuitCase suitCase =manager.getAcc(passacc);
        showNameBtn.setText(suitCase.name);
        s=String.valueOf(suitCase.amount);
        showAmountBtn.setText(s);





        creditBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int i = Integer.parseInt(amountEdt.getText().toString());
                int amt=Integer.parseInt(showAmountBtn.getText().toString());
                int set=amt+i;
                String amount=String.valueOf(set);

                manager.updateRecord(passacc,amount);
                Toast.makeText(Transcation.this,"Amount credited",Toast.LENGTH_SHORT).show();
                amountEdt.setText("");
                showAmountBtn.setText(amount);
            }
        });

        debitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int i = Integer.parseInt(amountEdt.getText().toString());
                int amt=Integer.parseInt(showAmountBtn.getText().toString());
                int set;
                if(i<amt) {
                    set = amt - i;
                    String amount = String.valueOf(set);
                    manager.updateRecord(passacc,amount);
                    Toast.makeText(Transcation.this, "Amount Debited", Toast.LENGTH_SHORT).show();
                    showAmountBtn.setText(amount);
                }
                else {
                    Toast.makeText(Transcation.this, "your account balance is low", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
