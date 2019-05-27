package com.example.ndsp.Holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Pojo.SearchPublisherResponse;
import com.example.ndsp.R;

import java.util.ArrayList;


public class SearchResultPublisherHolder extends RecyclerView.ViewHolder {
    private ArrayList<SearchPublisherResponse>searchPublisherResponses=new ArrayList<>();
    private TextView tvName;
    private LinearLayout layout;
    private OnClickSearchPublisher onclickSearchPublisher;

    public interface OnClickSearchPublisher{
        void clickSearchPublisher(int id);
    }


    public SearchResultPublisherHolder(@NonNull View itemView,OnClickSearchPublisher onClickSearchPublisher) {
        super(itemView);
        this.onclickSearchPublisher=onClickSearchPublisher;
        initResource(itemView);
    }



    public static SearchResultPublisherHolder create(LayoutInflater inflater, ViewGroup viewGroup,OnClickSearchPublisher onClickSearchPublisher){
        View view=inflater.inflate(R.layout.category_list_item,viewGroup,false);
        return new SearchResultPublisherHolder(view,onClickSearchPublisher);

    }

    public void initResource(View view){
        tvName=view.findViewById(R.id.tv_category_list);
        layout=view.findViewById(R.id.linear_categories);
    }

    public void bindData(final SearchPublisherResponse searchPublisherResponse){

        tvName.setText(searchPublisherResponse.publisherName);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onclickSearchPublisher.clickSearchPublisher(searchPublisherResponse.id);
            }
        });
    }
}
