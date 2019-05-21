package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.ndsp.Adapter.SearchResultAuthorAdapter;
import com.example.ndsp.Holder.SearchResultAuthorHolder;
import com.example.ndsp.R;

public class SearchResultAuthorFragment extends Fragment implements SearchResultAuthorHolder.OnClickSearchAuthor {
    ProgressBar progressBar;
    RecyclerView recyclerView;
    String authorName;
    LinearLayoutManager linearLayoutManager;
    SearchResultAuthorAdapter adapter;


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

    }

    @Override
    public void clickSearchAuthor(int id) {

    }
}
