package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ndsp.Pojo.CategoryAll;
import com.example.ndsp.R;

import java.util.ArrayList;
import java.util.List;

import static com.example.ndsp.R.layout.list_item_layout;


public class FragmentCategory extends Fragment {
    ListView listView;
    List<CategoryAll> categoryAllList=new ArrayList<>();

    public FragmentCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_category, container, false);
        listView=view.findViewById(R.id.list_item);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, list_item_layout, R.id.textView, categoryAllList);
//        listView.setAdapter(arrayAdapter);

        return view;
    }

}
