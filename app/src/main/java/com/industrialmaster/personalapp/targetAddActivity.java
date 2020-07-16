package com.industrialmaster.personalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class targetAddActivity extends AppCompatActivity {
    EditText etTarget,etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_add);
        etTarget = findViewById(R.id.target_name);
        etDate = findViewById(R.id.target_date);
    }

    public void save(View v){
        String target=etTarget.getText().toString();
        String date=etDate.getText().toString();
        DBHelper helper =  new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        String sql = "INSERT INTO targets(target,date,complete)VALUES('"+target+"','"+date+"','false')";
        db.execSQL(sql);

        Toast.makeText(this, "saved",Toast.LENGTH_SHORT).show();
    }
}
