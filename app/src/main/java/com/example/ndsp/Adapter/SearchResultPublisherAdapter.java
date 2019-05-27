package com.example.ndsp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ndsp.Holder.SearchResultPublisherHolder;
import com.example.ndsp.Pojo.SearchPublisherResponse;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPublisherAdapter extends RecyclerView.Adapter<SearchResultPublisherHolder> {

    private List<SearchPublisherResponse>searchPublisherResponses=new ArrayList<>();
    private SearchResultPublisherHolder.OnClickSearchPublisher onClickSearchPublisher;

    public SearchResultPublisherAdapter(SearchResultPublisherHolder.OnClickSearchPublisher onClickSearchPublisher) {
        this.onClickSearchPublisher=onClickSearchPublisher;
    }

    @NonNull
    @Override
    public SearchResultPublisherHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return SearchResultPublisherHolder.create(inflater,viewGroup,onClickSearchPublisher);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultPublisherHolder searchResultPublisherHolder, int i) {
        searchResultPublisherHolder.bindData(searchPublisherResponses.get(i));

    }

    @Override
    public int getItemCount() {
        return searchPublisherResponses.size();
    }
}
