package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ndsp.Adapter.CategoryRecyclerAdapter;
import com.example.ndsp.Adapter.CustomPagerAdapter;
import com.example.ndsp.Adapter.RecentBooksRecyclerAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.RecentBooksRecyclerHolder;
import com.example.ndsp.Pojo.RecentBook;
import com.example.ndsp.Pojo.RecentProduct;
import com.example.ndsp.Pojo.TenCategoryResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentExlplore extends Fragment implements RecentBooksRecyclerHolder.OnItemClicked{
    ArrayList<RecentProduct> recentProducts = new ArrayList<>();
    ArrayList<TenCategoryResponse>tenCategoryResponses=new ArrayList<>();
    private ViewPager viewPager;
    CustomPagerAdapter customPagerAdapter;
    Button btnauthor, btnpublisher, btngenre;
    TextView top10seeall, recentseeall, ebookseeall;
    RecyclerView recyclertop10, recyclerrecent, recyclercategory, recyclerebooks;
    FrameLayout frametop10, framerecent, framecategory, frame_ebook;
    RecentBooksRecyclerAdapter recentAdapter;
    CategoryRecyclerAdapter categoryRecyclerAdapter,categoryRecyclerAdapter1;
    RecentBook recentBook;
    private RetrofitService retrofitService;

    public FragmentExlplore() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        //viewpager
        viewPager = view.findViewById(R.id.view_pager);
        customPagerAdapter = new CustomPagerAdapter(getContext());
        viewPager.setAdapter(customPagerAdapter);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MyTimerTask(), 2000, 4000);

        initResource(view);
        initFragment(view);
        //CategoryBook
        categoryBookShow(view);

        //EBookShow
        EbookShow(view);

        if (recentProducts.size() == 0) {
            rectentBookShow(view);
        } else {
            recentAdapter.setRecentProductsList(recentProducts);

        }
        return view;
    }



    @Override
    public void onItemClick(int position) {
        FragmentRecentBookDetail recentBookDetail= new FragmentRecentBookDetail();
//        Intent intent=new Intent();
//        intent.putExtra("book_id",position);
        Bundle bundle=new Bundle();
        bundle.putInt("book_id",position);
        recentBookDetail.setArguments(bundle);
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container,recentBookDetail).commit();
//        recentBookDetail.setEnterTransition(new Explode());
//        fragmentManager.beginTransaction().replace(R.id.frame_container,recentBookDetail).commit();
        Log.e("book_id", String.valueOf(position));

    }

    public class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            if (getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(1);
                    } else if (viewPager.getCurrentItem() == 1) {
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }

                }
            });

        }
    }

    public void initResource(View view) {
        retrofitService = new RetrofitService();

        top10seeall = view.findViewById(R.id.txt_see_all);
        recentseeall = view.findViewById(R.id.recent_see_all);
        ebookseeall = view.findViewById(R.id.ebooks_see_all);

        btnauthor = view.findViewById(R.id.btnauthor);
        btnpublisher = view.findViewById(R.id.btnpublisher);
        btngenre = view.findViewById(R.id.btngenre);

        recyclertop10 = view.findViewById(R.id.recycler_top10);
        recyclercategory = view.findViewById(R.id.recycler_categories);
        recyclerrecent = view.findViewById(R.id.recycler_recent);
        recyclerebooks = view.findViewById(R.id.recycler_ebooks);


        frametop10 = view.findViewById(R.id.frame_top10);
        framecategory = view.findViewById(R.id.frame_categories);
        framerecent = view.findViewById(R.id.frame_recent);
        frame_ebook = view.findViewById(R.id.frame_ebooks);

    }

    public void initFragment(View view) {

        framerecent.setVisibility(View.GONE);
        recyclerrecent.setVisibility(view.VISIBLE);

        framecategory.setVisibility(View.GONE);
        recyclercategory.setVisibility(view.VISIBLE);

        frame_ebook.setVisibility(View.GONE);
        recyclerebooks.setVisibility(view.VISIBLE);



    }

    //**API For RecentBook**//
    public void rectentBookShow(View view) {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        //For Recent Recycler
        recyclerrecent.setLayoutManager(linearLayoutManager);
        recentAdapter = new RecentBooksRecyclerAdapter(getContext(), this);
        recyclerrecent.setAdapter(recentAdapter);


        Api api = retrofitService.getRetrofitService().create(Api.class);
        api.getRecentbook().enqueue(new Callback<ArrayList<RecentProduct>>() {
            @Override
            public void onResponse(Call<ArrayList<RecentProduct>> call, Response<ArrayList<RecentProduct>> response) {
                if (response.isSuccessful()){
                    recentAdapter.setRecentProductsList(response.body());
                    Log.d("book size", String.valueOf(response.body().size()));

                }
            }

            @Override
            public void onFailure(Call<ArrayList<RecentProduct>> call, Throwable t) {
                Log.d("recentbook failure",t.toString());

            }
        });
    }

    //**API For Categories**//
    public void categoryBookShow(View view){

        //For Category Recycler
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclercategory.setLayoutManager(linearLayoutManager);
        categoryRecyclerAdapter=new CategoryRecyclerAdapter(getContext(),this);
        recyclercategory.setAdapter(categoryRecyclerAdapter);

        Api api=retrofitService.getRetrofitService().create(Api.class);
        api.getTenCategory().enqueue(new Callback<ArrayList<TenCategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TenCategoryResponse>> call, Response<ArrayList<TenCategoryResponse>> response) {
                if (response.isSuccessful()){
                    categoryRecyclerAdapter.setTenCategoryResponses(response.body());
                    Log.e("categor size", String.valueOf(response.body().size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TenCategoryResponse>> call, Throwable t) {
                Log.e("TenCategory Failure",t.toString());
            }
        });
    }

    //**API For EBook**//
    public void EbookShow(View view){

        //For Category Recycler
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerebooks.setLayoutManager(linearLayoutManager);
        categoryRecyclerAdapter1=new CategoryRecyclerAdapter(getContext(),this);
        recyclerebooks.setAdapter(categoryRecyclerAdapter);

        Api api=retrofitService.getRetrofitService().create(Api.class);
        api.getTenCategory().enqueue(new Callback<ArrayList<TenCategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<TenCategoryResponse>> call, Response<ArrayList<TenCategoryResponse>> response) {
                if (response.isSuccessful()){
                    categoryRecyclerAdapter.setTenCategoryResponses(response.body());
                    Log.e("Ebook size", String.valueOf(response.body().size()));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TenCategoryResponse>> call, Throwable t) {
                Log.e("Ebook Failure",t.toString());
            }
        });
    }


}
