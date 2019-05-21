package com.example.ndsp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.ndsp.Holder.RecentBooksRecyclerHolder;
import com.example.ndsp.Pojo.RecentProduct;
import com.example.ndsp.Pojo.TopTenResponse;

import java.util.ArrayList;

public class RecentBooksRecyclerAdapter extends RecyclerView.Adapter<RecentBooksRecyclerHolder> {
    private ArrayList<RecentProduct> recentProducts=new ArrayList<>();
    private ArrayList<TopTenResponse>topTenResponses=new ArrayList<>();
    private Context context;
    private RecentBooksRecyclerHolder.OnItemClicked onItemClicked;



    public RecentBooksRecyclerAdapter(Context context, RecentBooksRecyclerHolder.OnItemClicked onItemClicked) {
        this.context=context;
        this.onItemClicked=onItemClicked;


    }

    @NonNull
    @Override
    public RecentBooksRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return RecentBooksRecyclerHolder.create(inflater,viewGroup,onItemClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentBooksRecyclerHolder viewHolder, int i) {
      viewHolder.bindData(recentProducts.get(i));

    }

    @Override
    public int getItemCount() {
        return recentProducts.size();
    }

    public void setRecentProductsList(ArrayList<RecentProduct>recentProducts){
        this.recentProducts=recentProducts;
        notifyDataSetChanged();

    }

    public void setRecentProductsList1(ArrayList<TopTenResponse> body) {
        this.topTenResponses=body;
        notifyDataSetChanged();

    }
}
