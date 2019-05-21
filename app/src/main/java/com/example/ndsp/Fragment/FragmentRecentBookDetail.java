package com.example.ndsp.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Pojo.RecentBookDetailResponce;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.squareup.picasso.Picasso;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.ndsp.RetrofitService.RetrofitService.BASE_URL;


public class FragmentRecentBookDetail extends Fragment {
    RatingBar ratingBar;
    TextView txtbooknameshow, txtauthornameshow, txtratingbarnumber, txtbookdetail, txtnameinfo, txtauthorinfo, txtpublisherinfo, txttypeinfo, txteditioninfo, txtpriceinfo;
    Button btnseeallcomment;
    RecyclerView recyclercomment;
    TextView txtdetail, txtcontinue;
    EditText etUserName,edComment;
    ImageView imageView;
    RetrofitService retrofitService = new RetrofitService();
    int book_id;
    FabSpeedDial fabSpeedDial;
    private String bookType;


    public FragmentRecentBookDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_recent_book_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initResoruce(view);
        fab_function();
        super.onViewCreated(view, savedInstanceState);
    }


    private void fab_function() {
        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.book_detail_add_to_cart:
                        Toast.makeText(getContext(), "Cart", Toast.LENGTH_SHORT).show();
                        displayQuantityFillDialog();
                        break;
                    case R.id.book_detail_comment:
                        Toast.makeText(getContext(), "Comment", Toast.LENGTH_SHORT).show();
                        displayCommentDialog();
                        break;
                }
                return true;
            }
        });
    }


    public void initResoruce(View view) {

        fabSpeedDial = getActivity().findViewById(R.id.fab_speedDial);
        retrofitService = new RetrofitService();
        imageView = view.findViewById(R.id.detail_image);

        Bundle extras=this.getArguments();

        if (extras!=null){
            book_id = extras.getInt("book_id");
            bookType=extras.getString("book_type");
        }

        Log.e("detail_book_id", String.valueOf(book_id));

        txtbooknameshow = view.findViewById(R.id.txt_book_detail_name);
        txtauthornameshow = view.findViewById(R.id.txt_author_name);
        ratingBar = view.findViewById(R.id.rating_bar_show);
        txtratingbarnumber = view.findViewById(R.id.rating_number);
        txtbookdetail = view.findViewById(R.id.tv_book_detail);
        txtnameinfo = view.findViewById(R.id.txt_name_info);
        txtauthorinfo = view.findViewById(R.id.txt_author_name_info);
        txtpublisherinfo = view.findViewById(R.id.txt_publisher_name_info);
        txttypeinfo = view.findViewById(R.id.tv_book_type_info);
        txteditioninfo = view.findViewById(R.id.tv_book_edition_info);
        txtpriceinfo = view.findViewById(R.id.tv_book_price_info);

        btnseeallcomment = view.findViewById(R.id.btn_see_all_comment);
        recyclercomment = view.findViewById(R.id.recycler_comment);

        txtdetail = view.findViewById(R.id.txt_detail);
        txtcontinue = view.findViewById(R.id.txt_continue);

        //binding booktype
        txttypeinfo.setText(bookType);

        bindtoNetwork(view);
    }

    public void bindtoNetwork(final View view) {

        Api api = retrofitService.getRetrofitService().create(Api.class);
        api.getrecentbookdetail(String.valueOf(book_id)).enqueue(new Callback<RecentBookDetailResponce>() {
            @Override
            public void onResponse(Call<RecentBookDetailResponce> call, Response<RecentBookDetailResponce> response) {

                if (response.isSuccessful()) {

                    Log.e("onbookdetail", String.valueOf(book_id));
                    Log.e("ondetail", "successful");
                    Log.e("authorname",String.valueOf(response.body().bookDetailBooks.author));
                    Log.e("price", String.valueOf(response.body().bookDetailBooks.bookSalePrice));
                    Log.e("publisher",String.valueOf(response.body().bookDetailBooks.detailBookPublisher.publisherName));
                    Log.e("edition",String.valueOf(response.body().bookDetailBooks.detailBookEdition.editionName));
                    Log.e("image",response.body().bookDetailBooks.bookCoverImgUrl);
                    Log.e("category",response.body().bookDetailBooks.category);

                    txtbooknameshow.setText(response.body().bookDetailBooks.bookTitle);
                    txtnameinfo.setText(response.body().bookDetailBooks.bookTitle);
                    txtauthorinfo.setText(response.body().bookDetailBooks.author);
                    txtpublisherinfo.setText(String.valueOf(response.body().bookDetailBooks.detailBookPublisher.publisherName));

                    txteditioninfo.setText(String.valueOf(response.body().bookDetailBooks.detailBookEdition.editionName));
                    txtpriceinfo.setText(String.valueOf(response.body().bookDetailBooks.bookSalePrice));

                    Picasso.get().load(BASE_URL+"/api/image/book/"+response.body().bookDetailBooks.bookCoverImgUrl).into(imageView);


                }
            }

            @Override
            public void onFailure(Call<RecentBookDetailResponce> call, Throwable t) {
                Log.e("ondetailfailure", t.toString());
            }
        });

    }

    void displayQuantityFillDialog() {

        final int[] q = {1};
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.order_quantity, null);
        dialogBuilder.setView(dialogView);

        final TextView qty = dialogView.findViewById(R.id.tv_qty);
        final ImageView add = dialogView.findViewById(R.id.add);
        final ImageView minus = dialogView.findViewById(R.id.minus);
        final Button btnDone = dialogView.findViewById(R.id.btn_order_qty);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(true);
        b.show();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                q[0]++;
                qty.setText("" + q[0]);
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ((q[0] > 1)) {
                    q[0]--;
                    qty.setText("" + q[0]);

                } else if (q[0] == 1) {
                    q[0] = 1;
                    qty.setText("" + q[0]);
                }

            }
        });
    }

    void displayCommentDialog() {

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.comment_dialog, null);
        dialogBuilder.setView(dialogView);
        final RatingBar ratingBarInput = dialogView.findViewById(R.id.rating_bar_input);
        final EditText etUserName = dialogView.findViewById(R.id.et_name);
        final EditText eTComment = dialogView.findViewById(R.id.et_comment);
        final Button btnCommit = dialogView.findViewById(R.id.btn_commit);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(true);
        b.show();

        ratingBarInput.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String s = String.valueOf(v);
                Toast.makeText(getContext(), "" + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    }
