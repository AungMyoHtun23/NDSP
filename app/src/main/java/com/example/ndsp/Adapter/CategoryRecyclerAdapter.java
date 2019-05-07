package com.example.ndsp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ndsp.Fragment.FragmentExlplore;
import com.example.ndsp.Holder.CategoryRecyclerHolder;
import com.example.ndsp.Pojo.TenCategoryResponse;

import java.util.ArrayList;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerHolder> {
    private ArrayList<TenCategoryResponse>tenCategoryResponses= new ArrayList<>();
    private CategoryRecyclerHolder.OnItemClicked onItemClicked;



    public CategoryRecyclerAdapter(ArrayList<TenCategoryResponse> tenCategoryResponses, CategoryRecyclerHolder.OnItemClicked onItemClicked) {
        this.tenCategoryResponses = tenCategoryResponses;
        this.onItemClicked = onItemClicked;
    }

    public CategoryRecyclerAdapter(Context context, CategoryRecyclerHolder.OnItemClicked onItemClicked){

    }

    public CategoryRecyclerAdapter(Context context, FragmentExlplore fragmentExlplore) {

    }

    @NonNull
    @Override
    public CategoryRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return CategoryRecyclerHolder.create(inflater, viewGroup,onItemClicked);
    }

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
