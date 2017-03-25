package in.coders.fsociety.taskbuddy.Adapters;

import android.content.Context;


import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.RegisterResponse;
import in.coders.fsociety.taskbuddy.Models.SingleProfilePost;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    public void onBindViewHolder(final viewHolder holder, int position) {
        final SingleProfilePost post = list.get(position);

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

            String tags_text="";
            for(int i=0;i<list.get(position).getTags().size();i++){
                tags_text=tags_text+"#"+list.get(position).getTags().get(i)+" ";
            }

            if(tags_text.length()>=2)
                holder.tags.setText(tags_text);

            Glide.with(context).load(list.get(position).getAuthorPicUrl())
                    .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.person_icon)
                    .error(R.drawable.person_icon).into(new ImageViewTarget<Bitmap>(holder.authorImage) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                    drawable.setCircular(true);
                    holder.authorImage.setImageDrawable(drawable);
                }
            });

        }
        holder.creditView.setText(""+post.getCredit());
        if(post.getUserProgress()!=0){
            holder.wantedToWork.setVisibility(View.GONE);
        }

        holder.wantedToWork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<RegisterResponse> call = Util.getRetrofitService().register(new SharedPref(context).getUserId(),post.getId());
                call.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        RegisterResponse r = response.body();
                        if(r!=null&&response.isSuccess()){
                            holder.wantedToWork.setVisibility(View.GONE);
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
        holder.noOFWorkingView.setText("No of people interested: "+list.get(position).getNoOfParticipant()+"");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class viewHolder extends RecyclerView.ViewHolder{
        private ImageView authorImage;
        private TextView authorNameView,titleView,descriptionView,creditView,noOFWorkingView,tags;
        private Button wantedToWork;
        public viewHolder(View itemView) {
            super(itemView);
            authorImage = (ImageView) itemView.findViewById(R.id.profilePic);
            authorNameView = (TextView) itemView.findViewById(R.id.username);
            titleView = (TextView) itemView.findViewById(R.id.post_title);
            descriptionView = (TextView) itemView.findViewById(R.id.post_description);
            creditView = (TextView) itemView.findViewById(R.id.post_credits);
            tags = (TextView) itemView.findViewById(R.id.post_tags);
            noOFWorkingView = (TextView) itemView.findViewById(R.id.post_peoples_involved);
            wantedToWork = (Button) itemView.findViewById(R.id.wanted_to_work);
        }
    }
}
