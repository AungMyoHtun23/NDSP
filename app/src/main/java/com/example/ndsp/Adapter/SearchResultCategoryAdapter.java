package com.example.ndsp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.ndsp.Holder.SearchResultCategoryHolder;
import com.example.ndsp.Pojo.SearchCategoryResponse;
import java.util.ArrayList;
import java.util.List;



public class SearchResultCategoryAdapter extends RecyclerView.Adapter<SearchResultCategoryHolder> {

    private ArrayList<SearchCategoryResponse>searchCategoryResponses=new ArrayList<>();
    private SearchResultCategoryHolder.OnClickSearchCategory onClickSearchCategory;

    public SearchResultCategoryAdapter(SearchResultCategoryHolder.OnClickSearchCategory onClickSearchCategory) {
        this.onClickSearchCategory = onClickSearchCategory;
    }

    @NonNull
    @Override
    public SearchResultCategoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(viewGroup.getContext());
        return SearchResultCategoryHolder.create(inflater,viewGroup,onClickSearchCategory);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultCategoryHolder searchResultCategoryHolder, int i) {
        searchResultCategoryHolder.bindData(searchCategoryResponses.get(i));

    }


    @Override
    public int getItemCount() {
        return searchCategoryResponses.size();
    }

    public void addItem(List<SearchCategoryResponse>searchCategoryResponses){
        this.searchCategoryResponses= (ArrayList<SearchCategoryResponse>) searchCategoryResponses;
        notifyDataSetChanged();
    }
}
