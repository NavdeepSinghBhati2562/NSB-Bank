package com.example.nsbbank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class DatabaseManager extends SQLiteOpenHelper {
    SQLiteDatabase database;
    String DBname="";
    static String DBpath="BANK.sqlite";

    public DatabaseManager(@Nullable Context context) {
        super(context, DBpath, null,1 );
        DBname="/data/data/"+context.getPackageName()+"/databases";

    }

    public static synchronized DatabaseManager getDB(Context context)
    {

        return new DatabaseManager(context);
    }

    public boolean checkDB(){
        try {
            database=SQLiteDatabase.openDatabase(DBname+"/"+DBpath,null,SQLiteDatabase.OPEN_READWRITE);
        }catch (Exception e)
        {
            database=null;
        }


       return database!=null;
    }

    public void createDB(Context context){
      this.getReadableDatabase();
      this.close();
        try {
            InputStream inputStream = context.getAssets().open(DBpath);
            String filePath=DBname+"/"+DBpath;

            FileOutputStream outputStream=new FileOutputStream(filePath);

            byte[] bytes = new byte[1024];
            int len;

            while ((len=inputStream.read(bytes))>0)
            {

                outputStream.write(bytes,0,len);

            }

            inputStream.close();
            outputStream.flush();
            outputStream.close();


        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public  boolean Authenticating(String acc, String mypassword)
    {
        Cursor cursor=database.rawQuery("select * from 'holder_details' where Acc_no = ? AND pass = ?", new String[]{acc, mypassword});


        return cursor.getCount() > 0;


    }

    public void settingDB(SuitCase suitCase){
        String s;
        ContentValues values=new ContentValues();

        values.put("name",suitCase.name);
        values.put("phone",suitCase.phone);
        values.put("aadhar",suitCase.aadharNo);
        values.put("email",suitCase.email);
        values.put("nominee",suitCase.nomineeName);
        values.put("pass",suitCase.password);
        values.put("amount",suitCase.amount);

        s=String.valueOf(new Random().nextInt((99999999-10000000)+1)+10000000);
        suitCase.AccNo="NSB0000"+s;
        values.put("Acc_no",suitCase.AccNo);

        database.insert("holder_details",null,values);

    }


    public  ArrayList<SuitCase> gettingData()
   {
    Cursor cursor=database.rawQuery("select * from 'holder_details'",null);
        ArrayList<SuitCase> arrData=new ArrayList<>();


        while(cursor.moveToNext()){

            SuitCase data=new SuitCase();
            data.name=cursor.getString(0);
            data.phone=cursor.getInt(1);
            data.aadharNo=cursor.getInt(2);
            data.email=cursor.getString(3);
            data.nomineeName=cursor.getString(4);
            data.password=cursor.getString(5);
            data.AccNo=cursor.getString(6);
            data.amount=cursor.getInt(7);


            arrData.add(data);

        }

        return arrData;
    }

    public  SuitCase getAcc(String acc){
        Cursor cursor = database.rawQuery("select * from 'holder_details' where Acc_no =? ",new String[] {acc});
        SuitCase suit = new SuitCase();

        cursor.moveToFirst();

        suit.name=cursor.getString(0);
        suit.phone=cursor.getInt(1);
        suit.aadharNo=cursor.getInt(2);
        suit.email=cursor.getString(3);
        suit.nomineeName=cursor.getString(4);
        suit.password=cursor.getString(5);
        suit.nomineeName=cursor.getString(6);
        suit.amount=cursor.getInt(7);

        return suit;
    }


    public void openDB(){

        database=SQLiteDatabase.openDatabase(DBname+"/"+DBpath,null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void updateRecord(String acc, String amount){

        ContentValues cv  = new ContentValues();
        cv.put("amount", amount);

        database.update("holder_details", cv, "Acc_no = ?", new String[]{acc});
    }





    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
