package com.example.task;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.task.databinding.FragmentImageBinding;
import com.squareup.picasso.Picasso;

public class ImageFragment extends Fragment {

    private String imageUrl = "";

    public ImageFragment() {
    }

    private FragmentImageBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            imageUrl = getArguments().getString("imageUrl");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_image, container, false);
        Picasso.get().load(imageUrl).into(binding.fullimage);
        return binding.getRoot();
    }
}