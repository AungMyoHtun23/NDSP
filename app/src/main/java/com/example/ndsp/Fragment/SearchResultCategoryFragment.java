package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.ndsp.Adapter.SearchResultCategoryAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.SearchResultCategoryHolder;
import com.example.ndsp.Pojo.SearchCategoryResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultCategoryFragment extends Fragment implements SearchResultCategoryHolder.OnClickSearchCategory {
    private RecyclerView recyclerView;
    private LinearLayoutManager layout;
    private SearchResultCategoryAdapter resultCategoryAdapter;
    String categoryName;
    private RetrofitService service=new RetrofitService();


    public SearchResultCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_search_result_category, container, false);
        initResource(view);

        return view;
    }

    public void initResource(View view){
        recyclerView=view.findViewById(R.id.rv_search_category);
        layout=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layout);
        resultCategoryAdapter=new SearchResultCategoryAdapter(this);
        recyclerView.setAdapter(resultCategoryAdapter);

        Bundle bundle=getArguments();

        if (bundle!=null){

            categoryName= bundle.getString("category_id");
        }

        getCategoryDetail(view);

    }

    public void getCategoryDetail(View view){

        Api api=service.getRetrofitService().create(Api.class);
        api.getCategorySearch(categoryName).enqueue(new Callback<ArrayList<SearchCategoryResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<SearchCategoryResponse>> call, Response<ArrayList<SearchCategoryResponse>> response) {
                if (response.isSuccessful()){
                    Log.e("search_category_success", String.valueOf(response.body().size()));

                }

            }

            @Override
            public void onFailure(Call<ArrayList<SearchCategoryResponse>> call, Throwable t) {
                Log.e("search_category_fail",t.toString());

            }
        });

    }

    @Override
    public void clickSearchCategory(int id) {

    }
}
