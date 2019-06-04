package com.example.ndsp.Holder;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
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
import com.example.ndsp.Language.Rabbit;
import com.example.ndsp.R;
import com.example.ndsp.model.AuthorDetail;
import com.squareup.picasso.Picasso;

public class AuthorHolder extends RecyclerView.ViewHolder{
    public static final String LANGUAGE_PREFERENCE = "lan_pref", PREFERENCE_KEY = "lan_key";
    Context context;

    private OnAuthorClickListener listener;
    private TextView title,price,name;
    private ImageView imageView,buttonOptionMenu;
    LinearLayout layout;
    SharedPreferences sharedPreferences;

    public AuthorHolder(@NonNull View itemView, OnAuthorClickListener listener,Context context) {

        super(itemView);
        this.context=context;
        this.listener = listener;

        initView(itemView);
    }

    private void initView(View itemView) {

        title = itemView.findViewById(R.id.tvtitle);
        price = itemView.findViewById(R.id.tvprice);
        buttonOptionMenu=itemView.findViewById(R.id.textViewOptions);
        name = itemView.findViewById(R.id.tvname);
        imageView= itemView.findViewById(R.id.profile);
        layout=itemView.findViewById(R.id.author_layout_list);
        sharedPreferences =context.getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE);
    }

    public void bindData(final AuthorDetail author) {

        if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("z")){
            title.setText(Rabbit.uni2zg(author.booktitle));
            price.setText(Rabbit.uni2zg(String.valueOf(author.booksaleprice)));
            name.setText(Rabbit.uni2zg(author.author));
        }else if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("u")){
            title.setText(Rabbit.zg2uni(author.booktitle));
            price.setText(Rabbit.zg2uni(String.valueOf(author.booksaleprice)));
            name.setText(Rabbit.zg2uni(author.author));
        }else {
            title.setText(author.booktitle);
            price.setText(String.valueOf(author.booksaleprice));
            name.setText(author.author);
        }

        Log.e("bookcovername",author.bookcover);
        Picasso.get()
                .load("http://128.199.217.182/api/image/book/" + author.bookcover)
                .resize(700,900)
                .centerCrop()
                .into(imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAuthorClick(author.id);

            }
        });

        buttonOptionMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

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
                                listener.onAuthorClick(author.id);

                                break;

                        }
                        return false;
                    }
                });
                popup.show();

            }
        });

    }

    public static AuthorHolder create(LayoutInflater inflater, ViewGroup viewGroup, OnAuthorClickListener listener,Context context) {

        View view = inflater.inflate(R.layout.layout_author_list, viewGroup, false);
        return new AuthorHolder(view, listener,context);
    }

    public interface  OnAuthorClickListener  {
        void onAuthorClick(int id);

    }
}
