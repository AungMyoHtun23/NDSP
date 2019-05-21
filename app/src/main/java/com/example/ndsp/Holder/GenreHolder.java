package com.example.ndsp.Holder;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.ndsp.Fragment.FragmentRecentBookDetail;
import com.example.ndsp.R;
import com.example.ndsp.model.AuthorDetail;
import com.example.ndsp.model.GenreDetail;
import com.squareup.picasso.Picasso;

public class GenreHolder  extends RecyclerView.ViewHolder {
    private GenreHolder.OnGenreClickListener listener;
    private TextView title,price,name,tvid;
    private ImageView imageView,buttonOptionMenu;
    LinearLayout layout;

    public GenreHolder(@NonNull View itemView,GenreHolder.OnGenreClickListener genreClickListener) {

        super(itemView);
        this.listener=genreClickListener;
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

    public void bindData(final GenreDetail author) {
        Log.e("author_ID",String.valueOf(author.id));
        title.setText(author.booktitle);
        price.setText(String.valueOf(author.booksale));
        name.setText(author.author);

        Log.e("bookcovername",author.bookcover);

        Picasso.get()
                .load("http://128.199.217.182/api/image/book/" + author.bookcover)
                .resize(700,900)
                .centerCrop()
                .into(imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onGenreClick(author.id);

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

    public static GenreHolder create(LayoutInflater inflater, ViewGroup viewGroup,GenreHolder.OnGenreClickListener listener) {

        View view = inflater.inflate(R.layout.layout_author_list, viewGroup, false);
        return new GenreHolder(view,listener);
    }


    public interface  OnGenreClickListener  {
        void onGenreClick(int id);

    }
}
