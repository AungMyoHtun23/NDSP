package com.example.ndsp.Fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ndsp.Adapter.AuthorAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.AuthorHolder;
import com.example.ndsp.Pojo.AuthorDetailResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.example.ndsp.model.AuthorDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class  AuthorListFragment extends Fragment implements AuthorHolder.OnAuthorClickListener {

    private RetrofitService service;
    private RecyclerView recyclerView;
    private TextView tvtitle,tvname,tvprice;
    private ImageView imageView;
    private int authorBookList;
    List<AuthorDetail> authorcategories = new ArrayList<>();
    private AuthorAdapter adapter;
    private LinearLayoutManager manager;
    private List<String> categoryName;
    private SwipeRefreshLayout refreshLayout;


    public AuthorListFragment() {
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
        recyclerView =view.findViewById(R.id.recyclerView);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvname = view.findViewById(R.id.tvname);
        tvprice = view.findViewById(R.id.tvprice);
        imageView = view.findViewById(R.id.profile);
        refreshLayout=view.findViewById(R.id.swipeRefresh);

        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new AuthorAdapter(this,getContext());
        recyclerView.setAdapter(adapter);

        Bundle bundle =this.getArguments();
        if(bundle != null){
            authorBookList= bundle.getInt("author_id");
            categoryName= Collections.singletonList(bundle.getString("author_name"));
        }
        Log.e("author_list_id", String.valueOf(authorBookList));

        getAuthorDetail();

    }
    private void getAuthorDetail() {

        Api authorDetailApi = service.getRetrofitService().create(Api.class);
        authorDetailApi.getAuthorDetail(authorBookList).enqueue(new Callback<AuthorDetailResponse>() {
            @Override
            public void onResponse(Call<AuthorDetailResponse> call, Response<AuthorDetailResponse> response) {

                if(response.isSuccessful()){

                    Log.e("getauthorDetail","success");

                    //output authorsize
                    authorcategories.addAll(response.body().author);
                    Log.e("authorList", String.valueOf(response.body().author.size()));

                    adapter.addItem(response.body().author);
                    Log.e("authorList", String.valueOf(response.body().author.size()));
//                    recyclerView.setLayoutManager(manager);
//                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<AuthorDetailResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onAuthorClick(int id) {
        FragmentRecentBookDetail fragmentRecentBookDetail=new FragmentRecentBookDetail();
        Bundle bundle=new Bundle();
        bundle.putInt("book_id",id);
        bundle.putString("book_type", String.valueOf(categoryName));
        fragmentRecentBookDetail.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragmentRecentBookDetail).commit();

        Log.e("book_list_id",String.valueOf(id));
    }

    public String getString(List<String> categoryname) {
        this.categoryName=categoryname;
        return null;
    }
}
