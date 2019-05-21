package com.example.ndsp.Activity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.SearchView;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.ndsp.Fragment.AuthorFragment;
import com.example.ndsp.Fragment.AuthorListFragment;
import com.example.ndsp.Fragment.CategoryItemFragment;
import com.example.ndsp.Fragment.FragmentBlog;
import com.example.ndsp.Fragment.FragmentCart;
import com.example.ndsp.Fragment.FragmentCategory;
import com.example.ndsp.Fragment.FragmentExlplore;
import com.example.ndsp.Fragment.GenreFragment;
import com.example.ndsp.Fragment.PublisherFragment;
import com.example.ndsp.Fragment.SearchResultAuthorFragment;
import com.example.ndsp.Holder.SearchResultAuthorHolder;
import com.example.ndsp.R;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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

//        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
//            @Override
//            public void onSearchViewShown() {
//                layoutData.setVisibility(View.GONE);
//                searchConditions();
////            }
//
//            @Override
//            public void onSearchViewClosed() {
//                layoutData.setVisibility(View.VISIBLE);
//
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
//                    toolbar.setTitle("Profile");
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
            public boolean onQueryTextSubmit(String query) {
                searchViewAndroidActionBar.clearFocus();
                // Inflate the menu; this adds items to the action bar if it is present.
                getMenuInflater().inflate(R.menu.main, menu);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
//        searchView.setCursorDrawable(R.drawable.custom_cursor);


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

    void searchConditions(){
    Fragment currentFragment=getSupportFragmentManager().findFragmentById(R.id.frame_container);

    if (currentFragment instanceof AuthorListFragment){

        this.searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                SearchResultAuthorFragment searchResultAuthorFragment=new SearchResultAuthorFragment();
                Bundle bundle=new Bundle();
                bundle.putString("author_id",query);
                searchResultAuthorFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,searchResultAuthorFragment).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchResultAuthorFragment searchResultAuthorFragment=new SearchResultAuthorFragment();
                Bundle bundle=new Bundle();
                bundle.putString("author_id",newText);
                searchResultAuthorFragment.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_container,searchResultAuthorFragment).commit();
                return true;
            }
        });
//    }else if (currentFragment instanceof CategoryItemFragment){
//
//
    }



    }

}
