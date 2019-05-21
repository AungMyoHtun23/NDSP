package com.example.ndsp.model;

import com.google.gson.annotations.SerializedName;

public class GenreDetail {
    
    @SerializedName("id")
    public int id;

    @SerializedName("bookTitle")
    public String booktitle;

    @SerializedName("bookCoverImgUrl")
    public String bookcover;

    @SerializedName("bookSalePrice")
    public int booksale;

    @SerializedName("author")
    public String author;

    @SerializedName("ebook_size")
    public String ebooksize;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public int rating;
}
