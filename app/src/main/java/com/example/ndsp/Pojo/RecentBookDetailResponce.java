package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RecentBookDetailResponce {

    @SerializedName("book")
    public RecentBookDetailBook bookDetailBooks;

    @SerializedName("comments")
    public List<RecentBookDetailComments>recentBookDetailComments;


}
