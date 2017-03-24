package in.coders.fsociety.taskbuddy.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import in.coders.fsociety.taskbuddy.Adapters.ProfileAdapter1;
import in.coders.fsociety.taskbuddy.Adapters.ProfileAdapter2;
import in.coders.fsociety.taskbuddy.Models.ProfilePostModel;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private Context context;
    private ProgressBar bar;
    private int type=1;
    private RecyclerView recyclerView;
    private SharedPref sharedPref;

    public void setSharedPref(SharedPref sharedPref) {
        this.sharedPref = sharedPref;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public Context getContext() {
        return context;
    }

    public  void setContext(Context context) {
        this.context = context;
    }

   public static ProfileFragment getInstance(Context context, int type){
       ProfileFragment fragment=new ProfileFragment();
       fragment.setContext(context);
       fragment.setType(type);
       fragment.setContext(context);
       fragment.setSharedPref(new SharedPref(context));

       return fragment;
   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recycler,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        bar=(ProgressBar)view.findViewById(R.id.bar);

        if(type==1){
            view.setBackgroundColor(Color.parseColor("#e9fded"));
            getAllPosts(sharedPref.getUserId());

        }else if(type==2){
            view.setBackgroundColor(Color.parseColor("#fffded"));
            getAllWorks(sharedPref.getUserId());

        }else{
            view.setBackgroundColor(Color.parseColor("#fdeaed"));
            getAllCircles(sharedPref.getUserId());

        }

        LinearLayoutManager manager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        return view;
    }


    private void  getAllPosts(String id){
        Call<ProfilePostModel> call= Util.getRetrofitService().getProfilePosts(id);
        call.enqueue(new Callback<ProfilePostModel>() {
            @Override
            public void onResponse(Call<ProfilePostModel> call, Response<ProfilePostModel> response) {
                ProfilePostModel r=response.body();

                if(r!=null&&response.isSuccess()){
                    ProfileAdapter1 adapter1=new ProfileAdapter1(context, r.getPosts());
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    recyclerView.setAdapter(adapter1);

                    Log.d("profile fragment size",""+r.getPosts().size());

                    bar.setVisibility(View.GONE);
                }
                else {
                    bar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ProfilePostModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });
    }

    private void getAllWorks(String id){
        Call<ProfilePostModel> call= Util.getRetrofitService().getProfilePosts(id);
        call.enqueue(new Callback<ProfilePostModel>() {
            @Override
            public void onResponse(Call<ProfilePostModel> call, Response<ProfilePostModel> response) {
                ProfilePostModel r=response.body();

                if(r!=null&&response.isSuccess()){

                    ProfileAdapter2 adapter2=new ProfileAdapter2(context, r.getPosts());
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    recyclerView.setAdapter(adapter2);

                    Log.d("profile works size",""+r.getPosts().size());

                    bar.setVisibility(View.GONE);
                }
                else {
                    bar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ProfilePostModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });
    }

    private void getAllCircles(String id){
        Call<ProfilePostModel> call= Util.getRetrofitService().getProfilePosts(id);
        call.enqueue(new Callback<ProfilePostModel>() {
            @Override
            public void onResponse(Call<ProfilePostModel> call, Response<ProfilePostModel> response) {
                ProfilePostModel r=response.body();

                if(r!=null&&response.isSuccess()){
                    ProfileAdapter1 adapter1=new ProfileAdapter1(context, r.getPosts());
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));

                    recyclerView.setAdapter(adapter1);

                    Log.d("profile circles size",""+r.getPosts().size());


                    bar.setVisibility(View.GONE);
                }
                else {
                    bar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ProfilePostModel> call, Throwable t) {
                bar.setVisibility(View.GONE);
            }
        });
    }

}
