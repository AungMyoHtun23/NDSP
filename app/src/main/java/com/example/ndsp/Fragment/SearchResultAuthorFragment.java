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
import android.widget.ProgressBar;

import com.example.ndsp.Adapter.SearchResultAuthorAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.SearchResultAuthorHolder;
import com.example.ndsp.Pojo.SearchAuthorResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultAuthorFragment extends Fragment {
    public ArrayList<SearchAuthorResponse>searchAuthorResponses=new ArrayList<>();
    ArrayAdapter<String > adapter;
    List<String>list=new ArrayList<>();
    private String authorName;
    private ListView listView;
    private RetrofitService retrofitService=new RetrofitService();


    public SearchResultAuthorFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search_result_author, container, false);
        initResource(view);
        return view;
    }

    public void initResource(View view){

        listView=view.findViewById(R.id.sr_author_list);

        Bundle bundle=this.getArguments();
        if (bundle!=null){

            authorName=bundle.getString("author_id");
            Log.e("authorID",authorName);

        }

        for (int i = 0; i<searchAuthorResponses.size(); i ++){

            list.add(searchAuthorResponses.get(i).authorName);

        }
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        getAuthorList(view);

    }

    public void getAuthorList(View view){
        Api api=retrofitService.getRetrofitService().create(Api.class);
        api.getAuthorSearch(authorName).enqueue(new Callback<ArrayList<SearchAuthorResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchAuthorResponse>> call, Response<ArrayList<SearchAuthorResponse>> response) {
                if (response.isSuccessful()){
                    Log.e("search_author_success", String.valueOf(response.body().size()));

                    for (int i = 0; i<searchAuthorResponses.size(); i ++){

                        list.add(searchAuthorResponses.get(i).authorName);

                    }
                    adapter.getFilter().filter(authorName);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchAuthorResponse>> call, Throwable t) {
                Log.e("search_author_fail",t.toString());
            }
        });
    }


}
