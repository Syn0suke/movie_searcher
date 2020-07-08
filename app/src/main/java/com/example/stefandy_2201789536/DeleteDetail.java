package com.example.stefandy_2201789536;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.File;

import helper.DatabaseHelper;

public class DeleteDetail extends AppCompatActivity {

    DatabaseHelper db;
    private String title,year,id,path;
    private TextView delete_detail_title,delete_detail_year,delete_detail_id;
    private Button btn;
    private ImageView delete_detail_thumbnail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_detail);

        btn = findViewById(R.id.delete_button);
        Toolbar toolbar = findViewById(R.id.toolbar_delete);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        db = new DatabaseHelper(this);
        delete_detail_title = findViewById(R.id.tv_delete_detail_title);
        delete_detail_year = findViewById(R.id.tv_delete_detail_year);
        delete_detail_id  = findViewById(R.id.tv_delete_detail_id);
        delete_detail_thumbnail = (ImageView)findViewById(R.id.iv_delete_detail);

        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        year = intent.getStringExtra("Year");
        id = intent.getStringExtra("Id");
        path = intent.getStringExtra("Image");

        delete_detail_thumbnail.setImageBitmap(BitmapFactory.decodeFile(path));
        delete_detail_title.setText("Title  : " + title);
        delete_detail_year.setText("Year   : " + year);
        delete_detail_id.setText("IMDB ID : " + id);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.Delete(title);
                File file = new File(path);
                file.delete();
                Toast.makeText(DeleteDetail.this,"Delete Successful",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DeleteDetail.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}