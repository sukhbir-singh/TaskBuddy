package in.coders.fsociety.taskbuddy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.UserModel;
import in.coders.fsociety.taskbuddy.R;

/**
 * Created by sahil on 24/3/17.
 */

public class UserRecyclerAdapter  extends RecyclerView.Adapter<UserRecyclerAdapter.viewHolder>{

    private ArrayList<UserModel> list;
    private Context context;

    public UserRecyclerAdapter(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
         UserModel u= list.get(position);
        if(u.getName()!=null){
            holder.userNameTextView.setText(u.getName());
        }
        if(u.getBio()!=null){
            holder.bioTextView.setText(u.getBio());
        }
        if(u.getPicUrl()!=null){
            Glide.with(context).load(u.getPicUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.userPic);
        }
        holder.creditTextView.setText(""+u.getCredit());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class viewHolder extends RecyclerView.ViewHolder{
        private ImageView userPic;
        private TextView userNameTextView,bioTextView,creditTextView;
        public viewHolder(View itemView) {
            super(itemView);
            userPic = (ImageView) itemView.findViewById(R.id.profilePic);
            userNameTextView = (TextView) itemView.findViewById(R.id.username);
            bioTextView = (TextView) itemView.findViewById(R.id.post_description);
            creditTextView = (TextView) itemView.findViewById(R.id.post_credits);
        }
    }
}
