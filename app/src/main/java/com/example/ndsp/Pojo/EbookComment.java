package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

public class EbookComment {

    @SerializedName("id")
    public int id;

    @SerializedName("book_id")
    public int book_id;

    @SerializedName("comment")
    public String comment;

    @SerializedName("rating")
    public String rating;

    @SerializedName("approve")
    public int approve;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("updated_at")
    public String updated_at;

    @SerializedName("username")
    public String username;
}
