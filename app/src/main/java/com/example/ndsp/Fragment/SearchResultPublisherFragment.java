package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ndsp.Adapter.SearchResultPublisherAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.SearchResultPublisherHolder;
import com.example.ndsp.Pojo.SearchPublisherResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchResultPublisherFragment extends Fragment implements SearchResultPublisherHolder.OnClickSearchPublisher {

    private RecyclerView recyclerView;
    private SearchResultPublisherAdapter adapter;
    private LinearLayoutManager layoutManager;
    String publisherName;
    private RetrofitService service=new RetrofitService();


    public SearchResultPublisherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_search_result_publisher, container, false);
        return view;
    }

    public void initResource(View view){
        recyclerView=getActivity().findViewById(R.id.rv_search_publisher);
        layoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new SearchResultPublisherAdapter(this);

        Bundle bundle=this.getArguments();
        if (bundle!=null){
            publisherName=bundle.getString("publisher_id");
        }

        getPublisherApi(view);
    }

    public void getPublisherApi(View view){
        Api api=service.getRetrofitService().create(Api.class);
        api.getPublisherSearch(publisherName).enqueue(new Callback<ArrayList<SearchPublisherResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchPublisherResponse>> call, Response<ArrayList<SearchPublisherResponse>> response) {
                if (response.isSuccessful()){
                    Log.e("search_publisher_sucess", String.valueOf(response.body().size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchPublisherResponse>> call, Throwable t) {

            }
        });
    }

    @Override
    public void clickSearchPublisher(int id) {

    }
}
