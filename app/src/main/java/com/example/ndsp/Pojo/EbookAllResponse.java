package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;



public class EbookAllResponse {

    @SerializedName("current_page")
    public int current_page;

    @SerializedName("data")
    public List<EbookAllData> getEbookAllData;

    @SerializedName("first_page_url")
    public String first_page_url;

    @SerializedName("from")
    public int from;

    @SerializedName("last_page")
    public int last_page;

    @SerializedName("last_page_url")
    public String last_page_url;

    @SerializedName("next_page_url")
    public String next_page_url;

    @SerializedName("path")
    public String path;

    @SerializedName("per_page")
    public int per_page;

    @SerializedName("prev_page_url")
    public String prev_page_url;

    @SerializedName("to")
    public int to;

    @SerializedName("total")
    public int total;
}
