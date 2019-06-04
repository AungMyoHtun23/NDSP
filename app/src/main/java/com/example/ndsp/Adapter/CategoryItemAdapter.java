package com.example.ndsp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.example.ndsp.Fragment.CategoryItemFragment;
import com.example.ndsp.Holder.CategoryItemHolder;
import com.example.ndsp.Pojo.BookListByCategoryDataResponse;
import com.example.ndsp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryItemAdapter extends RecyclerView.Adapter<CategoryItemHolder> {
    List<BookListByCategoryDataResponse>bookListByCategoryDataResponses=new ArrayList<>();
    CategoryItemHolder.OnItemClickListener listener;
    Context context;

    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;

    public CategoryItemAdapter(CategoryItemHolder.OnItemClickListener listener,Context context) {
        this.listener=listener;
        this.context=context;
    }


//
//        switch (i){
//            case VIEW_TYPE_NORMAL:
//                return new CategoryItemHolder(
//                        LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_category_item,viewGroup,false));
//
//            case VIEW_TYPE_LOADING:
//                return new FooterHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_loading,viewGroup,false));
//
//                default:
//                    return null;
//        }



    @NonNull
    @Override
    public CategoryItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return CategoryItemHolder.create(inflater,viewGroup,listener,context);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryItemHolder categoryItemHolder, int i) {
        categoryItemHolder.bindData(bookListByCategoryDataResponses.get(i));
        Log.e("bookListBycategoryID", String.valueOf(bookListByCategoryDataResponses.get(i)));

    }

    @Override
    public int getItemCount() {
        return bookListByCategoryDataResponses==null?0:bookListByCategoryDataResponses.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == bookListByCategoryDataResponses.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }
    public void addItem(List<BookListByCategoryDataResponse>bookListByCategoryDataResponses){
        this.bookListByCategoryDataResponses=bookListByCategoryDataResponses;
        notifyDataSetChanged();
    }

    public void add(BookListByCategoryDataResponse response) {
        bookListByCategoryDataResponses.add(response);
        notifyItemInserted(bookListByCategoryDataResponses.size() - 1);
    }

    public void addAll(List<BookListByCategoryDataResponse>bookListByCategoryDataResponses){
        for (BookListByCategoryDataResponse response:bookListByCategoryDataResponses){
            add(response);
        }
    }

    public void remove(BookListByCategoryDataResponse byCategoryDataResponse){
        int position= bookListByCategoryDataResponses.indexOf(byCategoryDataResponse);
        if (position>-1){
            bookListByCategoryDataResponses.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addloading(){
        isLoaderVisible=true;
        add(new BookListByCategoryDataResponse());
    }

    public void removeloading(){
        isLoaderVisible=false;
        int position=bookListByCategoryDataResponses.size()-1;
        BookListByCategoryDataResponse dataResponse=getItem(position);
        if (dataResponse !=null){
            bookListByCategoryDataResponses.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear(){
        while (getItemCount()>0){
            remove(getItem(0));
        }
    }

    BookListByCategoryDataResponse getItem(int position){
        return bookListByCategoryDataResponses.get(position);
    }
//    public class FooterHolder extends CategoryItemHolder {
//
//        @BindView(R.id.progressBar)
//        ProgressBar mProgressBar;
//
//
//        FooterHolder(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//
//    }
}
