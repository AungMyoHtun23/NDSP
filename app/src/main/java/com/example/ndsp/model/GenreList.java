package com.example.ndsp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class GenreList {

    @SerializedName("current_page")
    public int currentPage;

    @SerializedName("data")
    public ArrayList<Genre> data;

    @SerializedName("first_page_url")
    public String firstPage;

    @SerializedName("from")
    public int from;

    @SerializedName("last_page")
    public int lastpage;

    @SerializedName("last_page_url")
    public String LastPage;

    @SerializedName("next_page_url")
    public String nextPage;

    @SerializedName("path")
    public String path;

    @SerializedName("per_page")
    public int perPage;

    @SerializedName("prev_page_url")
    public String prevPage;

    @SerializedName("to")
    public int to;

    @SerializedName("total")
    public int total;
}
