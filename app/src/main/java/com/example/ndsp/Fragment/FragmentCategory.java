package com.example.ndsp.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Language.Rabbit;
import com.example.ndsp.Pojo.CategoryAll;
import com.example.ndsp.Pojo.CategoryAllDataResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentCategory extends Fragment {
    Context context;
    ListView listView;
    RetrofitService retrofitService=new RetrofitService();
    List<CategoryAllDataResponse> categoryAllList=new ArrayList<>();
    List<String>categoryname=new ArrayList<>();
    Api api;
    ArrayAdapter<String>arrayAdapter;
    SharedPreferences sharedPreferences;
    public static final String LANGUAGE_PREFERENCE = "lan_pref", PREFERENCE_KEY = "lan_key";


    public FragmentCategory() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fragment_category, container, false);
        listView=view.findViewById(R.id.list_item);
        sharedPreferences=getActivity().getSharedPreferences(LANGUAGE_PREFERENCE,Context.MODE_PRIVATE);
        getCategoryId(view);
        ApiCall(view);
        return view;
    }

    public void ApiCall(final View view){

        api=retrofitService.getRetrofitService().create(Api.class);
        api.getCategoryAll().enqueue(new Callback<CategoryAll>() {
            @Override
            public void onResponse(Call<CategoryAll> call, final Response<CategoryAll> response) {

                if (response.isSuccessful()){
                    categoryAllList.addAll(response.body().categoryAllDataResponseList);
                    for (int i=0;i<categoryAllList.size();i++){
                        if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("z")){
                            categoryname.add(Rabbit.uni2zg(categoryAllList.get(i).categoryName));
                        }else if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("u")){
                            categoryname.add(Rabbit.zg2uni(categoryAllList.get(i).categoryName));
                        }else {
                            categoryname.add(categoryAllList.get(i).categoryName);

                        }
//                        Log.e("category id", String.valueOf(response.body().categoryAllDataResponseList.get(i)));
                    }
                    arrayAdapter= new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,categoryname);
                    listView.setAdapter(arrayAdapter);
                    arrayAdapter.notifyDataSetChanged();

                    }
                }
            @Override
            public void onFailure(Call<CategoryAll> call, Throwable t) {
                Log.e("CategoryAll",t.toString());
            }
        });
    }

    public void getCategoryId(View view){

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AuthorListFragment categoryList=new AuthorListFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("author_id",categoryAllList.get((int) l).id);
                bundle.putString("author_name",categoryAllList.get((int) l).categoryName);
                categoryList.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,categoryList).commit();

                Log.e("For Category Id", String.valueOf(categoryAllList.get(i).id));
                Log.e("For Category position", String.valueOf(i));

            }
        });

    }
}