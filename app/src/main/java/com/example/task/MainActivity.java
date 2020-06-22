package com.example.task;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.task.adapter.ImageAdapter;
import com.example.task.databinding.ActivityMainBinding;
import com.example.task.model.ImageResponse;
import com.example.task.viewmodel.ImageViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ImageAdapter imageAdapter;
    private ImageViewModel imageViewModel;
    private GridLayoutManager gridLayoutManager;
    private ActivityMainBinding binding;
    private ItemClickListener listener;
    private ArrayList<ImageResponse> imageResponseArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        imageResponseArrayList = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();
        imageViewModel =
                new ViewModelProvider(this).get(ImageViewModel.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initLayout(3);
    }

    private void initLayout(int count) {
        gridLayoutManager = new GridLayoutManager(this, count);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setHasFixedSize(true);

        imageViewModel.getMutableImageLiveData().observe(this, imageData -> {
            Log.d(TAG, "initLayout: " + imageData);
            listener = (url, view, position) -> {
                startActivity(new Intent(this, ImageActivity.class)
                        .putExtra("imageUrl", url));
                Animatoo.animateSplit(this);
            };
            imageResponseArrayList.addAll(imageData.getHits());
            imageAdapter = new ImageAdapter(listener, imageResponseArrayList);
            binding.recyclerView.setAdapter(imageAdapter);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                initLayout(3);
                return true;
            case R.id.item2:
                initLayout(4);
                return true;
            case R.id.item3:
                initLayout(5);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}