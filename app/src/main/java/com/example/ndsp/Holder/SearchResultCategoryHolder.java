package com.example.ndsp.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Pojo.SearchCategoryResponse;
import com.example.ndsp.R;

import java.util.ArrayList;

public class SearchResultCategoryHolder extends RecyclerView.ViewHolder {

    ArrayList<SearchCategoryResponse>searchCategoryResponses=new ArrayList<>();
    private TextView tvName;
    private LinearLayout layout;
    private OnClickSearchCategory onClickSearchCategory;

    public interface OnClickSearchCategory{
        void clickSearchCategory(int id);
    }

    public SearchResultCategoryHolder(View view, OnClickSearchCategory onClickSearchCategory) {
        super(view);
        this.onClickSearchCategory=onClickSearchCategory;
        initResource(view);
    }

    public static SearchResultCategoryHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnClickSearchCategory onClickSearchCategory){
        View view=inflater.inflate(R.layout.category_list_item,viewGroup,false);
        return new SearchResultCategoryHolder(view,onClickSearchCategory);
    }

    public void initResource(View itemView){
        tvName=itemView.findViewById(R.id.tv_category_list);
        layout=itemView.findViewById(R.id.linear_categories);

    }

    public void bindData(final SearchCategoryResponse searchCategoryResponse){
        tvName.setText(searchCategoryResponse.categoryName);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSearchCategory.clickSearchCategory(searchCategoryResponse.id);
            }
        });
    }
}
