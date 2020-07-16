package com.industrialmaster.personalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class taskAddActivity extends AppCompatActivity {
    EditText etName,etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_add);
        etName = findViewById(R.id.task_name);
        etDate = findViewById(R.id.task_date);
    }

    public void save(View v){
        String name=etName.getText().toString();
        String date=etDate.getText().toString();
        DBHelper helper =  new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "INSERT INTO tasks(name,date,complete)VALUES('"+name+"','"+date+"','false')";
        db.execSQL(sql);

        Toast.makeText(this, "saved",Toast.LENGTH_SHORT).show();
    }
}
