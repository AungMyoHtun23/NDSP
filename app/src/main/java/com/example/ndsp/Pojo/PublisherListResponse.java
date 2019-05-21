package com.example.ndsp.Pojo;

import com.example.ndsp.model.Publisher;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PublisherListResponse {

    @SerializedName("first_page_url")
    public String FirstPageUrl;

    @SerializedName("last_page_url")
    public String lastPageUrl;

    @SerializedName("next_page_url")
    public String nextPageUrl;

    @SerializedName("path")
    public String path;

    @SerializedName("prev_page_url ")
    public String prevPageUrl;

    @SerializedName("data")
    public ArrayList<Publisher> publishers;
}
