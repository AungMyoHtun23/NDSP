package com.example.ndsp.Holder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.ndsp.Fragment.FragmentRecentBookDetail;
import com.example.ndsp.Pojo.BookListByCategoryDataResponse;
import com.example.ndsp.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CategoryItemHolder extends RecyclerView.ViewHolder {
    List<BookListByCategoryDataResponse>bookListByCategoryDataResponses=new ArrayList<>();
    private OnItemClickListener listener;
    private TextView title,price,name;
    private ImageView imageView,buttonOptionMenu;
    LinearLayout layout;

    public CategoryItemHolder(@NonNull View itemView, OnItemClickListener  listener) {
        super(itemView);
        this.listener=listener;
        initView(itemView);

    }

    private void initView(View itemView) {

        title = itemView.findViewById(R.id.tvtitle);
        price = itemView.findViewById(R.id.tvprice);
        buttonOptionMenu=itemView.findViewById(R.id.textViewOptions);
        name = itemView.findViewById(R.id.tvname);
        imageView= itemView.findViewById(R.id.profile);
        layout=itemView.findViewById(R.id.author_layout_list);
    }

    public void bindData(final BookListByCategoryDataResponse bookListByCategoryDataResponse){

        title.setText(bookListByCategoryDataResponse.bookTitle);
        price.setText(String.valueOf(bookListByCategoryDataResponse.bookSalePrice));
        name.setText(bookListByCategoryDataResponse.author);

        Picasso.get()
                .load("http://128.199.217.182/api/image/book/" + bookListByCategoryDataResponse.bookCoverImgUrl)
                .resize(700,900)
                .centerCrop()
                .into(imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onitemclicklistener(bookListByCategoryDataResponse.id);
//                Log.e("bookListID", String.valueOf(bookListByCategoryDataResponse.id));

                buttonOptionMenu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        //creating a popup menu
                        PopupMenu popup = new PopupMenu(view.getContext(), buttonOptionMenu);
                        //inflating menu from xml resource
                        popup.inflate(R.menu.option_popup_menu);
                        //adding click listener
                        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                switch (item.getItemId()) {
                                    case R.id.menu1:
                                        break;
                                    case R.id.menu2:
                                        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                                            @Override
                                            public boolean onMenuItemClick(MenuItem menuItem) {
                                                FragmentRecentBookDetail fragmentRecentBookDetail=new FragmentRecentBookDetail();
                                                Bundle bundle=new Bundle();
                                                fragmentRecentBookDetail.setArguments(bundle);

                                                return false;
                                            }
                                        });
                                        break;

                                }
                                return false;
                            }
                        });
                        popup.show();

                    }
                });
            }
        });

    }


    public static CategoryItemHolder create(LayoutInflater inflater, ViewGroup viewGroup,CategoryItemHolder.OnItemClickListener listener) {

        View view = inflater.inflate(R.layout.layout_author_list, viewGroup, false);
        return new CategoryItemHolder(view,listener);
    }

    public interface  OnItemClickListener  {
        void onitemclicklistener(int id);

    }

}

