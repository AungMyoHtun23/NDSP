package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchBookTitleResponse {

    @SerializedName("current_page")
    public String current_page;

    @SerializedName("data")
    public List<SearchBookTitleDataResponse>getBookTitlebySearch;
}
