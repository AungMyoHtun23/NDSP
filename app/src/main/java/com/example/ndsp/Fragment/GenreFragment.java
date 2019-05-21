package com.example.ndsp.Fragment;


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
import com.example.ndsp.Pojo.GenreListResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.example.ndsp.model.Genre;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GenreFragment extends Fragment {

    private ListView GenreList;
    private RetrofitService service;

    List<Genre> genrecategories = new ArrayList<>();
    List<String> genres = new ArrayList<>();

    ArrayAdapter<String> dataadapter;


    public GenreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_genre, container, false);
        initGenreList(view);
        return view;
    }

    private void initGenreList(View view) {

        GenreList =view.findViewById(R.id.genre_list);

        service = new RetrofitService();

        getGenreList();

        dataadapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,genres);

        GenreList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String BookList = dataadapter.getItem(position);
//                Log.e("genrecategoried", String.valueOf(genrecategories.get(position).id));
//                Log.e("position", String.valueOf(position));
//                Intent intent =new Intent(getApplicationContext(), GenreDetailActivity.class);
//                intent.putExtra("BookList", genrecategories.get(position).id);
//                Log.e("genreActicity_id",genrecategories.get(position).id);
//                startActivity(intent);
                GenreListFragment genreListFragment=new GenreListFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("BookList", Integer.parseInt(genrecategories.get(position).id));
                Log.e("GenrerID", String.valueOf(Integer.parseInt(genrecategories.get(position).id)));
                genreListFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,genreListFragment).commit();

            }
        });

    }

    private void getGenreList() {

        Api genreListApi = service.getRetrofitService().create(Api.class);
        genreListApi.getGenreList().enqueue(new Callback<GenreListResponse>() {
            @Override
            public void onResponse(Call<GenreListResponse> call, Response<GenreListResponse> response) {

                if (response.isSuccessful()){
                    genrecategories.addAll(response.body().genres);
                    Log.e("response",String.valueOf(response.body().genres.size()));
                    for(int i=0;i<genrecategories.size();i++){
                        genres.add(genrecategories.get(i).genrename);
                    }

                    dataadapter.notifyDataSetChanged();
                    GenreList.setAdapter(dataadapter);
                }

            }

            @Override
            public void onFailure(Call<GenreListResponse> call, Throwable t) {

            }
        });



    }


}
