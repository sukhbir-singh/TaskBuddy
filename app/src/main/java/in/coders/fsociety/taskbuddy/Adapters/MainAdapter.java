package in.coders.fsociety.taskbuddy.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import in.coders.fsociety.taskbuddy.Models.RegisterResponse;
import in.coders.fsociety.taskbuddy.Models.SingleMainPost;
import in.coders.fsociety.taskbuddy.R;
import in.coders.fsociety.taskbuddy.Utils.SharedPref;
import in.coders.fsociety.taskbuddy.Utils.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    ArrayList<SingleMainPost> arrayList=new ArrayList<>();
    Context context;

    public MainAdapter(Context context, ArrayList<SingleMainPost> posts){
        this.context = context;
        this.arrayList=posts;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mainfeed,parent,false);
        MainAdapter.ViewHolder view_holder = new MainAdapter.ViewHolder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(final MainAdapter.ViewHolder holder, final int position) {
        if(arrayList!=null){

            if(arrayList.get(position).getTitle()!=null){
                holder.title.setText(arrayList.get(position).getTitle());
                holder.description.setText(arrayList.get(position).getDescription());
                holder.credits.setText(arrayList.get(position).getCredit()+"");

                //holder.username.setText(arrayList.get(position).getAuthorName()+"");

                holder.post_peoples_involved.setText("No of people interested: "+arrayList.get(position).getNoOfParticipant()+"");

                String tags_text="";
                for(int i=0;i<arrayList.get(position).getTags().size();i++){
                    tags_text=tags_text+"#"+arrayList.get(position).getTags().get(i)+" ";
                }

                if(arrayList.get(position).isPublic()==false){
                    holder.circle_name.setVisibility(View.VISIBLE);

                    String circles_text="";

                    for(int i=0;i<arrayList.get(position).getCategoryTitle().size();i++){
                        tags_text=circles_text+"#"+arrayList.get(position).getTags().get(i)+" ";
                    }

                    holder.circle_name.setText(circles_text+"");

                }else{
                    holder.circle_name.setVisibility(View.GONE);
                }

                if(tags_text.length()>=2)
                    holder.tags.setText(tags_text);
                else holder.tags.setVisibility(View.GONE);


                //Glide.with(context).load(arrayList.get(position).getAuthorPicUrl()).asBitmap().into(holder.profilePic);
                 if(arrayList.get(position).getUserProgress()!=0){
                     holder.button.setVisibility(View.GONE);
                 }

               holder.button.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       Log.d("tag",""+arrayList.get(position).getId());
                       Call<RegisterResponse> call = Util.getRetrofitService().register(new SharedPref(context).getUserId(),arrayList.get(position).getId());
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
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView username,description,title,credits,post_peoples_involved,tags,circle_name;
        private ImageView profilePic;
        private Button button;

        public ViewHolder(View v){
            super(v);
            this.profilePic = (ImageView)v.findViewById(R.id.mainfeed_profilePic);
            this.username = (TextView)v.findViewById(R.id.mainfeed_username);
            this.tags = (TextView)v.findViewById(R.id.mainfeed_tags);
            this.title = (TextView)v.findViewById(R.id.mainfeed_title);
            this.description = (TextView)v.findViewById(R.id.mainfeed_description);
            this.credits = (TextView)v.findViewById(R.id.mainfeed_credits);
            this.post_peoples_involved = (TextView)v.findViewById(R.id.mainfeed_peoples_involved);
            this.button=(Button)v.findViewById(R.id.wanted_to_work);
            this.circle_name = (TextView)v.findViewById(R.id.mainfeed_circles);

        }
    }
}

