package com.example.ndsp.Pojo;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;


public class EbookDetailResponse {

    @SerializedName("book")
    public ArrayList<EbookDetail> book;

    @SerializedName("comments")
    Call<EbookComment> comments;

}
