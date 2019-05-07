package com.example.ndsp.Holder;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Pojo.TenCategoryResponse;
import com.example.ndsp.R;

import java.util.Random;

public class CategoryRecyclerHolder extends RecyclerView.ViewHolder {
    private TextView textView;
    LinearLayout layout;


    public interface OnItemClicked{ void onItemClick(int position);}
    OnItemClicked onItemClicked;

    public CategoryRecyclerHolder(@NonNull View itemView, OnItemClicked onItemClicked) {

        super(itemView);

        initView(itemView);

    }

    public void initView(View itemview){
        textView=itemview.findViewById(R.id.txt_category_list);
        layout=itemview.findViewById(R.id.categories_recycler_layout);

    }

    public void bindData(TenCategoryResponse tenCategoryResponse){
        textView.setText(tenCategoryResponse.categoryName);

        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        textView.setTextColor(Color.WHITE);
        textView.setBackgroundColor(color);

        Log.e("categoryname",tenCategoryResponse.categoryName);
    }

    public static CategoryRecyclerHolder create(LayoutInflater inflater, ViewGroup viewGroup,OnItemClicked onItemClicked){
        View view=inflater.inflate(R.layout.categories_recycler_layout,viewGroup,false);
        return new CategoryRecyclerHolder(view,onItemClicked);

    }
}
