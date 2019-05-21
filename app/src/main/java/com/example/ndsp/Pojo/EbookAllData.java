package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

public class EbookAllData {
    @SerializedName("id")
    public int id;

    @SerializedName("bookTitle")
    public String bookTitle;

    @SerializedName("bookCoverImgUrl")
    public String bookCoverImgUrl;

    @SerializedName("e_book_download")
    public String e_book_download;

    @SerializedName("author")
    public String author;

    @SerializedName("ebook_size")
    public String ebook_size;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public int rating;

}
