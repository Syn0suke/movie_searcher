package com.example.stefandy_2201789536;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

public class DeleteDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_detail);

        Toolbar toolbar = findViewById(R.id.toolbar_delete);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Movie Detail");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}