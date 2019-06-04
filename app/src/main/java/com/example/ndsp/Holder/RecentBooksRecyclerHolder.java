package com.example.ndsp.Holder;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Language.Rabbit;
import com.example.ndsp.Pojo.RecentProduct;
import com.example.ndsp.R;
import com.squareup.picasso.Picasso;

import static com.example.ndsp.RetrofitService.RetrofitService.BASE_URL;

public class RecentBooksRecyclerHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;
    private TextView txtrecent;
    private LinearLayout layout;
    private Context context;
    SharedPreferences sharedPreferences;
    public static final String LANGUAGE_PREFERENCE = "lan_pref", PREFERENCE_KEY = "lan_key";

//    Book_Id book_id=new Book_Id();


    public interface OnItemClicked{ void onItemClick(int position);}
    OnItemClicked onItemClicked;

    public RecentBooksRecyclerHolder(@NonNull View itemView, OnItemClicked onItemClicked,Context context) {
        super(itemView);
        this.context=context;
        this.onItemClicked=onItemClicked;
        initHolder(itemView);

    }

    public void initHolder(View itemView){

        imageView=itemView.findViewById(R.id.recent_book_image);
        txtrecent=itemView.findViewById(R.id.recent_book_text);
        layout=itemView.findViewById(R.id.recent_book_layout);
        sharedPreferences=context.getSharedPreferences(LANGUAGE_PREFERENCE,Context.MODE_PRIVATE);

    }

    public void bindData(final RecentProduct recentProduct){

        if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("z")){
            txtrecent.setText(Rabbit.uni2zg(recentProduct.bookTitle));
        }else if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("u")){
            txtrecent.setText(Rabbit.zg2uni(recentProduct.bookTitle));
        }else {
            txtrecent.setText(recentProduct.bookTitle);

        }

        //Picasso
        Picasso.get().load(BASE_URL+"/api/image/book/"+recentProduct.bookCoverImgUrl).into(imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Log.d("book_id", String.valueOf(recentProduct.id));
               onItemClicked.onItemClick(recentProduct.id);
            }
        });
    }

    public static RecentBooksRecyclerHolder create(LayoutInflater inflater, ViewGroup viewGroup,OnItemClicked onItemClicked,Context context){
        View view=inflater.inflate(R.layout.recent_book_layout,viewGroup,false);

        return new RecentBooksRecyclerHolder(view,onItemClicked,context);
    }

}
