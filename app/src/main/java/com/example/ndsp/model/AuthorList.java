package com.example.ndsp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AuthorList {

    @SerializedName("current_page")
    public int currentPage;

    @SerializedName("data")
    public ArrayList<Author> data;

    @SerializedName("frist_page_url")
    public String firstPage;

    @SerializedName("from")
    public int from;

    @SerializedName("last_page")
    public int lastPage;

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

    @SerializedName( "to")
    public int to;

    @SerializedName("total")
    public int total;


}
