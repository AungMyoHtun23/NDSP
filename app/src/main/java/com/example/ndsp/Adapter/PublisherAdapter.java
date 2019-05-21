package com.example.ndsp.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.ndsp.Holder.Publisherholders;
import com.example.ndsp.model.PublisherDetail;

import java.util.ArrayList;
import java.util.List;

public class PublisherAdapter extends RecyclerView.Adapter<Publisherholders> {

    List<PublisherDetail> publisherLists ;
    Publisherholders.OnPublisherClickListener listener;


    public PublisherAdapter(Publisherholders.OnPublisherClickListener listener){

        publisherLists =new ArrayList<>();
        this.listener=listener;
    }

    @NonNull
    @Override
    public Publisherholders onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        return Publisherholders.create(inflater,viewGroup,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull Publisherholders publisherholder, int i) {

        publisherholder.bindData(publisherLists.get(i));
    }


    @Override
    public int getItemCount() {
        return publisherLists.size();
    }


    public void addItem(List<PublisherDetail> publisher){

        this.publisherLists = publisher;
        notifyDataSetChanged();

    }

}


