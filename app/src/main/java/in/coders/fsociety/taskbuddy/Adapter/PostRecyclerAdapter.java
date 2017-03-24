package in.coders.fsociety.taskbuddy.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.SingleProfilePost;
import in.coders.fsociety.taskbuddy.R;

/**
 * Created by sahil on 24/3/17.
 */

public class PostRecyclerAdapter extends RecyclerView.Adapter<PostRecyclerAdapter.viewHolder> {
    private ArrayList<SingleProfilePost> list=new ArrayList<>();
    private Context context;

    public PostRecyclerAdapter(Context context,ArrayList<SingleProfilePost> list) {
        this.context=context;
        this.list = list;
    }

    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_postfeed,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        SingleProfilePost post = list.get(position);
      if(post.getTitle()!=null){
          holder.titleView.setText(post.getTitle());
      }
        if(post.getAuthorName()!=null){
            Log.d("tag",post.getAuthorName());
            holder.authorNameView.setText(post.getAuthorName());
        }
        if(post.getAuthorPicUrl()!=null){
            Glide.with(context).load(post.getAuthorPicUrl()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.authorImage);
        }
        if(post.getDescription()!=null){
            Log.d("tag",post.getDescription());
            holder.descriptionView.setText(post.getDescription());
        }
        holder.creditView.setText(""+post.getCredit());
        holder.noOFWorkingView.setText(""+post.getNoOfParticipant());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class viewHolder extends RecyclerView.ViewHolder{
        private ImageView authorImage;
        private TextView authorNameView,titleView,descriptionView,creditView,noOFWorkingView;
        public viewHolder(View itemView) {
            super(itemView);
            authorImage = (ImageView) itemView.findViewById(R.id.profilePic);
            authorNameView = (TextView) itemView.findViewById(R.id.username);
            titleView = (TextView) itemView.findViewById(R.id.post_title);
            descriptionView = (TextView) itemView.findViewById(R.id.post_description);
            creditView = (TextView) itemView.findViewById(R.id.post_credits);
            noOFWorkingView = (TextView) itemView.findViewById(R.id.post_peoples_involved);
        }
    }
}
