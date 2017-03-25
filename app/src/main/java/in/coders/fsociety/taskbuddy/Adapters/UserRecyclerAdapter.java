package in.coders.fsociety.taskbuddy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.RegisterResponse;
import in.coders.fsociety.taskbuddy.Models.UserModel;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(final viewHolder holder, int position) {
         final UserModel u= list.get(position);
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
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RegisterResponse> call = Util.getRetrofitService().addAsFriend(new SharedPref(context).getUserId(),u.getId());
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        RegisterResponse r = response.body();
                        if(r!=null&&response.isSuccess()){
                            holder.button.setVisibility(View.GONE);
                            Toast.makeText(context,r.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
           t.printStackTrace();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class viewHolder extends RecyclerView.ViewHolder{
        private ImageView userPic;
        private TextView userNameTextView,bioTextView,creditTextView;
        private Button button;
        public viewHolder(View itemView) {
            super(itemView);
            userPic = (ImageView) itemView.findViewById(R.id.profilePic);
            userNameTextView = (TextView) itemView.findViewById(R.id.username);
            bioTextView = (TextView) itemView.findViewById(R.id.post_description);
            creditTextView = (TextView) itemView.findViewById(R.id.post_credits);
            button = (Button) itemView.findViewById(R.id.addAsFriend);
        }
    }
}
