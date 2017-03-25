package in.coders.fsociety.taskbuddy.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
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
import com.bumptech.glide.request.target.ImageViewTarget;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.RegisterResponse;
import in.coders.fsociety.taskbuddy.Models.UserModel;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

         final UserModel u= list.get(position);

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


        holder.cardCredits.setText("Credits: "+u.getCredit());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static  class viewHolder extends RecyclerView.ViewHolder{

        private ImageView cardImage;
        private TextView cardName,cardDescription,cardCredits;
        private Button button;

        public viewHolder(View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardName = (TextView) itemView.findViewById(R.id.card_name);
            cardDescription = (TextView) itemView.findViewById(R.id.card_description);
            cardCredits = (TextView) itemView.findViewById(R.id.card_credits);
            button = (Button) itemView.findViewById(R.id.addAsFriend);

        }
    }
}
