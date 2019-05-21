package com.example.ndsp.Holder;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.ndsp.Pojo.TenCategoryResponse;
import com.example.ndsp.R;

import java.util.ArrayList;
import java.util.Random;

public class EbookRecyclerHolder extends RecyclerView.ViewHolder {
    private ArrayList<TenCategoryResponse> tenCategoryResponses= new ArrayList<>();

    private TextView textView;
    private LinearLayout layout;
    private int i;
    Context context;


    public interface OnItemClicked2{

        void onItemClick2(String position);
    }

    EbookRecyclerHolder.OnItemClicked2 onItemClicked2;

    public EbookRecyclerHolder(@NonNull View itemView, EbookRecyclerHolder.OnItemClicked2 onItemClicked1) {
        super(itemView);
        this.onItemClicked2=onItemClicked1;
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
                Toast.makeText(view.getContext(),"E_Book Will Release Soon",Toast.LENGTH_SHORT).show();
//                onItemClicked2.onItemClick2(tenCategoryResponse.id);

            }
        });

    }

    public static EbookRecyclerHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClicked2 onItemClicked){
        View view=inflater.inflate(R.layout.categories_recycler_layout,viewGroup,false);
        return new EbookRecyclerHolder(view,onItemClicked);

    }
}
