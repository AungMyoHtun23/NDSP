package com.example.ndsp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ndsp.Holder.GenreHolder;
import com.example.ndsp.model.Genre;
import com.example.ndsp.model.GenreDetail;

import java.util.ArrayList;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreHolder> {
    List<GenreDetail> authorLists;
    GenreHolder.OnGenreClickListener onGenreClickListener;

    public GenreAdapter(GenreHolder.OnGenreClickListener listener) {

        authorLists = new ArrayList<>();
        this.onGenreClickListener=listener;

            }


    @NonNull
    @Override
    public GenreHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater=LayoutInflater.from(viewGroup.getContext());
        return GenreHolder.create(layoutInflater,viewGroup,onGenreClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreHolder genreHolder, int i) {
        genreHolder.bindData(authorLists.get(i));

    }

    @Override
    public int getItemCount() {
        return authorLists.size();
    }

    public void addItem(List<GenreDetail>genreDetails){
        this.authorLists=genreDetails;
        notifyDataSetChanged();

    }

}
