package com.example.task.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task.ItemClickListener;
import com.example.task.R;
import com.example.task.model.ImageResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private ArrayList<ImageResponse> list;
    private ItemClickListener mListener;

    public ImageAdapter(ItemClickListener itemClickListener, ArrayList<ImageResponse> list) {
        this.mListener = itemClickListener;
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_layout, parent, false);
        return new ImageViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.setData(list.get(position).getUserImageURL(), position, mListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener mListener;
        AppCompatImageView imageView;
        private String url = "";

        public ImageViewHolder(@NonNull View itemView, ItemClickListener listener) {
            super(itemView);
            mListener = listener;
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        public void setData(String imageUrl, int position, ItemClickListener listener) {
            Picasso.get().load(imageUrl).into(imageView);

            itemView.setOnClickListener(view -> {
                url = imageUrl;
                mListener.onClick(imageUrl, itemView, position);
            });
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(url, view, getAdapterPosition());
        }
    }
}
