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
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.ndsp.Adapter.SearchResultCategoryAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.SearchResultCategoryHolder;
import com.example.ndsp.Pojo.SearchCategoryResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchResultCategoryFragment extends Fragment {
    private ArrayList<SearchCategoryResponse>searchCategoryResponses=new ArrayList<>();
    private ListView searchList;
    String categoryName;
    ArrayAdapter<String>adapter;
    List<String>category;



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
        searchList=view.findViewById(R.id.list_search_category);
        adapter=new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,category);

        Bundle bundle=getArguments();

        if (bundle!=null){

            categoryName= bundle.getString("category_id");
        }
        searchList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
