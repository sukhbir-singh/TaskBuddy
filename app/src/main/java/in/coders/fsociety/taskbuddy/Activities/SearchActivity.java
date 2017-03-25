package in.coders.fsociety.taskbuddy.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

import in.coders.fsociety.taskbuddy.Adapters.SearchAdapter;
import in.coders.fsociety.taskbuddy.Fragments.PersonFragment;
import in.coders.fsociety.taskbuddy.Fragments.PostFragment;
import in.coders.fsociety.taskbuddy.Models.SearchResponse;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressBar progressBar;
    private SearchAdapter adapter;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        adapter = new SearchAdapter(getSupportFragmentManager());
        searchView = (android.support.v7.widget.SearchView) findViewById(R.id.searchView);
        android.support.v7.widget.SearchView.SearchAutoComplete searchAutoComplete = (android.support.v7.widget.SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchAutoComplete.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        searchView.setIconified(false);
        searchView.setQueryHint("Search");
        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                progressBar.setVisibility(View.VISIBLE);
                search(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        searchView.setOnCloseListener(new android.support.v7.widget.SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                finish();
                return true;
            }
        });
    }

    private void search(String s){
        Call<SearchResponse> call = Util.getRetrofitService().getSearchResult(new SharedPref(SearchActivity.this).getUserId(),s);
        call.enqueue(new Callback<SearchResponse>() {
            @Override
            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                progressBar.setVisibility(View.GONE);

                SearchResponse searchResponse=response.body();
                if(response.isSuccess()&&searchResponse!=null){
                    adapter.reset();
                    tabLayout.setVisibility(View.VISIBLE);

                    adapter.addSlide(PostFragment.newInstance(searchResponse.getGetAllPosts()),"Posts");
                    adapter.addSlide(PersonFragment.newInstance(searchResponse.getGetAllUsers()),"Users");
                    adapter.addSlide(PostFragment.newInstance(searchResponse.getGetAllTagsPost()),"Tags");

                    viewPager.setAdapter(adapter);
                    tabLayout.setupWithViewPager(viewPager);
                }
            }

            @Override
            public void onFailure(Call<SearchResponse> call, Throwable t) {
              progressBar.setVisibility(View.GONE);
                t.printStackTrace();
            }
        });
    }


}
