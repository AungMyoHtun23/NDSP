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
import com.example.ndsp.Pojo.PublisherListResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.example.ndsp.model.AuthorList;
import com.example.ndsp.model.Publisher;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PublisherFragment extends Fragment {

    private ListView PublisherList;
    private RetrofitService service;

    List<Publisher> publishercategories = new ArrayList<>();
    List<String> publishers = new ArrayList<>();

    ArrayAdapter<String> dataadapter;


    public PublisherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_publisher, container, false);
        initPublisherList(view);
        return view;
    }

    private void initPublisherList(View view) {

        PublisherList = view.findViewById(R.id.publisherlist);
        service = new RetrofitService();

        getPublisherList();
        dataadapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, publishers);

        PublisherList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                String BookList = dataadapter.getItem(position);
//                Log.e("publishercategories", publishercategories.get(position).id);
//                Log.e("position", String.valueOf(position));
//                Intent intent =new Intent(getApplicationContext(), PublisherDetailActivity.class);
//                intent.putExtra("book_id", publishercategories.get(position).id);
//                Log.e("publisherActivity_id",String.valueOf(publishercategories.get(position).id));
//                startActivity(intent);
                Log.e("publishercategories", publishercategories.get(position).id);
                PublisherListFragment publisherListFragment=new PublisherListFragment();
                Bundle bundle=new Bundle();
                bundle.putInt("publisher_id", Integer.parseInt(publishercategories.get(position).id));
                publisherListFragment.setArguments(bundle);
                FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_container,publisherListFragment).commit();

                Log.e("publisherActivity_id",String.valueOf(publishercategories.get((int) id).id));

            }
        });

    }

    private void getPublisherList() {

        Api getPublisherList = service.getRetrofitService().create(Api.class);
        getPublisherList.getPublisherList().enqueue(new Callback<PublisherListResponse>() {
            @Override
            public void onResponse(Call<PublisherListResponse> call, Response<PublisherListResponse> response) {

                if (response.isSuccessful()) {
                    publishercategories.addAll(response.body().publishers);
                    Log.e("response", String.valueOf(response.body().publishers.size()));
                    for (int i = 0; i < publishercategories.size(); i++) {
                        publishers.add(publishercategories.get(i).publishername);
                    }
                    dataadapter.notifyDataSetChanged();
                    PublisherList.setAdapter(dataadapter);
                }

            }

            @Override
            public void onFailure(Call<PublisherListResponse> call, Throwable t) {

                Log.e("onfailure", t.toString());


            }
        });

    }

}
