package com.example.task.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImageData {

    @SerializedName("total")
    @Expose
    private int total;

    @SerializedName("totalHits")
    @Expose
    private int totalHits;

    @SerializedName("hits")
    @Expose
    private ArrayList<ImageResponse> hits = null;

    public ImageData(int total, int totalHits, ArrayList<ImageResponse> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }

    public ImageData() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public ArrayList<ImageResponse> getHits() {
        return hits;
    }

    public void setHits(ArrayList<ImageResponse> hits) {
        this.hits = hits;
    }

    @Override
    public String toString() {
        return "ImageData{" +
                "total=" + total +
                ", totalHits=" + totalHits +
                ", hits=" + hits +
                '}';
    }
}
