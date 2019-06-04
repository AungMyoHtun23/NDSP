package com.example.ndsp.Activity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.LightingColorFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ndsp.ApiInterface.Api;
import com.example.ndsp.Fragment.AuthorFragment;

import com.example.ndsp.Fragment.FragmentBlog;
import com.example.ndsp.Fragment.FragmentCart;
import com.example.ndsp.Fragment.FragmentCategory;
import com.example.ndsp.Fragment.FragmentExlplore;
import com.example.ndsp.Fragment.GenreFragment;
import com.example.ndsp.Fragment.PublisherFragment;

import com.example.ndsp.Fragment.SearchResultAuthorFragment;
import com.example.ndsp.Fragment.SearchResultBookFragment;
import com.example.ndsp.Fragment.SearchResultCategoryFragment;
import com.example.ndsp.Fragment.SearchResultEBookFragmemt;
import com.example.ndsp.Fragment.SearchResultGenreFragment;
import com.example.ndsp.Fragment.SearchResultPublisherFragment;
import com.example.ndsp.Fragment.SettingFragment;
import com.example.ndsp.Pojo.SearchAuthorResponse;
import com.example.ndsp.Pojo.SearchBookResponse;
import com.example.ndsp.Pojo.SearchCategoryResponse;
import com.example.ndsp.Pojo.SearchEbookResponse;
import com.example.ndsp.Pojo.SearchGenreResponse;
import com.example.ndsp.Pojo.SearchItemList;
import com.example.ndsp.Pojo.SearchPublisherResponse;
import com.example.ndsp.R;
import com.example.ndsp.RetrofitService.RetrofitService;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.miguelcatalan.materialsearchview.MaterialSearchView.*;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayAdapter<String > adapter ;
    List<String>list=new ArrayList<>();

    private SearchItemList searchItemList=new SearchItemList();
    private RetrofitService service=new RetrofitService();
    ValueAnimator animator;
    DrawerArrowDrawable drawerArrowDrawable;
    ImageView plusIcon;
    Toolbar toolbar;
    TextView toolbarTitle;
    LinearLayout layoutData;
    DrawerLayout drawer;
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        plusIcon = findViewById(R.id.search_icon_plus);

        drawerArrowDrawable = new DrawerArrowDrawable(this);

        loadFragment(new FragmentExlplore());
        initResource();

        drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

//NavigationDrawer
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //bottomnavigation
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

//        drawerArrorFunction();

//        this.searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//                layoutData.setVisibility(View.GONE);
//                searchConditions();
////            }
//
//            @Override
//            public void onSearchViewClosed() {
//                layoutData.setVisibility(View.VISIBLE);
//d
//            }
//        });
    }
    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            =new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {

                case R.id.btnnav_explore:
                    fragment=new FragmentExlplore();
                    loadFragment(fragment);
                    return true;
                case R.id.btnnav_category:
                    fragment=new FragmentCategory();
                    loadFragment(fragment);
                    return true;
                case R.id.btnnav_blog:
                    fragment=new FragmentBlog();
                    loadFragment(fragment );
                    return true;
                case R.id.btnnav_cart:
                    fragment=new FragmentCart();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
        if (!(currentFragment instanceof FragmentExlplore)) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, new FragmentExlplore()).commit();
