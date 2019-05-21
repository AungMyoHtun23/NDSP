package com.example.ndsp.Holder;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Pojo.TenCategoryResponse;
import com.example.ndsp.R;

import java.util.ArrayList;
import java.util.Random;

public class CategoryRecyclerHolder extends RecyclerView.ViewHolder {
    private ArrayList<TenCategoryResponse> tenCategoryResponses= new ArrayList<>();

    private TextView textView;
    private LinearLayout layout;
    private int i;

    public interface OnItemClicked1{

        void onItemClick1(String position , String bookType);
    }
    OnItemClicked1 onItemClicked1;

    public CategoryRecyclerHolder(@NonNull View itemView, OnItemClicked1 onItemClicked1) {
        super(itemView);
        this.onItemClicked1=onItemClicked1;
        initView(itemView);

    }

    public void initView(View itemview){
        textView=itemview.findViewById(R.id.txt_category_list);
        layout=itemview.findViewById(R.id.categories_recycler_layout);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void bindData(final TenCategoryResponse tenCategoryResponse){
        textView.setText(tenCategoryResponse.categoryName);
        Random rnd = new Random();
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        int color = Color.argb(80, rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200));

        if (i % 1== 0) {
            gradientDrawable.setColor(color);
        } else {
            gradientDrawable.setColor(Color.BLUE);
        }
         textView.setBackground(gradientDrawable);


        Log.e("categoryname",tenCategoryResponse.categoryName);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onItemClicked1.onItemClick1(String.valueOf(tenCategoryResponse.id) , tenCategoryResponse.categoryName);
            }
        });

    }

    public static CategoryRecyclerHolder create(LayoutInflater inflater, ViewGroup viewGroup,OnItemClicked1 onItemClicked){
        View view=inflater.inflate(R.layout.categories_recycler_layout,viewGroup,false);
        return new CategoryRecyclerHolder(view,onItemClicked);

    }
}
