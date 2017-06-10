package com.example.shobhit.policymanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   static ArrayList<String> policies=new ArrayList<>();
   static  ArrayAdapter arrayAdapter;
   static SQLiteDatabase myDatabase;
    public static void updateList()
    {
        try
        {

            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS policies (name VARCHAR, policyNo INT(10), preAmt INT(10), mode VARCHAR, preDate VARCHAR, matDate VARCHAR, matAmt INT(10), nominee VARCHAR)");
            Cursor c=myDatabase.rawQuery("SELECT * FROM policies",null);
            int nameIndex=c.getColumnIndex("name");


            c.moveToFirst();
            Log.i("Task","Successful");
            int count=0;
            while(c!=null)
            {
                policies.add(c.getString(nameIndex));
                c.moveToNext();
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            Log.i("Task","failed");
            System.out.println(e);

        }
        arrayAdapter.notifyDataSetChanged();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView policy=(ListView)findViewById(R.id.policyList);

        arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,policies);
        policy.setAdapter(arrayAdapter);

         myDatabase=getApplicationContext().openOrCreateDatabase("Policies",MODE_PRIVATE,null);
        updateList();

        policy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),PolicyViewer.class);
                i.putExtra("policyId",position);
                startActivity(i);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.add) {

            Intent i=new Intent(getApplicationContext(),Main2Activity.class);
            i.putExtra("policyId",0);
            startActivity(i);
            updateList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
