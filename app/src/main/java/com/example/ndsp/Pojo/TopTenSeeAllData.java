package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

public class TopTenSeeAllData {

    @SerializedName("id")
    public int id;

    @SerializedName("bookTitle")
    public String bookTitle;

    @SerializedName("bookCoverImgUrl")
    public String bookCoverImgUrl;

    @SerializedName("bookSalePrice")
    public int bookSalePrice;

    @SerializedName("author")
    public String author;

    @SerializedName("ebook_size")
    public String ebooksize;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public int rating;

}
