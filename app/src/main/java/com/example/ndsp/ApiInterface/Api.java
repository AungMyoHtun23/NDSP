package com.example.ndsp.ApiInterface;

import com.example.ndsp.Pojo.CategoryAll;
import com.example.ndsp.Pojo.RecentBookDetailResponce;
import com.example.ndsp.Pojo.RecentProduct;
import com.example.ndsp.Pojo.TenCategoryResponse;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Api {


    @GET("/api/book/recent")
    Call<ArrayList<RecentProduct>> getRecentbook();

    @GET("/api/book/detail/{book_id}")
    Call<RecentBookDetailResponce>recentbookdetail(@Path("book_id") String bookId);

    @GET("/api/category/show")
    Call<ArrayList<TenCategoryResponse>>getTenCategory();

    @GET("/api/category/all")
    Call<CategoryAll>getCategoryAll();


}
