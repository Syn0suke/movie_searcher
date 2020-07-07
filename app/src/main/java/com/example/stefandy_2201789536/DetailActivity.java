package com.example.stefandy_2201789536;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {

    private String title,year,id,url;
    private TextView detail_title,detail_year,detail_id;
    private Button btn;
    private ImageView detail_thumbnail;
    RequestOptions option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btn = findViewById(R.id.save_button);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
        option = new RequestOptions().centerCrop().placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_foreground);
//
        detail_title = findViewById(R.id.tv_detail_title);
        detail_year = findViewById(R.id.tv_detail_year);
        detail_id  = findViewById(R.id.tv_detail_id);
        detail_thumbnail = findViewById(R.id.iv_detail);

        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        year = intent.getStringExtra("Year");
        id = intent.getStringExtra("Id");
        url = intent.getStringExtra("Image");

        detail_title.setText(title);
        detail_year.setText(year);
        detail_id.setText(id);
        Glide.with(this).load(url).apply(option).into(detail_thumbnail);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}