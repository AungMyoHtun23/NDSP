package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

import retrofit2.Call;

public class EbookDetail {

    @SerializedName("id")
    public int id;

    @SerializedName("edition_id")
    public int edition_id;

    @SerializedName("ebook_id")
    public int ebook_id;

    @SerializedName("publisher_id")
    public int publisher_id;

    @SerializedName("bookTitle")
    public String bookTitle;

    @SerializedName("bookDescription")
    public String bookDescription;

    @SerializedName("bookSalePrice")
    public int bookSalePrice;

    @SerializedName("bookCoverImgUrl")
    public String bookCoverImgUrl;

    @SerializedName("isEbook")
    public int isEbook;

    @SerializedName("e_book_download")
    public String e_book_download;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("updated_at")
    public String updated_at;

    @SerializedName("buy_or_consigment")
    public int buy_or_consigment;

    @SerializedName("author")
    public String author;

    @SerializedName("ebook_size")
    public int ebook_size;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public int rating;

    @SerializedName("edition")
    Call<EbookEdition>ebookEdition;

    @SerializedName("publisher")
    Call<EbookPublisher>ebookPublisher;

    @SerializedName("genres")
    Call<EbookGenres>ebookGenres;

    @SerializedName("authors")
    Call<EbookAuthors>ebookAuthors;

    @SerializedName("categories")
    Call<EbookCategories>ebookCategories;


}
