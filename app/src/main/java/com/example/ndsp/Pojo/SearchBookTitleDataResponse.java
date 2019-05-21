package com.example.ndsp.Pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchBookTitleDataResponse {

    @SerializedName("id")
    public int id;

    @SerializedName("edition_id")
    public int edition_id;

    @SerializedName("publisher_id")
    public int publisher_id;

    @SerializedName("bookTitle")
    public int bookTitle;

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

    @SerializedName("ebook_size")
    public int ebook_size;

    @SerializedName("category")
    public String category;

    @SerializedName("rating")
    public int rating;

    @SerializedName("edition")
    public List<SearchBookTitleDataResponse>getBookTitleDataBySearching;

    @SerializedName("publisher")
    public List<SearchBookTitlePublisherResponse>getBooktitlePublisher;

    @SerializedName("genres")
    public List<SearchBookTitleGenreResponse>getBookTitleGenre;

    @SerializedName("authors")
    public List<SearchBookTitleAuthorResponse>getBookTitleAuthor;

    @SerializedName("categories")
    public List<SearchBookTitleCategoriesResponse>getBookTitleCategories;

    @SerializedName("first_page_url")
    public String first_page_url;

    @SerializedName("from")
    public int from;

    @SerializedName("last_page")
    public int last_page;

    @SerializedName("last_page_url")
    public String last_page_url;

    @SerializedName("next_page_url")
    public int next_page_url;

    @SerializedName("path")
    public String path;

    @SerializedName("per_page")
    public  int per_page;

    @SerializedName("prev_page_url")
    public int prev_page_url;

    @SerializedName("to")
    public int to;

    @SerializedName("total")
    public int total;

}