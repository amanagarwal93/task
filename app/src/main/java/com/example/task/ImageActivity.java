package com.example.task;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImageFragment imageFragment = new ImageFragment();

        String url = getIntent().getStringExtra("imageUrl");
        Bundle bundle = new Bundle();
        bundle.putString("imageUrl", url);
        imageFragment.setArguments(bundle);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, imageFragment, "ImageFragment")
                    .commit();
        }

    }
}