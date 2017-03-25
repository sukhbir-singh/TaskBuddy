package in.coders.fsociety.taskbuddy.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.UserModel;
import in.coders.fsociety.taskbuddy.R;

import static com.facebook.FacebookSdk.getApplicationContext;

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
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_singleuser,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(final viewHolder holder, int position) {
         UserModel u= list.get(position);
        if(u.getName()!=null){
            holder.cardName.setText(u.getName());
        }

        if(u.getBio()!=null){
            holder.cardDescription.setText(u.getBio());
        }else{
            holder.cardDescription.setVisibility(View.GONE);
        }

        if(u.getPicUrl()!=null){
            Glide.with(getApplicationContext()).load(u.getPicUrl()).asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.drawable.person_icon)
                    .error(R.drawable.person_icon).into(new ImageViewTarget<Bitmap>(holder.cardImage) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(),resource);
                    drawable.setCircular(true);
                    holder.cardImage.setImageDrawable(drawable);
                }
            });
        }

        holder.cardCredits.setText("Credits: "+u.getCredit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class viewHolder extends RecyclerView.ViewHolder{
        private ImageView cardImage;
        private TextView cardName,cardDescription,cardCredits;

        public viewHolder(View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardName = (TextView) itemView.findViewById(R.id.card_name);
            cardDescription = (TextView) itemView.findViewById(R.id.card_description);
            cardCredits = (TextView) itemView.findViewById(R.id.card_credits);
        }
    }
}
