package com.example.ndsp.Fragment;


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

import com.example.ndsp.Adapter.CategoryItemAdapter;
import com.example.ndsp.Adapter.EbookRecyclerAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.CategoryItemHolder;
import com.example.ndsp.Holder.EbookRecyclerHolder;
import com.example.ndsp.Pojo.BookListByCategoryDataResponse;
import com.example.ndsp.Pojo.BookListByCategoryResponse;
import com.example.ndsp.Pojo.EbookDetail;
import com.example.ndsp.Pojo.EbookDetailResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EbookBookFragment extends Fragment implements CategoryItemHolder.OnItemClickListener {
    private RetrofitService service;
    private RecyclerView recyclerView;
    private TextView tvtitle, tvname, tvprice;
    private ImageView imageView;
    private int authorBookList;
    List<EbookDetail> authorcategories = new ArrayList<>();
    private CategoryItemAdapter adapter;
    private LinearLayoutManager manager;
    private String categoryName;
    private SwipeRefreshLayout refreshLayout;


    public EbookBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ebook_book, container, false);
        initResource(view);
        return view;
    }

    public void initResource(View view) {
        service = new RetrofitService();
        recyclerView = view.findViewById(R.id.recyclerView);
        tvtitle = view.findViewById(R.id.tvtitle);
        tvname = view.findViewById(R.id.tvname);
        tvprice = view.findViewById(R.id.tvprice);
        imageView = view.findViewById(R.id.profile);
        refreshLayout=view.findViewById(R.id.swipeRefresh);

        manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new CategoryItemAdapter(this);
        recyclerView.setAdapter(adapter);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            authorBookList = bundle.getInt("author_id");
            categoryName = bundle.getString("author_name");
        }
        Log.e("author_list_id", String.valueOf(authorBookList));

        getEbookDetail(view);
    }

    public void getEbookDetail(View view) {
        Api api = service.getRetrofitService().create(Api.class);
//        api.getEbookDetail(authorBookList).enqueue(new Callback<EbookDetailResponse>() {
//            @Override
//            public void onResponse(Call<EbookDetailResponse> call, Response<EbookDetailResponse> response) {
//
//                authorcategories.addAll(response.body().book);
//            }
//
//            @Override
//            public void onFailure(Call<EbookDetailResponse> call, Throwable t) {
//
//            }
//        });
    }

    @Override
    public void onitemclicklistener(int id) {
        FragmentRecentBookDetail fragmentRecentBookDetail = new FragmentRecentBookDetail();
        Bundle bundle = new Bundle();
        bundle.putInt("book_id", Integer.parseInt(String.valueOf(id)));
//        bundle.putString("book_type", String.valueOf(categoryName));
        fragmentRecentBookDetail.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragmentRecentBookDetail).commit();

        Log.e("Ebook_list_id", String.valueOf(id));

    }
}

