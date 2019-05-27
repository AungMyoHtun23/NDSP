package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ndsp.Adapter.SearchResultAuthorAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.SearchResultAuthorHolder;
import com.example.ndsp.Pojo.SearchAuthorResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultAuthorFragment extends Fragment implements SearchResultAuthorHolder.OnClickSearchAuthor {
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private String authorName;
    private LinearLayoutManager linearLayoutManager;
    private SearchResultAuthorAdapter adapter;
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
        recyclerView=getActivity().findViewById(R.id.sr_author_rv);
        linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new SearchResultAuthorAdapter(this);
        recyclerView.setAdapter(adapter);
        Bundle bundle=this.getArguments();
        if (bundle!=null){
            authorName=bundle.getString("author_id");

        }
        getAuthorList(view);

    }

    public void getAuthorList(View view){
        Api api=retrofitService.getRetrofitService().create(Api.class);
        api.getAuthorSearch(authorName).enqueue(new Callback<ArrayList<SearchAuthorResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchAuthorResponse>> call, Response<ArrayList<SearchAuthorResponse>> response) {
                if (response.isSuccessful()){
                    Log.e("search_author_success", String.valueOf(response.body().size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<SearchAuthorResponse>> call, Throwable t) {
                Log.e("search_author_fail",t.toString());
            }
        });
    }

    @Override
    public void clickSearchAuthor(int id) {

    }
}
