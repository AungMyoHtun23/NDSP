package com.example.ndsp.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ndsp.Holder.EbookRecyclerHolder;
import com.example.ndsp.Pojo.BookListByCategoryDataResponse;
import com.example.ndsp.Pojo.EbookDetail;
import com.example.ndsp.Pojo.TenCategoryResponse;

import java.util.ArrayList;

public class EbookRecyclerAdapter extends RecyclerView.Adapter<EbookRecyclerHolder> {
    private ArrayList<TenCategoryResponse> tenCategoryResponses;
    private ArrayList<BookListByCategoryDataResponse>bookListByCategoryDataResponses=new ArrayList<>();
    private EbookRecyclerHolder.OnItemClicked2 onItemClicked;
    private Context context;

    public EbookRecyclerAdapter(Context context, EbookRecyclerHolder.OnItemClicked2 onItemClicked) {
        this.tenCategoryResponses=new ArrayList<>();
        this.context=context;
        this.onItemClicked= onItemClicked;
    }



    @NonNull
    @Override
    public EbookRecyclerHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return EbookRecyclerHolder.create(inflater,viewGroup,onItemClicked,context);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull EbookRecyclerHolder ebookRecyclerHolder, int i) {
        ebookRecyclerHolder.bindData(tenCategoryResponses.get(i));

    }

    @Override
    public int getItemCount() {
        return tenCategoryResponses.size();
    }

    public void setTenEbookResponses(ArrayList<TenCategoryResponse>tenCategoryResponses){
        this.tenCategoryResponses=tenCategoryResponses;
        notifyDataSetChanged();
    }

}
