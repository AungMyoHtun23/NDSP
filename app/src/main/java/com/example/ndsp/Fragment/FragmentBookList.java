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

import com.example.ndsp.Adapter.AuthorAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.AuthorHolder;
import com.example.ndsp.Pojo.RecentBookAllList;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.example.ndsp.model.AuthorDetail;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBookList extends Fragment implements AuthorHolder.OnAuthorClickListener{
    private RetrofitService retrofitService=new RetrofitService();
    private RecyclerView recyclerView;
    private TextView tvtitle,tvname,tvprice;
    private ImageView imageView;
    List<AuthorDetail> recentBookSeeAllLists = new ArrayList<com.example.ndsp.model.AuthorDetail>();
    private AuthorAdapter adapter;
    private LinearLayoutManager manager;


    public FragmentBookList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_book_list, container, false);
        initResource(view);
        return view;
    }

    public void initResource(View view){

        retrofitService = new RetrofitService();
        recyclerView =view.findViewById(R.id.recyclerView);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvname = view.findViewById(R.id.tvname);
        tvprice = view.findViewById(R.id.tvprice);
        imageView = view.findViewById(R.id.profile);

        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new AuthorAdapter(this);
        recyclerView.setAdapter(adapter);
        getApi(view);

    }

    public void getApi(View view){
        Api api=retrofitService.getRetrofitService().create(Api.class);
        api.getRecentBookAllList().enqueue(new Callback<RecentBookAllList>() {
            @Override
            public void onResponse(Call<RecentBookAllList> call, Response<RecentBookAllList> response) {

                recentBookSeeAllLists.addAll(response.body().getBookListData);
                Log.e("EbookList", String.valueOf(response.body().getBookListData));

                adapter.addItem(response.body().getBookListData);
                Log.e("EbookItem",String.valueOf(response.body().getBookListData));
            }

            @Override
            public void onFailure(Call<RecentBookAllList> call, Throwable t) {

                Log.e("onFailureEbook",t.toString());

            }
        });
    }

    @Override
    public void onAuthorClick(int id) {
        FragmentRecentBookDetail fragmentRecentBookDetail=new FragmentRecentBookDetail();
        Bundle bundle=new Bundle();
        bundle.putInt("book_id",id);
        fragmentRecentBookDetail.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragmentRecentBookDetail).commit();

        Log.e("book_list_id",String.valueOf(id));

    }
}
