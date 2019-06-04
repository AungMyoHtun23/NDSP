package com.example.ndsp.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ndsp.AbstractClass.EndlessScrollListener;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Language.Rabbit;
import com.example.ndsp.Pojo.AuthorListResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.example.ndsp.model.Author;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AuthorFragment extends Fragment {
    private ListView AuthorList;
    List<Author> authorcategories = new ArrayList<>();
    private RetrofitService service;
    private SharedPreferences sharedPreferences;
    public static final String LANGUAGE_PREFERENCE = "lan_pref", PREFERENCE_KEY = "lan_key";


    List<String> authors = new ArrayList<>();
    ArrayAdapter<String> dataadapter;


    public AuthorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_author, container, false);
        initAuthorList(view);
            return view;
    }

    private void getAuthorList() {

        final Api authorListApi = service.getRetrofitService().create(Api.class);
        authorListApi.getAuthorList().enqueue(new Callback<AuthorListResponse>() {
            @Override
            public void onResponse(Call<AuthorListResponse> call, Response<AuthorListResponse> response) {

                if (response.isSuccessful()) {
                    authorcategories.addAll(response.body().authors);
                    Log.e("response", String.valueOf(response.body().authors.size()));
                    for (int i = 0; i < authorcategories.size(); i++) {
                        if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("z")){
                                authors.add(Rabbit.uni2zg(authorcategories.get(i).authorName));

                        }else if (sharedPreferences.getString(PREFERENCE_KEY,"").equals("u")){
                            authors.add(Rabbit.zg2uni(authorcategories.get(i).authorName));
                        }else {
                            authors.add(authorcategories.get(i).authorName);

                        }


                    }
                    dataadapter.notifyDataSetChanged();
                    AuthorList.setAdapter(dataadapter);
                }
            }

            @Override
            public void onFailure(Call<AuthorListResponse> call, Throwable t) {
                Log.e("onfailure", t.toString());

            }
        });

    }
    private void initAuthorList(View view) {

        service = new RetrofitService();
        AuthorList = view.findViewById(R.id.authorlist);
        sharedPreferences=getContext().getSharedPreferences(LANGUAGE_PREFERENCE, Context.MODE_PRIVATE);
        getAuthorList();

        dataadapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, authors);

        AuthorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AuthorListFragment authorListFragment=new AuthorListFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("author_id", Integer.parseInt(authorcategories.get(position).id));
                authorListFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,authorListFragment).commit();

                Log.e("author_Id",authorcategories.get(position).id);

            }
        });

    }

}