//            animator.reverse();
        } else {
            showAlertDialog();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_view_menu_item, menu);
        MenuItem searchViewItem = menu.findItem(R.id.action_search);

        final SearchView searchViewAndroidActionBar = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        searchViewAndroidActionBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(final String query) {
                getMenuInflater().inflate(R.menu.main, menu);
                Log.e("query",query);
//                searchViewAndroidActionBar.clearFocus();
                // Inflate the menu; this adds items to the action bar if it is present.
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {

                final Api api=service.getRetrofitService().create(Api.class);

                //Search Publisher//
               api.getPublisherSearch(newText).enqueue(new Callback<ArrayList<SearchPublisherResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchPublisherResponse>> call, Response<ArrayList<SearchPublisherResponse>> response) {

                        if (response.isSuccessful()){
                            if (response.body().size()!=0){
                                Log.e("PubNewText",response.body().get(0).publisherName);

                            }else {
                                searchItemList.searchPublisherResponses.addAll(response.body());
                                SearchResultPublisherFragment searchResultPublisherFragment=new SearchResultPublisherFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("publisher_id", String.valueOf(response.body()));
                                Log.e("publisher_id", String.valueOf(response.body()));
                                searchResultPublisherFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,searchResultPublisherFragment).commit();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SearchPublisherResponse>> call, Throwable t) {
                        Log.e("pub_fail",t.toString());

                    }
                });


                //Search Author//

                api.getAuthorSearch(newText).enqueue(new Callback<ArrayList<SearchAuthorResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchAuthorResponse>> call, Response<ArrayList<SearchAuthorResponse>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size()!=0){
                                Log.e("author_newText", String.valueOf(response.body().get(0).authorName));
                            }else {

                                searchItemList.searchAuthorResponses.addAll(response.body());
                                SearchResultAuthorFragment fragment=new SearchResultAuthorFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("author_id", String.valueOf(response.body()));
                                Log.e("authortext", String.valueOf(response.body()));
                                fragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragment).commit();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SearchAuthorResponse>> call, Throwable t) {
                        Log.e("author_fail",t.toString());

                    }
                });

                //**Search Genre**//

                api.getGenreSearch(newText).enqueue(new Callback<ArrayList<SearchGenreResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchGenreResponse>> call, Response<ArrayList<SearchGenreResponse>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size()!=0){
                                Log.e("genre_newText",response.body().get(0).genreName);
                            }else {
                                searchItemList.searchGenreResponses.addAll(response.body());
                                SearchResultGenreFragment genreFragment=new SearchResultGenreFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("genre_id", String.valueOf(response.body()));
                                genreFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,genreFragment).commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SearchGenreResponse>> call, Throwable t) {
                        Log.e("Genre_fail",t.toString());

                    }
                });


                //**Search Book**//
                api.getSearchBook(newText).enqueue(new Callback<ArrayList<SearchBookResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchBookResponse>> call, Response<ArrayList<SearchBookResponse>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size() != 0){
                                Log.e("book_newText",response.body().get(0).bookTitle);
                            }
                            else {
                                searchItemList.searchBookResponses.addAll(response.body());
                                SearchResultBookFragment resultBookFragment=new SearchResultBookFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("book_id", String.valueOf(response.body()));
                                resultBookFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,resultBookFragment).commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SearchBookResponse>> call, Throwable t) {
                        Log.e("book_fail",t.toString());

                    }
                });

                //**Search Ebook**//
                api.getEbookSearch(newText).enqueue(new Callback<ArrayList<SearchEbookResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchEbookResponse>> call, Response<ArrayList<SearchEbookResponse>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size()!=0){
                                Log.e("ebook_newText",response.body().get(0).bookTitle);
                            }else {
                                searchItemList.searchEbookResponses.addAll(response.body());
                                SearchResultEBookFragmemt eBookFragmemt=new SearchResultEBookFragmemt();
                                Bundle bundle=new Bundle();
                                bundle.putString("ebook_id", String.valueOf(response.body()));
                                eBookFragmemt.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,eBookFragmemt).commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SearchEbookResponse>> call, Throwable t) {
                        Log.e("ebook_fail",t.toString());

                    }
                });

                //***Search category*//
                api.getCategorySearch(newText).enqueue(new Callback<ArrayList<SearchCategoryResponse>>() {
                    @Override
                    public void onResponse(Call<ArrayList<SearchCategoryResponse>> call, Response<ArrayList<SearchCategoryResponse>> response) {
                        if (response.isSuccessful()){
                            if (response.body().size()!=0){
                                Log.e("category_newText",response.body().get(0).categoryName);

                            }
                            else {
                                searchItemList.searchCategoryResponses.addAll(response.body());
                                SearchResultCategoryFragment searchResultCategoryFragment=new SearchResultCategoryFragment();
                                Bundle bundle=new Bundle();
                                bundle.putString("category_id", String.valueOf(response.body().size()));
                                Log.e("categoryID", String.valueOf(response.body().size()));
                                searchResultCategoryFragment.setArguments(bundle);
                                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,searchResultCategoryFragment).commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ArrayList<SearchCategoryResponse>> call, Throwable t) {

                        Log.e("category_fail",t.toString());
                    }
                });

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

      switch (id){
          case R.id.action_search:
//          searchView.setVisibility(View.VISIBLE);
          break;
      }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_category) {
            FragmentCategory fragmentCategory=new FragmentCategory();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragmentCategory).commit();

        } else if (id == R.id.nav_author) {
            AuthorFragment authorFragment=new AuthorFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,authorFragment).commit();

        } else if (id == R.id.nav_publisher) {
            PublisherFragment publisherFragment=new PublisherFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,publisherFragment).commit();

        } else if (id == R.id.nav_genre) {
            GenreFragment genreFragment=new GenreFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,genreFragment).commit();

        }else if (id==R.id.nav_ebook){
            FragmentCategory fragmentCategory=new FragmentCategory();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,fragmentCategory).commit();

        }else if (id == R.id.nav_language) {
            SettingFragment settingFragment=new SettingFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,settingFragment).commit();


        }

        DrawerLayout drawer =findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    private void drawerArrorFunction() {

        drawerArrowDrawable.setSpinEnabled(false);
        drawerArrowDrawable.setColor(getResources().getColor(R.color.burger_white));
//        plusIcon.setImageDrawable(drawerArrowDrawable);
//        plusIcon.setVisibility(View.GONE);
        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.frame_container);
                if ((currentFragment instanceof FragmentExlplore)) {
                    drawerArrowDrawable.setColor(getResources().getColor(R.color.burger_white));

                } else {
                    drawerArrowDrawable.setColor(getResources().getColor(R.color.burger_white));
                }
                drawerArrowDrawable.setProgress((Float) animation.getAnimatedValue());
            }
        });
    }

    public void initResource(){

        toolbar = findViewById(R.id.toolbar);

        toolbarTitle = findViewById(R.id.tvToolbarTitle);

        layoutData = findViewById(R.id.frame_for_data);

        searchView=findViewById(R.id.search_view);

    }

    private void showAlertDialog() {
        Button btn_no,btn_yes;
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.exit_dialog, null);
        dialogBuilder.setView(dialogView);

        final AlertDialog b = dialogBuilder.create();
        b.setCanceledOnTouchOutside(true);
        b.show();
        btn_no= dialogView.findViewById(R.id.exit_No_btn);
        btn_yes= dialogView. findViewById(R.id.exit_Yes_btn);
        btn_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
        btn_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    }


