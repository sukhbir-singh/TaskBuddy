package in.coders.fsociety.taskbuddy;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {

    private Context context;
    private ProgressBar bar;
    private int type=1;

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

        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler);

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

}
