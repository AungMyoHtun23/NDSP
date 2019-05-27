package com.example.ndsp.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ndsp.Adapter.CategoryItemAdapter;
import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Holder.CategoryItemHolder;
import com.example.ndsp.Pojo.BookListByCategoryDataResponse;
import com.example.ndsp.Pojo.BookListByCategoryResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryItemFragment extends Fragment implements CategoryItemHolder.OnItemClickListener {
    RetrofitService retrofitService=new RetrofitService();
    RecyclerView recyclerView;
    private TextView tvtitle,tvname,tvprice;
    private ImageView imageView;
    List<BookListByCategoryDataResponse>bookListByCategoryDataResponses=new ArrayList<>();
    private CategoryItemAdapter categoryItemAdapter;
    private LinearLayoutManager manager;
    private String categoryListID;
    private String bookType;


    public CategoryItemFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_category_item, container, false);
        initResource(view);
        return view;
    }

    public void initResource(View view){
        tvtitle=view.findViewById(R.id.tvtitle);
        tvname=view.findViewById(R.id.tvname);
        tvprice=view.findViewById(R.id.tvprice);
        imageView=view.findViewById(R.id.profile);
        recyclerView=view.findViewById(R.id.recycler_category_list);

        manager=new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        categoryItemAdapter=new CategoryItemAdapter( this);
        recyclerView.setAdapter(categoryItemAdapter);

        Bundle bundle=this.getArguments();
        if (bundle!=null){
            categoryListID= String.valueOf(bundle.getInt("author_id"));
            bookType=bundle.getString("book_type");
            Log.e("bookTypeinfragment",bookType);
            Log.e("categoryListID", String.valueOf(categoryListID));
        }
        getApi(view);

    }

    private void getApi(View view){
        Api api=retrofitService.getRetrofitService().create(Api.class);
        api.getbooklistcategory(String.valueOf(categoryListID)).enqueue(new Callback<BookListByCategoryResponse>() {
            @Override
            public void onResponse(Call<BookListByCategoryResponse> call, Response<BookListByCategoryResponse> response) {
                bookListByCategoryDataResponses.addAll(response.body().bookListByCategoryDataResponseCall);
                categoryItemAdapter.addItem(bookListByCategoryDataResponses);
            }

            @Override
            public void onFailure(Call<BookListByCategoryResponse> call, Throwable t) {
                Log.e("onFailureCategoryItem",t.toString());

            }
        });
    }

    @Override
    public void onitemclicklistener(int id) {
        FragmentRecentBookDetail fragmentRecentBookDetail=new FragmentRecentBookDetail();
        Bundle bundle=new Bundle();
        bundle.putInt("book_id",id);
        bundle.putString("book_type",bookType);
        fragmentRecentBookDetail.setArguments(bundle);
        FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_container,fragmentRecentBookDetail).commit();
        Log.e("book_list_id",String.valueOf(id));
    }
}
