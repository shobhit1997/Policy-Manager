package com.example.shobhit.policymanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent i=getIntent();
        int policyId=i.getIntExtra("policyId",-1);


    }
    public void save(View view)
    {
        EditText name=(EditText)findViewById(R.id.name);
        EditText policyNo=(EditText)findViewById(R.id.policyNo);
        EditText mode=(EditText)findViewById(R.id.mode);
        EditText premAmt=(EditText)findViewById(R.id.premAmt);
        EditText premDate=(EditText)findViewById(R.id.premDate);
        EditText matDate=(EditText)findViewById(R.id.matDate);
        EditText matAmt=(EditText)findViewById(R.id.matSum);
        EditText nominee=(EditText)findViewById(R.id.nominee);
        try
        {
            SQLiteDatabase myDatabase=this.openOrCreateDatabase("Policies",MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS policies (name VARCHAR, policyNo INT(10), preAmt INT(10), mode VARCHAR, preDate VARCHAR, matDate VARCHAR, matAmt INT(10), nominee VARCHAR)");
            myDatabase.execSQL("INSERT INTO policies (name, policyNo, preAmt, mode, preDate, matDate, matAmt, nominee) VALUES ('Shobhit Agarwal',2548976155,3200,'Yearly','8/8','8/8/2024',2000000,'Seema Agarwal')");
            myDatabase.execSQL("INSERT INTO policies (name, policyNo, preAmt, mode, preDate, matDate, matAmt, nominee) VALUES ('Rachit Agarwal',2548976155,3200,'Yearly','8/8','8/8/2024',2000000,'Seema Agarwal')");
            MainActivity.updateList();

            Log.i("Task","Successful");

        }catch (Exception e)
        {
            e.printStackTrace();
            Log.i("Task","failed");

        }

    }
}
