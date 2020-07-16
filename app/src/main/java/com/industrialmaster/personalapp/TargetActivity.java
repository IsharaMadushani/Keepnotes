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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class TargetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TargetActivity.this , targetAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ListView lv=findViewById(R.id.target_list);
        List<String> list = new ArrayList<>();

        DBHelper helper =new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT* FROM targets";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false){

            String target=cursor.getString(1);
            String date=cursor.getString(2);
            list.add(target+" /" +date);
            cursor.moveToNext();


        }
        int layout = android.R.layout.simple_list_item_1;
        ArrayAdapter adapter =new ArrayAdapter(this,layout,list);

        lv.setAdapter(adapter);
    }
}
