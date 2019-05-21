package com.example.ndsp.model;

import com.google.gson.annotations.SerializedName;

public class Authors {

    @SerializedName("id")
    public int id;

    @SerializedName("bookTitle")
    public String authorName;

    @SerializedName("bookCoverImgUrl")
    public String bookcoverimgurl;

    @SerializedName("bookSalePrice")
    public String booksaleprice;

    @SerializedName("author")
    public String author;

    @SerializedName("ebook_size")
    public String ebooksize;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public String rating;


}

