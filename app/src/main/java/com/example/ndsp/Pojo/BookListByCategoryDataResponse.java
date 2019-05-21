package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

import retrofit2.http.PUT;

public class BookListByCategoryDataResponse {

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
    public int ebook_size;

    @SerializedName("category")
    public  String category;

    @SerializedName("rating")
    public int rating;
}
