package in.coders.fsociety.taskbuddy.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Adapter.UserRecyclerAdapter;
import in.coders.fsociety.taskbuddy.Models.SingleProfilePost;
import in.coders.fsociety.taskbuddy.Models.UserModel;
import in.coders.fsociety.taskbuddy.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends Fragment {


    private static final String USER_LIST ="userList" ;
    private RecyclerView recyclerView;
    private ArrayList<UserModel> list;
    private UserRecyclerAdapter adapter;
    public PersonFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            list=getArguments().getParcelableArrayList(USER_LIST);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_recycler, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new UserRecyclerAdapter(list,getContext());
        recyclerView.setAdapter(adapter);
        return v;
    }

    public static  PersonFragment newInstance(ArrayList<UserModel> list){
        PersonFragment personFragment =new PersonFragment();
        Bundle b=new Bundle();
        b.putParcelableArrayList(USER_LIST,list);
        personFragment.setArguments(b);
        return  personFragment;
    }

}
