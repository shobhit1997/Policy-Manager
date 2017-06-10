package com.example.shobhit.policymanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PolicyViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_policy_viewer);
        TextView name=(TextView)findViewById(R.id.name);
        TextView policyNo=(TextView)findViewById(R.id.policyNo);
        TextView mode=(TextView)findViewById(R.id.mode);
        TextView premAmt=(TextView)findViewById(R.id.premAmt);
        TextView premDate=(TextView)findViewById(R.id.premDate);
        TextView matDate=(TextView)findViewById(R.id.matDate);
        TextView matAmt=(TextView)findViewById(R.id.matSum);
        TextView nominee=(TextView)findViewById(R.id.nominee);

        Intent i=getIntent();
        int policyId=i.getIntExtra("policyId",-1);

        try
        {
            SQLiteDatabase myDatabase=this.openOrCreateDatabase("Policies",MODE_PRIVATE,null);
            Cursor c=myDatabase.rawQuery("SELECT * FROM policies",null);
            int nameIndex=c.getColumnIndex("name");
            int policyNoIndex=c.getColumnIndex("policyNo");
            int modeIndex=c.getColumnIndex("mode");
            int preAmtIndex=c.getColumnIndex("preAmt");
            int preDateIndex=c.getColumnIndex("preDate");
            int matAmtIndex=c.getColumnIndex("matAmt");
            int matDateIndex=c.getColumnIndex("matDate");
            int nomineeIndex=c.getColumnIndex("nominee");

            //c.moveToFirst();
            
            System.out.println("policy Id="+policyId);
            c.moveToPosition(policyId);
            System.out.println(c.getPosition());

                    name.setText(c.getString(nameIndex));
                    policyNo.setText(""+c.getLong(policyNoIndex));

                    mode.setText(c.getString(modeIndex));
                    premAmt.setText(c.getInt(preAmtIndex)+"");
                    premDate.setText(c.getString(preDateIndex));
                    matAmt.setText(c.getInt(matAmtIndex)+"");
                    matDate.setText(c.getString(matDateIndex));
                    nominee.setText(c.getString(nomineeIndex));



        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }
}
