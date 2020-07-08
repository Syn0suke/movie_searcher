package com.example.stefandy_2201789536;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import adapter.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager2 = findViewById(R.id.view_pager);
        viewPager2.setAdapter(new SectionsPagerAdapter(this));

        TabLayout tabLayout = findViewById(R.id.tabs);
        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0: {
                        tab.setText("Search Movies");
                        break;
                    }
                    case 1: {
                        tab.setText("Saved Movies");
                        break;
                    }
                }
            }
        }
        );
        tabLayoutMediator.attach();

    }

    @Override
    public void onBackPressed() {

    }
}