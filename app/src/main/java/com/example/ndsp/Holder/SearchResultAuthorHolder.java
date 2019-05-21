package com.example.ndsp.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Adapter.SearchResultAuthorAdapter;
import com.example.ndsp.Pojo.SearchAuthorResponse;
import com.example.ndsp.R;

import java.util.ArrayList;

public class SearchResultAuthorHolder extends RecyclerView.ViewHolder {
    ArrayList<SearchAuthorResponse>searchAuthorResponses=new ArrayList<>();
    private TextView tvName;
    private LinearLayout layout;
    private OnClickSearchAuthor onClickSearchAuthor;


    public interface OnClickSearchAuthor{

        void clickSearchAuthor(int id);
    }

    public SearchResultAuthorHolder(@NonNull View itemView,OnClickSearchAuthor onClickSearchAuthor) {
        super(itemView);
        this.onClickSearchAuthor=onClickSearchAuthor;
        initResource(itemView);
    }

    public static SearchResultAuthorHolder create(LayoutInflater inflater, ViewGroup viewGroup,OnClickSearchAuthor onClickSearchAuthor){
        View view=inflater.inflate(R.layout.category_list_item,viewGroup,false);
        return new SearchResultAuthorHolder(view,onClickSearchAuthor);
    }

    public void initResource(View itemView){
        tvName = itemView.findViewById(R.id.tv_category_list);
        layout = itemView.findViewById(R.id.linear_categories);
    }

    public void bindData(final SearchAuthorResponse searchAuthorResponse){
        tvName.setText(searchAuthorResponse.authorName);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSearchAuthor.clickSearchAuthor(searchAuthorResponse.id);
            }
        });
    }
}
