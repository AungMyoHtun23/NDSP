package com.example.ndsp.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ndsp.Fragment.FragmentExlplore;
import com.example.ndsp.Holder.CategoryRecyclerHolder;
import com.example.ndsp.Pojo.BookListByCategoryDataResponse;
import com.example.ndsp.Pojo.TenCategoryResponse;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerHolder> {
    private ArrayList<TenCategoryResponse>tenCategoryResponses= new ArrayList<>();
    private ArrayList<BookListByCategoryDataResponse>bookListByCategoryDataResponses=new ArrayList<>();
    private CategoryRecyclerHolder.OnItemClicked1 onItemClicked;
    private Context context;

    public CategoryRecyclerAdapter(Context context, FragmentExlplore onItemClicked) {
        this.context=context;
        this.onItemClicked=onItemClicked;
    }


    @NonNull
    @Override
    public CategoryRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return CategoryRecyclerHolder.create(inflater, viewGroup,onItemClicked);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull CategoryRecyclerHolder categoryRecyclerHolder, int i) {
        categoryRecyclerHolder.bindData(tenCategoryResponses.get(i));

    }

    @Override
    public int getItemCount() {
        return tenCategoryResponses.size();

    }

    public void setTenCategoryResponses(ArrayList<TenCategoryResponse>tenCategoryResponses){
        this.tenCategoryResponses=tenCategoryResponses;
        notifyDataSetChanged();
    }
}
