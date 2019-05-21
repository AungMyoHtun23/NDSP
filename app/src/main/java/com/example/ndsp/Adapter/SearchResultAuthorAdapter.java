package com.example.ndsp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Fragment.SearchResultAuthorFragment;
import com.example.ndsp.Holder.SearchResultAuthorHolder;
import com.example.ndsp.Pojo.SearchAuthorResponse;
import com.example.ndsp.R;
import com.example.ndsp.model.Author;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAuthorAdapter extends RecyclerView.Adapter<SearchResultAuthorHolder> {

    private List<SearchAuthorResponse> authorResponses = new ArrayList<>();
    private SearchResultAuthorHolder.OnClickSearchAuthor onClickSearchAuthor;

    public SearchResultAuthorAdapter(SearchResultAuthorHolder.OnClickSearchAuthor onClickSearchAuthor){
        this.onClickSearchAuthor=onClickSearchAuthor;
    }

    @NonNull
    @Override
    public SearchResultAuthorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return SearchResultAuthorHolder.create(inflater, viewGroup, onClickSearchAuthor);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultAuthorHolder searchResultAuthorHolder, int i) {
        searchResultAuthorHolder.bindData(authorResponses.get(i));

    }

    @Override
    public int getItemCount() {
        return authorResponses.size();
    }

    public void addItem(List<SearchAuthorResponse> searchAuthorResponses) {
        this.authorResponses = searchAuthorResponses;
        notifyDataSetChanged();
    }
}
