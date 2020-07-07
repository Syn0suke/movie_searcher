package com.example.stefandy_2201789536;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DeleteDetail extends AppCompatActivity {

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

        delete_detail_title = findViewById(R.id.tv_delete_detail_title);
        delete_detail_year = findViewById(R.id.tv_delete_detail_year);
        delete_detail_id  = findViewById(R.id.tv_delete_detail_id);
        delete_detail_thumbnail = (ImageView)findViewById(R.id.iv_delete_detail);

        Intent intent = getIntent();
        title = intent.getStringExtra("Title");
        year = intent.getStringExtra("Year");
        id = intent.getStringExtra("Id");
        path = intent.getStringExtra("Image");

//        Bitmap myBitmap = BitmapFactory.decodeFile(path);
//        if(myBitmap != null)
//        {
//            //reduce to 70% size; bitmaps produce larger than actual image size
//            Bitmap rescaledMyBitmap = Bitmap.createScaledBitmap(
//                    myBitmap,
//                    myBitmap.getWidth()/10*7,
//                    myBitmap.getHeight()/10*7,
//                    false);
//
//            delete_detail_thumbnail.setImageBitmap(rescaledMyBitmap);
//        }
        delete_detail_thumbnail.setImageBitmap(BitmapFactory.decodeFile(path));
        delete_detail_title.setText(title);
        delete_detail_year.setText(year);
        delete_detail_id.setText(id);
    }
}