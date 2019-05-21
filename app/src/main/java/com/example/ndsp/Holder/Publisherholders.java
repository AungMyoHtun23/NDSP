package com.example.ndsp.Holder;

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
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ndsp.R;
import com.example.ndsp.model.PublisherDetail;
import com.squareup.picasso.Picasso;

import static com.example.ndsp.RetrofitService.RetrofitService.BASE_URL;

public class Publisherholders extends RecyclerView.ViewHolder{

    private OnPublisherClickListener listener;
    private TextView title,price,name,tvid;
    private ImageView imageView,buttonoptionmenu;
    RatingBar bar;
    LinearLayout layout;

    public Publisherholders(@NonNull View itemView, OnPublisherClickListener listener) {

        super(itemView);
        this.listener = listener;
        initView(itemView);
//        itemView.setOnClickListener();
    }



    private void initView(View itemView) {

        title = itemView.findViewById(R.id.tv_title);
        price = itemView.findViewById(R.id.tv_price);
        name = itemView.findViewById(R.id.tv_name);
        bar=itemView.findViewById(R.id.rating_Bar);
        imageView= itemView.findViewById(R.id.profile_list);
        buttonoptionmenu=itemView.findViewById(R.id.textView_Options);
        layout = itemView.findViewById(R.id.publisher_layout_list);

    }

    public static Publisherholders create(LayoutInflater inflater, ViewGroup viewGroup, OnPublisherClickListener listener) {

        View view = inflater.inflate(R.layout.layout_publisher_list, viewGroup, false);
        return new Publisherholders(view,listener);
    }

    public void bindData(final PublisherDetail publisher) {
        Log.e("Publisher_ID",String.valueOf(publisher.id));

        title.setText(publisher.booktitle);
        price.setText(String.valueOf(publisher.booksaleprice));
        name.setText(publisher.author);

//        Log.e("bookcovername",publisher.bookcover);


        Picasso.get().load(BASE_URL+publisher.bookcover).into(imageView);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onPublisherClick(publisher.id);
            }
        });

    }

//    @Override
//    public void onClick(View v) {
//
//        listener.onPublisherClick(Integer.parseInt(tvid.getText().toString()));
//        int position;
//        position = getAdapterPosition();
//        Log.e("position",String.valueOf(position));
//
//    }
    public interface  OnPublisherClickListener {
         void onPublisherClick(int id);
    }

}


