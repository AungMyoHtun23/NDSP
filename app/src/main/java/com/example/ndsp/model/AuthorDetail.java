package com.example.ndsp.model;

import com.google.gson.annotations.SerializedName;

public class AuthorDetail {

    @SerializedName("id")
    public int id;

    @SerializedName("bookTitle")
    public String booktitle;

    @SerializedName("bookCoverImgUrl")
    public String bookcover;

    @SerializedName("bookSalePrice")
    public int booksaleprice;

    @SerializedName("author")
    public String author;

    @SerializedName("ebook_size")
    public String ebooksize;

    @SerializedName("ebook_id")
    public int ebook_id;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public int rating;

}
