package in.coders.fsociety.taskbuddy.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Adapters.PostRecyclerAdapter;
import in.coders.fsociety.taskbuddy.Models.SingleProfilePost;
import in.coders.fsociety.taskbuddy.R;

/**
 * Created by sahil on 24/3/17.
 */

public class PostFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<SingleProfilePost> list;
    private static  final String POST_LIST="postList";
    public PostFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            list=getArguments().getParcelableArrayList(POST_LIST);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recycler,container,false);
        recyclerView= (RecyclerView) v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Log.d("size",""+list.size());
        PostRecyclerAdapter adapter = new PostRecyclerAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);
        return v;
    }
    public static  PostFragment newInstance(ArrayList<SingleProfilePost> list){
        PostFragment personFragment =new PostFragment();
        Bundle b=new Bundle();
        b.putParcelableArrayList(POST_LIST,list);
        personFragment.setArguments(b);
        return  personFragment;
    }

}
