package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ndsp.Adapter.PublisherAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.Publisherholders;
import com.example.ndsp.Pojo.PublisherDetailResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.example.ndsp.model.PublisherDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PublisherListFragment extends Fragment implements Publisherholders.OnPublisherClickListener {

    private RetrofitService retrofitService=new RetrofitService();
    List<PublisherDetail> publishercategories = new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView txtTitle,txtName,txtPrice;
    private ImageView imageView;
    private PublisherAdapter publisherAdapter;
    int publisherId;


    public PublisherListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_publisher_list, container, false);
        initPublisher(view);
        return view;
    }

    public void initPublisher(View view){
        recyclerView=view.findViewById(R.id.publisher_recycler);
        imageView=view.findViewById(R.id.textViewOptions);
        txtTitle=view.findViewById(R.id.tvtitle);
        txtName=view.findViewById(R.id.tvname);
        txtPrice=view.findViewById(R.id.tvprice);
        publisherAdapter=new PublisherAdapter(this);

        Bundle bundle=this.getArguments();
        if (bundle !=null){
            publisherId=bundle.getInt("publisher_id");
        }
        Log.e("publisher_book_list_id", String.valueOf(publisherId));
        getPublisherDetail(view);
    }
    private void getPublisherDetail(View view) {

        Log.e("getpublisherdetail","success");
        Api publisherDetailApi = retrofitService.getRetrofitService().create(Api.class);

        publisherDetailApi.getPublisherDetail(publisherId).enqueue(new Callback<PublisherDetailResponse>() {
            @Override
            public void onResponse(Call<PublisherDetailResponse> call, Response<PublisherDetailResponse> response) {
                if(response.isSuccessful()){

                    //output publishersize
                    publishercategories.addAll(response.body().publishers);
                    Log.e("publisherList", String.valueOf(response.body().publishers.size()));

                    publisherAdapter.addItem(response.body().publishers);
                    Log.e("publisherList", String.valueOf(response.body().publishers.size()));
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
                    recyclerView.setAdapter(publisherAdapter);

                }
            }

            @Override
            public void onFailure(Call<PublisherDetailResponse> call, Throwable t) {

                Log.e("onfailure", t.toString());
            }
        });


    }


    @Override
    public void onPublisherClick(int id) {
        FragmentRecentBookDetail fragmentRecentBookDetail=new FragmentRecentBookDetail();
        Bundle bundle=new Bundle();
        bundle.putInt("book_id",id);
        fragmentRecentBookDetail.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragmentRecentBookDetail).commit();

        Log.e("book_id", String.valueOf(id));
    }
}
