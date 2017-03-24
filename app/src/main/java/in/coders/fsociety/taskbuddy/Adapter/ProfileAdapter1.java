package in.coders.fsociety.taskbuddy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.SingleProfilePost;
import in.coders.fsociety.taskbuddy.R;

public class ProfileAdapter1 extends RecyclerView.Adapter<ProfileAdapter1.ViewHolder> {

    ArrayList<SingleProfilePost> arrayList=new ArrayList<>();
    Context context;

    public ProfileAdapter1(Context context)
    {
        this.context = context;
    }
    public void refresh(ArrayList<SingleProfilePost> list){
        arrayList=list;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postfeed,parent,false);
        ViewHolder view_holder = new ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(arrayList!=null){

            if(arrayList.get(position).getTitle()!=null){

            }

        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView username,title,credits,post_peoples_involved;
        private ImageView profilePic;

        public ViewHolder(View v){
            super(v);
            this.profilePic = (ImageView)v.findViewById(R.id.profilePic);
            this.username = (TextView)v.findViewById(R.id.username);
            this.title = (TextView)v.findViewById(R.id.title);
            this.credits = (TextView)v.findViewById(R.id.post_credits);
            this.username = (TextView)v.findViewById(R.id.username);
            this.post_peoples_involved = (TextView)v.findViewById(R.id.post_peoples_involved);

        }
    }
}
