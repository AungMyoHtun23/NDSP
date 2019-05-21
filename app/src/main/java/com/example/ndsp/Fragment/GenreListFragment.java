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
import android.widget.LinearLayout;

import com.example.ndsp.Adapter.AuthorAdapter;

import com.example.ndsp.Adapter.GenreAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.GenreHolder;
import com.example.ndsp.Pojo.GenreDetailResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.example.ndsp.model.GenreDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GenreListFragment extends Fragment implements GenreHolder.OnGenreClickListener{

    private RetrofitService service;
    private RecyclerView recyclerView;
    private int BookList;
    List<GenreDetail> genrecategories = new ArrayList<>();
    private LinearLayoutManager layoutManager;
    private GenreAdapter adapter;


    public GenreListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_author_list, container, false);
        initActivity(view);
        return view;
    }
    private void initActivity(View view) {

        service = new RetrofitService();
        recyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new GenreAdapter(this);
        recyclerView.setAdapter(adapter);

        Bundle bundle =this.getArguments();
        if (bundle!=null){
            BookList=bundle.getInt("BookList");
        }
        Log.e("genre_id", String.valueOf(BookList));

        getGenreDetail(view);
    }
    private void getGenreDetail(View view) {

        Log.e("getgenreDetail","success");
        Api genreDetailApi = service.getRetrofitService().create(Api.class);
        genreDetailApi.getGenreDetail(BookList).enqueue(new Callback<GenreDetailResponse>() {
            @Override
            public void onResponse(Call<GenreDetailResponse> call, Response<GenreDetailResponse> response) {

                if(response.isSuccessful()){

                    genrecategories.addAll(response.body().genre);
                    adapter.addItem(response.body().genre);
                    Log.e("genreList",String.valueOf(response.body().genre));
                }
            }

            @Override
            public void onFailure(Call<GenreDetailResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onGenreClick(int id) {
        FragmentRecentBookDetail fragmentRecentBookDetail=new FragmentRecentBookDetail();
        Bundle bundle=new Bundle();
        bundle.putInt("book_id",id);
        fragmentRecentBookDetail.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragmentRecentBookDetail).commit();

        Log.e("book_list_id",String.valueOf(id));

    }
}
