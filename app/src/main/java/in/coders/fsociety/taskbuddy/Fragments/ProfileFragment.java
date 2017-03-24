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

import in.coders.fsociety.taskbuddy.Models.ProfilePostModel;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private Context context;
    private ProgressBar bar;
    private int type=1;
    private RecyclerView recyclerView;

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

   public static ProfileFragment getInstance(Context context,int type){
       ProfileFragment fragment=new ProfileFragment();
       fragment.setContext(context);
       fragment.setType(type);

       return fragment;
   }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recycler,container,false);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);

        if(type==1){
            view.setBackgroundColor(Color.parseColor("#12f23a"));
        }else if(type==2){
            view.setBackgroundColor(Color.parseColor("#fff23a"));
        }else{
            view.setBackgroundColor(Color.parseColor("#f21f3a"));
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

                    Log.d("size",""+r.getPosts().size());
                    //adapter.setList(r.getGetAllMeal());

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
