package com.example.ndsp.ApiInterface;

import com.example.ndsp.Pojo.AuthorDetailResponse;
import com.example.ndsp.Pojo.AuthorListResponse;
import com.example.ndsp.Pojo.BookListByCategoryResponse;
import com.example.ndsp.Pojo.BookSearchByTitleResponse;
import com.example.ndsp.Pojo.CategoryAll;
import com.example.ndsp.Pojo.EbookAllResponse;
import com.example.ndsp.Pojo.EbookDetailResponse;
import com.example.ndsp.Pojo.GenreDetailResponse;
import com.example.ndsp.Pojo.GenreListResponse;
import com.example.ndsp.Pojo.PublisherDetailResponse;
import com.example.ndsp.Pojo.PublisherListResponse;
import com.example.ndsp.Pojo.RecentBookAllList;
import com.example.ndsp.Pojo.RecentBookDetailResponce;
import com.example.ndsp.Pojo.RecentProduct;
import com.example.ndsp.Pojo.SearchAuthorResponse;
import com.example.ndsp.Pojo.SearchBookResponse;
import com.example.ndsp.Pojo.SearchCategoryResponse;
import com.example.ndsp.Pojo.SearchEbookResponse;
import com.example.ndsp.Pojo.SearchGenreResponse;
import com.example.ndsp.Pojo.SearchPublisherResponse;
import com.example.ndsp.Pojo.TenCategoryResponse;
import com.example.ndsp.Pojo.TopTenResponse;
import com.example.ndsp.Pojo.TopTenSeeAllBook;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("/api/book/topten")
    Call<ArrayList<TopTenResponse>>getTopTenBook();

    @GET("/api/book/topall")
    Call<TopTenSeeAllBook>getTopTenSeeAll();

    @GET("/api/book/recent")
    Call<ArrayList<RecentProduct>> getRecentbook();

    @GET("/api/book/all")
    Call<RecentBookAllList>getRecentBookAllList();

    @GET("/api/book/detail/{book_id}")
    Call<RecentBookDetailResponce>getrecentbookdetail(@Path("book_id") String bookId);

    @GET("/api/category/show")
    Call<ArrayList<TenCategoryResponse>>getTenCategory();

    @GET("/api/category/all")
    Call<CategoryAll>getCategoryAll();

    @GET("/api/book/by_category/{category_id}")
    Call<BookListByCategoryResponse>getbooklistcategory(@Path("category_id")String categoryId);

    @GET("/api/category/search/{name}")
    Call<ArrayList<SearchCategoryResponse>>getCategorySearch(@Path("name")String categorySearchId);

    @GET("api/author/all")
    Call<AuthorListResponse> getAuthorList();

    @GET("/api/author/search/{name}")
    Call<ArrayList<SearchAuthorResponse>>getAuthorSearch(@Path("name")String authorSearchId);

    @GET("api/publisher/all")
    Call<PublisherListResponse> getPublisherList();

    @GET("/api/publisher/search/{name}")
    Call<ArrayList<SearchPublisherResponse>>getPublisherSearch(@Path("name")String publisherSearchId);

    @GET("api/genre/all")
    Call<GenreListResponse> getGenreList();

    @GET("api/genre/search/{name}")
    Call<ArrayList<SearchGenreResponse>>getGenreSearch(@Path("name")String genreSearchId);

    @GET("api/book/by_publisher/{publisher_id}")
    Call<PublisherDetailResponse> getPublisherDetail(@Path("publisher_id") int publisher_id);

    @GET("api/book/by_author/{author_id}")
    Call<AuthorDetailResponse> getAuthorDetail(@Path("author_id") int author_id);

    @GET("api/book/by_genre/{genre_id}")
    Call<GenreDetailResponse> getGenreDetail(@Path("genre_id") int genre_id);

    @GET("/api/ebook/all")
    Call<EbookAllResponse>getEbookAll();

    @GET("/api/ebook/detail/{book_id}")
    Call<EbookDetailResponse>getEbookDetail(@Path("book_id") int ebook_id);

    @GET("/api/ebook/search/{name}")
    Call<ArrayList<SearchEbookResponse>>getEbookSearch(@Path("name")String ebook_name);

    @GET("/api/book/search/{name}")
    Call<ArrayList<SearchBookResponse>>getSearchBook(@Path("name")String bookSearchId);

    @GET("/api/search/book/{name}")
    Call<BookSearchByTitleResponse>getBookSearchByTitle(@Path("name") String book_name);





}
