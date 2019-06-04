package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ndsp.Adapter.SearchResultPublisherAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.SearchResultPublisherHolder;
import com.example.ndsp.Pojo.SearchPublisherResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchResultPublisherFragment extends Fragment{

    private ArrayList<SearchPublisherResponse>searchPublisherResponses=new ArrayList<>();
    List<String>list=new ArrayList<>();
    ArrayAdapter<String > adapter;
    private ListView listView;
    String publisherNames;
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

        listView=view.findViewById(R.id.rv_search_publisher);
        getPublisherApi(view);

        Bundle bundle=this.getArguments();
        if (bundle!=null){
            publisherNames=bundle.getString("publisher_id");
            Log.e("publisher_ID",publisherNames);
        }

        for (int i = 0; i < searchPublisherResponses.size(); i++) {
            list.add(searchPublisherResponses.get(i).publisherName);
        }
        adapter = new ArrayAdapter<>( getActivity(),android.R.layout.simple_list_item_1,list);
            listView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
    }

    public void getPublisherApi(View view){
        Api api=service.getRetrofitService().create(Api.class);
        api.getPublisherSearch(publisherNames).enqueue(new Callback<ArrayList<SearchPublisherResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchPublisherResponse>> call, Response<ArrayList<SearchPublisherResponse>> response) {
                if (response.isSuccessful()){

                    for (int i = 0; i < searchPublisherResponses.size(); i++){
                        list.add(response.body().get(i).publisherName);
                        list.add(String.valueOf(response.body().get(i).id));
                        list.add(searchPublisherResponses.get(i).publisherName);

                    }
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                    Log.e("search_publisher_sucess", String.valueOf(response.body().size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchPublisherResponse>> call, Throwable t) {
                Log.e("search_pub_Fail",t.toString());

            }
        });
    }

}
