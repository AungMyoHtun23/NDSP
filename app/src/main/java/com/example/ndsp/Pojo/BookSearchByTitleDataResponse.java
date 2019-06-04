package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import retrofit2.Call;

public class BookSearchByTitleDataResponse {

    @SerializedName("id")
    public int id;

    @SerializedName("edition_id")
    public int edition_id;

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
    public int e_book_download;

    @SerializedName("created_at")
    public String created_at;

    @SerializedName("updated_at")
    public String updated_at;

    @SerializedName("buy_or_consigment")
    public int buy_or_consigment;

    @SerializedName("author")
    public String author;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public int rating;

    @SerializedName("edition")
    public Call<BookSearchByTitleDataEdition> bookSearchByTitleDataEditionCall;

    @SerializedName("publisher")
    public Call<BookSearchByTitleDataPublisher> bookSearchByTitleDataPublisherCall;

    @SerializedName("genres")
    public Call<BookSearchByTitleDataGenre> bookSearchByTitleDataGenreCall;

    @SerializedName("authors")
    public ArrayList<BookSearchByTitleDataAuthor>bookSearchByTitleDataAuthors;

    @SerializedName("categories")
    public ArrayList<BookSearchByTitleDataCategories>bookSearchByTitleDataCategories;
}
