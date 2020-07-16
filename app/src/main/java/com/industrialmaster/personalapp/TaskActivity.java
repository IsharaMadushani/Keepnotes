package com.industrialmaster.personalapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class TaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaskActivity.this , taskAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView lv=findViewById(R.id.task_list);
        List<String> list = new ArrayList<>();

        DBHelper helper =new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT* FROM tasks WHERE complete='false'";
        Cursor cursor = db.rawQuery(sql, null);
        //cursor.moveToFirst();
        //while(cursor.isAfterLast()==false){

          //  String name=cursor.getString(1);
          //  String date=cursor.getString(2);
         //   list.add(name+" /" +date);
         //   cursor.moveToNext();

        {
        }
      //  int layout = android.R.layout.simple_list_item_1;
       // ArrayAdapter adapter =new ArrayAdapter(this,layout,list);
        int layout =  R.layout.single_task;
        int[] views = {R.id.lbl_task_name,R.id.lbl_task_date,R.id.lbl_task_id}; //views 3 variable 3 kata nam karagannawa
        String[] cols = {"name","date","_id"};   //me colomns walin data aran uda tyna views walata set karanna oana


        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,layout,cursor,cols,views);

        lv.setAdapter(adapter);
    }
    public void complete(View v){

        LinearLayout layout =(LinearLayout) v.getParent();
        TextView tv = layout.findViewById(R.id.lbl_task_id);

        String id = tv.getText().toString();



        DBHelper myDB = new DBHelper(this);
        SQLiteDatabase db = myDB.getWritableDatabase();

        String sql ="UPDATE tasks SET complete='true' WHERE _id=' "+id+"'";
        db.execSQL(sql);

        Toast.makeText(this,"Task complete !",Toast.LENGTH_SHORT).show();
        onResume();
    }
}
