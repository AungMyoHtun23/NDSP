package com.example.ndsp.Holder;

import android.content.Context;
import android.content.SharedPreferences;
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


import com.example.ndsp.Language.Rabbit;
import com.example.ndsp.Pojo.TenCategoryResponse;
import com.example.ndsp.R;

import java.util.ArrayList;
import java.util.Random;

public class EbookRecyclerHolder extends RecyclerView.ViewHolder {
    private ArrayList<TenCategoryResponse> tenCategoryResponses= new ArrayList<>();
    SharedPreferences sharedPreferences;
    public static final String LANGUAGE_PREFERENCE = "lan_pref", PREFERENCE_KEY = "lan_key";
    Context context;
    private TextView textView;
    private LinearLayout layout;
    private int i;



    public interface OnItemClicked2{

        void onItemClick2(String position);
    }

    EbookRecyclerHolder.OnItemClicked2 onItemClicked2;

    public EbookRecyclerHolder(@NonNull View itemView, EbookRecyclerHolder.OnItemClicked2 onItemClicked1,Context context) {
        super(itemView);
        this.context=context;
        this.onItemClicked2=onItemClicked1;
        initView(itemView);

    }

    public void initView(View itemview){
        textView=itemview.findViewById(R.id.txt_category_list);
        layout=itemview.findViewById(R.id.categories_recycler_layout);
        sharedPreferences=context.getSharedPreferences(LANGUAGE_PREFERENCE,Context.MODE_PRIVATE);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void bindData(final TenCategoryResponse tenCategoryResponse){
        if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("z")){
            textView.setText(Rabbit.uni2zg(tenCategoryResponse.categoryName));
        }else if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("u")){
            textView.setText(Rabbit.zg2uni(tenCategoryResponse.categoryName));
        }else {
            textView.setText(tenCategoryResponse.categoryName);

        }
        Random rnd = new Random();
        GradientDrawable gradientDrawable=new GradientDrawable();
        gradientDrawable.setCornerRadius(10);
        int color = Color.argb(90, rnd.nextInt(200), rnd.nextInt(200), rnd.nextInt(200));

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

    public static EbookRecyclerHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnItemClicked2 onItemClicked,Context context){
        View view=inflater.inflate(R.layout.categories_recycler_layout,viewGroup,false);
        return new EbookRecyclerHolder(view,onItemClicked,context);

    }
}
